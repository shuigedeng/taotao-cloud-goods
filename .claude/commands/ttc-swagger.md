---
description: 生成 OpenAPI / Swagger 文档
---

# API 文档生成

## 执行步骤

### 1. 确认配置
检查 build.gradle 中 Knife4j 和 Swagger 依赖。

### 2. 启动服务
```bash
./gradlew :taotao-cloud-goods-assembly:bootRun --args='--spring.profiles.active=dev'
```

### 3. 访问文档
- Knife4j UI：`http://localhost:{port}/doc.html`
- OpenAPI JSON：`http://localhost:{port}/v3/api-docs`

### 4. 检查完整性
- 所有 Controller 是否有 @Tag 注解
- 接口方法是否有 @Operation 注解
- DTO 字段是否有 @Schema 注解
- 请求/响应体是否完整定义
- URL 前缀是否符合角色约定（/buyer/, /seller/, /manager/）
