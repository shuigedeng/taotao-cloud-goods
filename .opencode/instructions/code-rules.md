# 项目编码规范 — taotao-cloud-goods

> 补充 DDD 架构规范（详见 `.claude/CLAUDE.md` 和 `.claude/rules/`）中未覆盖的实现细节

---

## 1. 模块依赖规则

```
api  ←  interfaces  ←  application  →  facade
                          ↓
                     domain  ←  infrastructure
```

- `domain`：零外部依赖，不依赖 Spring、不依赖数据库
- `application`：依赖 `domain`，可依赖 `facade` 接口，不依赖 `infrastructure`
- `infrastructure`：依赖 `domain` 实现仓储，依赖 `application` 实现事件订阅
- `interfaces`：依赖 `application`，不直接依赖 `infrastructure`
- `api`：纯 DTO + 接口定义，不依赖任何业务模块

### 禁止违反的依赖
```java
// ❌ 禁止：Controller 直接调用 Repository
@Autowired private OrderRepository orderRepository;

// ❌ 禁止：Application Service 直接调用 Mapper
@Autowired private OrderMapper orderMapper;

// ❌ 禁止：Domain Service 注入 Repository
@Autowired private OrderRepository orderRepository;

// ✅ 正确：Application Service 通过仓储接口操作持久化
private final OrderDomainRepository orderRepository;
```

## 2. 包结构规范

```
com.taotao.cloud.order.{module}/
├── aggregate/     # 聚合根（@AggregateRoot）
├── entity/        # 实体（@Entity）
├── valobj/        # 值对象（@ValueObject | @Embeddable）
├── event/         # 领域事件（extends DomainEvent）
├── repository/    # 仓储接口
└── service/       # 领域服务（@DomainService）
```

### 聚合根的写法
```java
@AggregateRoot
public class OrderAgg {
    // 聚合内实体用对象引用（非 ID）
    private List<OrderItem> items;

    // 跨聚合用 ID 引用
    private Long customerId;

    // 业务行为方法（不是 setter）
    public void addItem(ProductId productId, Money price, int quantity) {
        // 校验业务规则
        // 修改内部状态
        // 注册领域事件
        registerEvent(new OrderItemAddedEvent(this.id, productId));
    }

    // 无参构造（JPA 要求），protected
    protected OrderAgg() {}

    // 静态工厂方法
    public static OrderAgg create(...) { ... }
}
```

### 值对象的写法
```java
@Embeddable
public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    // 构造时自验证
    public Money(BigDecimal amount, Currency currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("金额不能为负数");
        }
        this.amount = amount;
        this.currency = currency;
    }

    // 只有 getter，无 setter
    // 覆写 equals/hashCode（基于所有属性）
}
```

## 3. Application Service 规范

### 命令服务（写操作）
```java
@ApplicationService
@Service
@Transactional
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderDomainRepository orderRepository;
    private final OrderDomainService orderDomainService;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        // 1. 构建领域对象
        // 2. 调用领域服务（如果需要跨聚合逻辑）
        // 3. 保存聚合
        // 4. 发布领域事件
        // 5. 返回 DTO
        return CreateOrderResponse.fromDomain(order);
    }
}
```

### 查询服务（读操作）
```java
@ApplicationService
@Service
@Transactional(readOnly = true)
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderQueryRepository orderQueryRepository;

    @Override
    public OrderDetailResult queryDetail(String orderSn) {
        // 直接返回 DTO/Result，不经过领域模型
        return orderQueryRepository.getDetailBySn(orderSn);
    }
}
```

## 4. Controller 规范

```java
@RestController
@RequestMapping("/{role}/order/order")
// role = buyer | seller | manager
public class OrderBuyerController extends BusinessController {
    // HTTP 解析 + 参数校验 + Result 封装
    // 禁止业务逻辑

    @GetMapping("/page")
    public Result<PageResult<OrderSimpleResult>> page(OrderPageQuery query) {
        return Result.success(orderQueryService.pageQuery(query));
    }

    @PostMapping("/{orderSn}/cancel")
    public Result<Void> cancel(@PathVariable String orderSn, @RequestParam String reason) {
        orderCommandService.cancel(orderSn, reason);
        return Result.success();
    }
}
```

## 5. 枚举规范

```java
// 订单状态枚举，在 common 模块定义
public enum OrderStatusEnum {
    PENDING("待付款"),
    PAID("已付款"),
    DELIVERED("已发货"),
    RECEIVED("已收货"),
    COMPLETED("已完成"),
    CANCELLED("已取消");

    private final String description;
    // ...
}
```

## 6. 领域事件规范

```java
// 事件定义在 domain/event/
public class OrderCreatedEvent extends DomainEvent {
    private final Long orderId;
    // 不可变，构造时赋值
}

// 事件在聚合根内注册
// 仓储 save() 时自动 flush 发布
// 订阅在 infrastructure/event/
```

## 7. MapStruct + Assembler 规范

```java
// Assembler 在 infrastructure/assembler/
// 职责：Domain Entity ←→ Persistence PO 双向映射

@Mapper(componentModel = "spring")
public interface OrderAssembler {
    OrderPo toPo(Order order);
    Order toDomain(OrderPo po);
}
```

## 8. 构建与测试

```bash
# 全量构建
./gradlew build

# 运行所有测试
./gradlew test

# 运行指定模块测试
./gradlew :taotao-cloud-goods-domain:test

# 代码质量
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 本地启动
./gradlew :taotao-cloud-goods-assembly:bootRun --args='--spring.profiles.active=dev'
```

## 9. 数据库规范

### 表必备字段
```sql
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
`create_by` bigint DEFAULT NULL COMMENT '创建人ID',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_by` bigint DEFAULT NULL COMMENT '更新人ID',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
`tenant_id` bigint DEFAULT 0 COMMENT '租户ID',
`version` int DEFAULT 0 COMMENT '乐观锁'
```

### 禁止
- 循环中查询数据库（N+1 问题）
- `SELECT *`
- 在 Java 代码中拼接 SQL
- 跨聚合直接操作其他聚合的数据表


