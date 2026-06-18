# API 设计规范 — taotao-cloud-goods

## RESTful 约定

### URL 前缀按角色分包

| 角色 | 前缀 | 示例 Controller |
|------|------|----------------|
| 买家端 | `/buyer/goods/...` | `GoodsBuyerController` |
| 卖家端 | `/seller/goods/...` | `GoodsSellerController` |
| 管理端 | `/manager/goods/...` | `GoodsManagerController` |
| 内部 API | `/inner/goods/...` | `GoodsCommandApiController` |
| 开放平台 | `/open/...` | `AlipayNotifyController` |

### HTTP 方法

| 方法 | 用途 | 示例 |
|------|------|------|
| GET | 查询 | `/seller/goods/page` |
| POST | 创建 | `/seller/goods` |
| PUT | 全量更新 | `/seller/goods/{id}` |
| DELETE | 删除 | `/seller/goods/{id}` |

### 统一响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": {},
  "timestamp": "2026-06-18T00:00:00Z"
}
```

```java
// Controller 统一返回
@GetMapping("/page")
public Result<PageResult<GoodsSimpleResult>> page(GoodsPageQuery query) {
    return Result.success(goodsQueryService.pageQuery(query));
}

@PostMapping
public Result<GoodsDetailResult> create(@Valid @RequestBody CreateGoodsCommand command) {
    return Result.success(goodsCommandService.create(command));
}
```

### Controller 规范

```java
@RestController
@RequestMapping("/seller/goods")
public class GoodsSellerController extends BusinessController {
    // 严禁业务逻辑
    // 只做：HTTP 解析 + 参数校验 + Result 封装
}
```

### 参数校验

```java
public record CreateGoodsCommand(
    @NotBlank(message = "商品名称不能为空")
    String name,

    @NotNull(message = "商品价格不能为空")
    @DecimalMin(value = "0.01", message = "价格必须大于0")
    BigDecimal price,

    @NotNull(message = "分类不能为空")
    Long categoryId
) {}
```

### 命名规范

| 元素 | 命名 | 示例 |
|------|------|------|
| 命令 DTO | `{动词}{名词}Command` | `CreateGoodsCommand` |
| 查询参数 | `{名词}PageQuery` | `GoodsPageQuery` |
| 响应 DTO | `{名词}Result` | `GoodsDetailResult` |
| 基础 DTO | `BaseQuery`, `BaseCommand`, `BaseResponse` | `api/dto/` |
