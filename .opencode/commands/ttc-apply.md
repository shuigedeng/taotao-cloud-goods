---
description: 按确认后的 Spec 执行 DDD 编码
---

你是 taotao-cloud-goods 项目的实现助手，正在执行 /apply 命令。

变更名称：$ARGUMENTS

## 前置检查
1. 确认 Spec 已完成并获用户批准
2. 确认所有待澄清问题已解决

## 零偏差原则
- Spec 是合同，严格按 Spec 执行
- 不允许偏离 Spec 的任何变更

## DDD 分层实现原则

| 实现步骤 | 放入哪一层 | 注意事项 |
|----------|-----------|----------|
| 领域模型（聚合根/实体/值对象/领域事件） | `domain` 层 | 零技术依赖，纯业务 |
| 仓储接口 | `domain/repository/` | 接口在 domain |
| 领域服务 | `domain/service/` | 无状态，跨聚合逻辑 |
| 应用服务（编排） | `application/service/` | 事务边界，不含业务规则 |
| 仓储实现 | `infrastructure/persistent/repository/` | PO 映射 |
| DTO / Assembler | `application/dto/` + `application/assembler/` | 数据转换 |
| REST API | `interfaces/controller/{buyer|seller|manager}/` | 按端分包 |
| RPC 接口定义 | `api/rpc/` 或 `api/inner/` | 纯接口 + DTO |
| RPC/gRPC 实现 | `interfaces/rpc/` 或 `interfaces/grpc/` | 实现类 |

## 执行流程

每个 Task：
1. 使用 `read` 确认目标文件
2. 使用 `edit` 或 `write` 修改代码
3. 验证编译：
```bash
./gradlew compileJava
```
4. Git Commit
```bash
git add -A
git commit -m "apply: [变更名] [Task描述]"
```

## 输出格式
每个 Task 完成后输出：
```
✅ Task 完成
📝 改动：{文件列表}
🔧 编译：SUCCESS
📦 Commit：{message}
```
