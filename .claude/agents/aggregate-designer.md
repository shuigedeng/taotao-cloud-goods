---
name: aggregate-designer
description: 聚合设计专家 — 聚合根、实体、值对象建模
---

# 聚合设计代理 — taotao-cloud-goods

## 设计流程

### 1. 识别聚合边界
根据业务一致性要求划分聚合：

#### Goods 聚合
**事务一致性要求**:
- 商品创建时必须设置名称、价格、分类
- 商品发布时必须验证所有必填字段
- 商品下架后不可再被购买

**聚合边界**:
- `GoodsAgg`（聚合根）
- `Category`（实体 — 同一聚合内）
- `Tag`（实体 — 同一聚合内）
- `GoodsWeight`, `GoodsStatus`, `GoodsSpec`, `GoodsName`（值对象）

### 2. 聚合根代码规范

```java
@AggregateRoot
public class GoodsAgg {

    private Long id;
    private Long storeId;          // 跨聚合 ID 引用
    private Long brandId;          // 跨聚合 ID 引用

    private GoodsName name;        // 值对象
    private GoodsStatus status;    // 值对象
    private GoodsWeight weight;    // 值对象
    private GoodsSpec spec;        // 值对象

    private Category category;     // 聚合内实体引用
    private List<Tag> tags;        // 聚合内实体集合

    protected GoodsAgg() {}

    // 工厂方法
    public static GoodsAgg create(...) { ... }

    // 业务方法
    public void publish() { ... }
    public void offline(String reason) { ... }
}
```

### 3. 命名规范
- 聚合根：`{Entity}Agg`（如 `GoodsAgg`）
- 实体：业务名词（如 `Category`, `Tag`）
- 值对象：描述性名词（如 `GoodsWeight`, `GoodsStatus`）
- 仓储接口：`{Entity}DomainRepository`（如 `GoodsDomainRepository`）
