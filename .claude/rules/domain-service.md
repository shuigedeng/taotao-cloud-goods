# 领域服务设计规范 — taotao-cloud-goods

## 何时使用领域服务

### 适用场景
1. **跨聚合的业务逻辑** — 涉及多个聚合根的协调操作
2. **无状态的计算服务** — 不持有状态，只提供计算行为
3. **不属于任何单一聚合的外部概念**

### 不适用场景
1. **应该属于聚合根的行为**（如 `order.addItem()` 不应提取到 Service）
2. **纯粹的技术性操作**（属于 infrastructure 层）
3. **应用层的用例编排**（属于 application 层）

## 代码规范

```java
@DomainService
@Service
public class GoodsDomainService {

    // 方法名表达业务意图
    public void transferCategory(GoodsAgg goods, Category oldCategory, Category newCategory) {
        // 跨聚合的业务逻辑
        if (newCategory.isDisabled()) {
            throw new DomainException("目标分类已禁用");
        }

        goods.changeCategory(newCategory.getId());
        oldCategory.removeGoods(goods.getId());

        // 注册领域事件
        DomainEventPublisher.publish(new CategoryChangedEvent(goods.getId(),
            oldCategory.getId(), newCategory.getId()));
    }

    // 无状态查询
    public boolean isGoodsValidForSale(GoodsAgg goods) {
        return goods.isPublished()
            && !goods.isExpired()
            && goods.getStock() > 0;
    }
}
```

## 命名规范

```
{Entity}DomainService
```

示例：`GoodsDomainService`, `CategoryDomainService`

## 禁止做法
```java
// ❌ 有状态的领域服务
private Map<Long, GoodsAgg> cache;

// ❌ 技术性操作
public void saveToElasticsearch(GoodsAgg goods) { ... }

// ❌ 应用层编排逻辑（属于 Application Service）
public CreateGoodsResponse createGoods(CreateGoodsCommand command) { ... }
```
