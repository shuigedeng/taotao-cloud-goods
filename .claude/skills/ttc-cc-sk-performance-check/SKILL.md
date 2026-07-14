---
name: performance-check
description: 性能检查工作流，分析 SQL 查询、缓存策略、并发瓶颈、慢接口
triggers:
  - "性能检查"
  - "慢查询"
  - "接口优化"
  - "SQL 分析"
---

# 性能检查工作流 — taotao-cloud-goods

适用于商品域（Goods）的查询性能、缓存策略和并发瓶颈分析。

## 检查清单

### 1. 数据库查询性能

```sql
-- 检查慢查询日志
SHOW VARIABLES LIKE 'slow_query%';
SHOW VARIABLES LIKE 'long_query_time';

-- 检查索引使用情况
EXPLAIN SELECT * FROM t_goods WHERE category_id = ? AND status = ?;

-- 检查表大小
SELECT table_name, table_rows, data_length / 1024 / 1024 AS size_mb
FROM information_schema.tables
WHERE table_schema = 'taotao_cloud_goods';
```

#### 常见问题
- [ ] N+1 查询（ORM 懒加载导致）
- [ ] 缺少联合索引（经常组合查询的字段）
- [ ] `SELECT *` 查询（应只查询需要的列）
- [ ] 分页查询未使用覆盖索引（`ORDER BY` + `LIMIT` 导致 filesort）
- [ ] JOIN 字段缺少索引

### 2. 缓存策略检查

| 缓存层级 | 用途 | 建议 |
|---------|------|------|
| Redis (Redisson) | 商品详情缓存 | 缓存热点商品，TTL 5-30 分钟 |
| 本地缓存 (Caffeine) | 分类树、枚举字典 | 只读数据，TTL 1 小时 |
| 查询缓存 | 分页列表 | 根据变更频率决定是否缓存 |

- [ ] 是否存在缓存穿透风险（查询不存在的数据）
- [ ] 是否存在缓存雪崩风险（大量 key 同时过期）
- [ ] 缓存更新策略是否合理（先更新 DB 再删缓存）
- [ ] 热点 key 是否有本地缓存兜底

### 3. 接口性能分析

```bash
# 使用 Spring Boot Actuator 查看接口调用统计
curl http://localhost:{port}/actuator/metrics/http.server.requests

# 查看慢接口
curl http://localhost:{port}/actuator/metrics/http.server.requests?tag=uri:/seller/goods/page
```

- [ ] 响应时间是否 < 200ms（P99）
- [ ] 是否有不必要的循环查询
- [ ] 批量接口是否支持分页
- [ ] 大列表是否支持字段筛选

### 4. 并发瓶颈检查

- [ ] 商品库存扣减是否使用乐观锁/分布式锁
- [ ] 秒杀场景是否有独立限流方案
- [ ] `@Transactional` 范围是否合理（避免长事务）
- [ ] 是否有死锁风险（多个事务以不同顺序操作同一批数据）

### 5. 代码层性能

```java
// ❌ 循环中查询数据库（N+1）
for (Long goodsId : goodsIds) {
    GoodsAgg goods = goodsRepository.findById(goodsId); // 每次查询
}

// ✅ 批量查询
List<GoodsAgg> goodsList = goodsRepository.findByIds(goodsIds);

// ❌ 循环中组装数据
for (GoodsAgg goods : goodsList) {
    Brand brand = brandService.findById(goods.getBrandId()); // 额外查询
    Category category = categoryService.findById(goods.getCategoryId()); // 额外查询
}

// ✅ 批量组装
Map<Long, Brand> brandMap = brandService.findByIds(brandIds);
Map<Long, Category> categoryMap = categoryService.findByIds(categoryIds);
```

## 检查命令

```bash
# 开启 SQL 日志（开发环境）
./gradlew :taotao-cloud-goods-assembly:bootRun --args='--spring.profiles.active=dev --logging.level.com.taotao.cloud.goods.infrastructure.persistent=DEBUG'

# JaCoCo 检查热点代码
./gradlew jacocoTestReport

# OWASP 依赖安全
./gradlew dependencyCheckAnalyze
```

## 输出格式

```
📊 性能检查报告

✅ 通过：
- [项目]

⚠️ 警告：
- [项目]

❌ 问题：
- [严重度] [位置] [问题描述]

💡 优化建议：
- [建议]
```
