---
name: event-storming
description: 事件风暴工作流，识别领域事件、命令和聚合
triggers:
  - "事件风暴"
  - "领域建模"
  - "识别聚合"
---

# 事件风暴工作流 — taotao-cloud-goods

## 步骤1：识别领域事件

询问用户以下问题：
1. 商品域业务流程中有哪些关键事件？
2. 哪些事件会改变商品状态？
3. 哪些事件会触发其他行为？

**输出格式**：
```markdown
## 领域事件列表

1. **GoodsCreated** - 商品已创建
   - 触发条件: 卖家创建商品
   - 结果: 商品状态变为草稿
   - 订阅者: 搜索引擎、统计服务

2. **GoodsPublished** - 商品已发布
   - 触发条件: 卖家发布商品
   - 结果: 商品状态变为已发布
   - 订阅者: 搜索引擎、推荐系统
```

## 步骤2：识别命令

| 命令 | 触发事件 | 执行者 | 验证规则 |
|------|---------|--------|---------|
| CreateGoods | GoodsCreated | 卖家 | 名称/价格/分类必填 |
| PublishGoods | GoodsPublished | 卖家 | 商品为草稿状态 |
| OfflineGoods | GoodsOfflined | 卖家/系统 | 商品为已发布状态 |

## 步骤3：识别聚合

### Goods 聚合
- **聚合根**: GoodsAgg
- **包含实体**: Category, Tag
- **包含值对象**: GoodsWeight, GoodsStatus, GoodsSpec, GoodsName
- **命令**: CreateGoods, PublishGoods, OfflineGoods, UpdateGoods
- **事件**: GoodsCreated, GoodsPublished, GoodsOfflined
- **不变性**:
  - 只有草稿商品可以发布
  - 发布后不可修改关键属性
  - 下架商品不可被购买

## 步骤4：输出产物
- `docs/event-storming/domain-events.md` — 领域事件清单
- `docs/event-storming/aggregates.md` — 聚合设计文档
- `docs/event-storming/business-rules.md` — 业务规则文档
