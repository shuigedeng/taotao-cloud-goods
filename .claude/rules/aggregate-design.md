# 聚合设计规范 — taotao-cloud-goods

## 聚合识别原则

### 1. 事务边界
聚合内修改必须在一个事务中完成，聚合间使用最终一致性（通过领域事件）。

### 2. 一致性规则
- 强一致性：聚合内保证
- 最终一致性：聚合间通过事件保证

### 3. 聚合大小
- 小聚合原则：一个聚合根只包含必要实体
- 聚合内实体用对象引用，跨聚合用 ID 引用

## 聚合根命名

```
{Entity}Agg
```

示例：`GoodsAgg`, `CategoryAgg`

## 代码规范

```java
@AggregateRoot
public class GoodsAgg {
    // 聚合内实体用对象引用
    private List<Tag> tags;
    private Category category;

    // 跨聚合用 ID 引用
    private Long storeId;
    private Long brandId;

    // 值对象
    private GoodsName name;
    private GoodsStatus status;
    private GoodsWeight weight;
    private GoodsSpec spec;

    // 业务行为方法（有语义的命令方法）
    public void publish() {
        if (status != GoodsStatus.DRAFT) {
            throw new DomainException("只有草稿状态的商品才能发布");
        }
        this.status = GoodsStatus.PUBLISHED;
        registerEvent(new GoodsCreateEvent(this.id));
    }

    public void offline(String reason) {
        if (status == GoodsStatus.OFFLINE) {
            throw new DomainException("商品已经下架");
        }
        this.status = GoodsStatus.OFFLINE;
        registerEvent(new GoodsOfflineEvent(this.id, reason));
    }

    // 无参构造（JPA 要求）
    protected GoodsAgg() {}

    // 静态工厂方法
    public static GoodsAgg create(...) { ... }
}
```

### 禁止做法
```java
// ❌ 贫血模型：setter 代替业务方法
public void setStatus(GoodsStatus status) { this.status = status; }

// ❌ 跨聚合引用对象而非 ID
private Store store;

// ❌ 聚合根中注入 Repository
@Autowired private GoodsDomainRepository repository;
```

### 聚合根方法设计

| 方法类型 | 规范 | 示例 |
|---------|------|------|
| 命令方法 | 有业务语义的动词 | `publish()`, `offline()`, `updateSpec()` |
| 查询方法 | 以 is/has/can 开头 | `isPublished()`, `canAddToCart()` |
| 工厂方法 | 静态 create 方法 | `GoodsAgg.create(factory, params)` |
