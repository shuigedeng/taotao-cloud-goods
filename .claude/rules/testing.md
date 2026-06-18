# 测试规范 — taotao-cloud-goods

## 分层测试策略

### 领域层测试（单元测试）
- 纯 POJO 测试，无需 Spring 上下文
- 测试聚合根业务规则、值对象验证
- 测试领域事件注册
- 覆盖率目标：>= 90%

```java
class GoodsAggTest {
    @Test
    void shouldPublishDraftGoods() {
        GoodsAgg goods = GoodsAgg.createDraft("测试商品", new BigDecimal("100"));
        goods.publish();
        assertThat(goods.getStatus()).isEqualTo(GoodsStatus.PUBLISHED);
        assertThat(goods.getDomainEvents()).hasSize(1);
    }

    @Test
    void shouldRejectPublishWhenNotDraft() {
        GoodsAgg goods = GoodsAgg.createDraft("测试", BigDecimal.TEN);
        goods.publish();
        assertThatThrownBy(() -> goods.publish())
            .isInstanceOf(DomainException.class);
    }
}
```

### 应用层测试（集成测试）
- 使用 `@SpringBootTest`
- 测试事务边界、仓储交互

```java
@SpringBootTest
@Transactional
class GoodsCommandServiceTest {
    @Autowired
    private GoodsCommandService goodsCommandService;

    @Test
    void shouldCreateGoods() {
        CreateGoodsCommand command = new CreateGoodsCommand(...);
        GoodsDetailResult result = goodsCommandService.create(command);
        assertThat(result.id()).isNotNull();
    }
}
```

### 基础设施测试
- 仓储实现测试
- 事件发布/订阅测试

## 运行命令

```bash
# 全量测试
./gradlew test

# 指定模块
./gradlew :taotao-cloud-goods-domain:test

# 覆盖率报告
./gradlew jacocoTestReport
```

## 覆盖率目标

| 层 | 目标 |
|----|------|
| Domain | >= 90% |
| Application | >= 80% |
| Infrastructure | >= 70% |
| Interfaces | >= 60% |
| 总覆盖率 | >= 80% |

## 禁止事项
- 禁止在测试中使用 `@DirtiesContext`（性能影响）
- 禁止删除失败的测试（应该修复）
- 禁止测试中硬编码敏感信息
