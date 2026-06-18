# 代码风格规范 — taotao-cloud-goods

## 格式化规则

- 缩进：4 个空格（不使用 Tab）
- 行宽：120 字符
- 大括号：K&R 风格（左括号不换行）
- 编码：UTF-8

## 包命名

```
com.taotao.cloud.goods.{layer}.{subdomain}
```

| 层 | 子包示例 |
|----|---------|
| domain | `domain/aggregate/`, `domain/entity/`, `domain/valobj/`, `domain/event/` |
| application | `application/service/command/`, `application/dto/result/` |
| infrastructure | `infrastructure/persistent/repository/`, `infrastructure/event/` |
| interfaces | `interfaces/controller/buyer/`, `interfaces/rpc/`, `interfaces/grpc/` |
| api | `api/rpc/query/`, `api/inner/command/` |

## 类命名

| 元素 | 命名规则 | 示例 |
|------|---------|------|
| 聚合根 | `{Entity}Agg` | `GoodsAgg` |
| 实体 | 业务名词 | `Category`, `Tag` |
| 值对象 | 描述性名词 | `GoodsWeight`, `GoodsStatus`, `GoodsName` |
| 领域事件 | `{名词}{过去式}Event` | `GoodsCreateEvent`, `CategoryCreateEvent` |
| 仓储接口 | `{Entity}DomainRepository` | `GoodsDomainRepository` |
| 领域服务 | `{Entity}DomainService` | `GoodsDomainService` |
| 命令 DTO | `{动词}{名词}Command` | `CreateGoodsCommand` |
| 查询 DTO | `{名词}PageQuery` | `GoodsPageQuery` |
| 结果 DTO | `{名词}Result` | `GoodsDetailResult` |
| 命令服务接口 | `{Entity}CommandService` | `GoodsCommandService` |
| 查询服务接口 | `{Entity}QueryService` | `GoodsQueryService` |

## Lombok 使用规范

```java
@Data             // 仅用于 PO/DTO（简单数据载体）
@Builder          // 构建复杂对象
@Slf4j            // 日志
@RequiredArgsConstructor  // 构造器注入
@Getter           // 值对象
```

## MapStruct + Record Builder 规范

```java
// Assembler: PO ←→ Domain 映射
@Mapper(componentModel = "spring")
public interface GoodsAssembler {
    GoodsPo toPo(GoodsAgg goods);
    GoodsAgg toDomain(GoodsPo po);
}

// Record Builder: 不可变 DTO
public record CreateGoodsCommand(
    @NotBlank String name,
    @NotNull BigDecimal price
) {}
```

## 导入顺序

1. Java 标准库 (`java.*`, `javax.*`, `jakarta.*`)
2. 第三方库 (`org.*`, `com.*`)
3. Spring 框架 (`org.springframework.*`)
4. 项目内部包 (`com.taotao.cloud.goods.*`)
5. 静态导入
