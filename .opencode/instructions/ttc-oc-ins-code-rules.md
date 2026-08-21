# 项目编码规范 — taotao-cloud-goods

> 商品域 DDD 单体服务编码规范。
> 基础包：`com.taotao.cloud.goods`

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
- `facade`：防腐层，适配外部系统

### 禁止违反的依赖
```java
// ❌ 禁止：Controller 直接调用 Repository
@Autowired private GoodsDomainRepository goodsRepository;

// ❌ 禁止：Application Service 直接调用 Mapper
@Autowired private GoodsMapper goodsMapper;

// ❌ 禁止：Domain Service 注入 Repository
@Autowired private GoodsDomainRepository goodsRepository;

// ✅ 正确：Application Service 通过仓储接口操作持久化
private final GoodsDomainRepository goodsRepository;
```

## 2. 包结构规范

### Domain 层
```
com.taotao.cloud.goods.domain/
├── aggregate/         # 聚合根
│   └── GoodsAgg.java
├── entity/            # 实体
│   ├── Category.java
│   └── Tag.java
├── valobj/            # 值对象（final 字段 + 构造验证 + 无 setter）
│   ├── GoodsWeight.java
│   ├── GoodsStatus.java
│   ├── GoodsSpec.java
│   ├── GoodsName.java
│   ├── CategoryName.java
│   ├── CategoryDesc.java
│   └── WeightUnit.java
├── event/             # 领域事件
│   ├── GoodsCreateEvent.java
│   ├── CategoryCreateEvent.java
│   ├── FreightTemplateChangedEvent.java
│   └── GoodsAggSnapshot.java
├── repository/        # 仓储接口
│   └── GoodsDomainRepository.java
├── service/           # 领域服务
│   └── GoodsDomainService.java
├── factory/           # 工厂
│   ├── GoodsFactory.java
│   ├── CategoryFactory.java
│   ├── DraftGoodsFactory.java
│   └── GoodsTagFactory.java
└── assembler/         # 领域 Assembler
    ├── GoodsDomainAssembler.java
    ├── CategoryDomainAssembler.java
    └── GoodsTagDomainAssembler.java
```

### Application 层
```
com.taotao.cloud.goods.application/
├── service/
│   ├── command/       # 命令服务（写操作，@Transactional）
│   └── query/         # 查询服务（读操作，@Transactional(readOnly=true))
├── dto/
│   ├── command/       # 命令 DTO
│   ├── query/         # 查询 DTO
│   └── result/        # 结果 DTO
└── assembler/         # DTO ↔ 领域对象转换
```

### Interfaces 层
```
com.taotao.cloud.goods.interfaces/
├── controller/
│   ├── buyer/         # 买家端 API
│   ├── seller/        # 卖家端 API
│   ├── manager/       # 管理端 API
│   ├── inner/         # 内部 API
│   └── open/          # 开放平台（微信/支付宝通知等）
├── rpc/               # Dubbo RPC 实现
└── grpc/              # gRPC 实现
```

### API 层
```
com.taotao.cloud.goods.api/
├── rpc/
│   ├── query/         # RPC 查询接口
│   └── command/       # RPC 命令接口
├── inner/
│   ├── query/         # 内部查询接口
│   └── command/       # 内部命令接口
├── dto/               # 基础 DTO（BaseResponse, BaseQuery, BaseCommand）
└── rpc/dto/           # RPC 专用的 DTO
```

## 3. 聚合根的写法

```java
package com.taotao.cloud.goods.domain.aggregate;

@AggregateRoot
public class GoodsAgg {
    // 聚合内实体用对象引用（非 ID）
    private List<Tag> tags;
    private Category category;

    // 跨聚合用 ID 引用
    private Long storeId;

    // 值对象
    private GoodsName name;
    private GoodsStatus status;
    private GoodsWeight weight;
    private GoodsSpec spec;

    // 业务行为方法（不是 setter）
    public void publish() {
        if (status != GoodsStatus.DRAFT) {
            throw new DomainException("只有草稿状态的商品才能发布");
        }
        this.status = GoodsStatus.PUBLISHED;
        registerEvent(new GoodsCreateEvent(this.id));
    }

    // 无参构造（JPA 要求），protected
    protected GoodsAgg() {}

    // 静态工厂方法
    public static GoodsAgg create(GoodsFactory factory, ...) { ... }
}
```

## 4. 值对象的写法

```java
package com.taotao.cloud.goods.domain.valobj;

@Embeddable
public class GoodsWeight {
    private final BigDecimal value;
    private final WeightUnit unit;

    public GoodsWeight(BigDecimal value, WeightUnit unit) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("商品重量必须为正数");
        }
        this.value = value;
        this.unit = unit;
    }

    // 只有 getter，无 setter
    // 覆写 equals/hashCode（基于所有属性）
    protected GoodsWeight() {} // JPA
}
```

## 5. Application Service 规范

### 命令服务（写操作）
```java
package com.taotao.cloud.goods.application.service.command;

@ApplicationService
@Service
@Transactional
public class GoodsCommandServiceImpl implements GoodsCommandService {
    private final GoodsDomainRepository goodsRepository;
    private final GoodsDomainService goodsDomainService;

    @Override
    public CreateGoodsResponse createGoods(CreateGoodsCommand command) {
        // 1. 构建领域对象（通过工厂）
        // 2. 调用领域服务（如果需要跨聚合逻辑）
        // 3. 保存聚合
        // 4. 发布领域事件
        // 5. 返回 DTO
        return CreateGoodsResponse.fromDomain(goods);
    }
}
```

### 查询服务（读操作）
```java
package com.taotao.cloud.goods.application.service.query;

@ApplicationService
@Service
@Transactional(readOnly = true)
public class GoodsQueryServiceImpl implements GoodsQueryService {
    private final GoodsQueryRepository goodsQueryRepository;

    @Override
    public GoodsDetailResult queryDetail(Long goodsId) {
        // 直接返回 DTO/Result，不经过领域模型
        return goodsQueryRepository.getDetailById(goodsId);
    }
}
```

## 6. Controller 规范

```java
package com.taotao.cloud.goods.interfaces.controller.seller;

@RestController
@RequestMapping("/seller/goods")
public class GoodsSellerController extends BusinessController {
    // HTTP 解析 + 参数校验 + Result 封装
    // 严禁业务逻辑

    @GetMapping("/page")
    public Result<PageResult<GoodsSimpleResult>> page(GoodsPageQuery query) {
        return Result.success(goodsQueryService.pageQuery(query));
    }

    @PostMapping("/{id}/publish")
    public Result<Void> publish(@PathVariable Long id) {
        goodsCommandService.publish(id);
        return Result.success();
    }
}
```

## 7. 领域事件规范

```java
// 事件定义在 domain/event/
public class GoodsCreateEvent extends DomainEvent {
    private final Long goodsId;
    // 不可变，构造时赋值
}

// 事件在聚合根内注册
// 仓储 save() 时自动 flush 发布
// 订阅在 infrastructure/event/
```

## 8. MapStruct + Assembler 规范

```java
// Assembler 在 infrastructure/persistent/assembler/
// 职责：Domain Entity ←→ Persistence PO 双向映射

@Mapper(componentModel = "spring")
public interface GoodsAssembler {
    GoodsPo toPo(GoodsAgg goods);
    GoodsAgg toDomain(GoodsPo po);
}
```

## 9. API 返回规范

```java
// 统一使用 taotao-boot-starter-web 的 Result
import com.taotao.boot.common.model.result.Result;

// 成功返回
return Result.success(data);
return Result.success();

// 失败返回
return Result.fail("商品不存在");
return Result.fail(ErrorCode.GOODS_NOT_FOUND);
```

## 10. 命名规范

| 元素 | 命名规则 | 示例 |
|------|---------|------|
| 聚合根 | `{Entity}Agg` | `GoodsAgg` |
| 实体 | 业务名词 | `Category`, `Tag` |
| 值对象 | 描述性名词 | `GoodsWeight`, `GoodsStatus` |
| 领域事件 | `{名词}{过去式动词}Event` | `GoodsCreatedEvent` |
| 仓储接口 | `{Entity}DomainRepository` | `GoodsDomainRepository` |
| 领域服务 | `{Entity}DomainService` | `GoodsDomainService` |
| 命令 DTO | `{动词}{名词}Command` | `CreateGoodsCommand` |
| 查询 DTO | `{名词}PageQuery` | `GoodsPageQuery` |
| 结果 DTO | `{名词}Result` | `GoodsDetailResult` |
| 命令服务 | `{Entity}CommandService` | `GoodsCommandService` |
| 查询服务 | `{Entity}QueryService` | `GoodsQueryService` |

## 11. 构建与测试

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

# 覆盖率报告
./gradlew jacocoTestReport
```

## 12. 数据库规范

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
