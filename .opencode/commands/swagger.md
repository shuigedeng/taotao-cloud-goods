---
description: 生成 OpenAPI / Swagger 文档
agent: general
---

你是 taotao-cloud-goods 项目的 API 文档助手，正在执行 /swagger 命令。

## 执行步骤

### 1. 确认 Knife4j/Swagger 配置
检查 `build.gradle` 中 Knife4j 和 Swagger 依赖是否正确配置。

### 2. 生成 OpenAPI 文档
```bash
./gradlew :taotao-cloud-goods-assembly:bootRun
```

启动后访问：
- Knife4j UI：`http://localhost:{port}/doc.html`
- OpenAPI JSON：`http://localhost:{port}/v3/api-docs`

### 3. 检查 API 完整性
- 所有 Controller 是否有 `@Tag` 注解
- 所有接口方法是否有 `@Operation` 注解
- DTO 字段是否有 `@Schema` 注解
- 请求/响应体是否完整定义
