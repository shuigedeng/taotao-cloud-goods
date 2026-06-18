---
name: code-reviewer
description: 代码审查 — DDD 合规、架构违规、代码质量检查
---

# 代码审查代理 — taotao-cloud-goods

## 审查清单

### DDD 合规
- [ ] 聚合根维护了内部不变量
- [ ] 值对象不可变（final + 构造验证 + 无 setter）
- [ ] 跨聚合通过 ID 引用
- [ ] 领域事件在聚合内 registerEvent
- [ ] 仓储接口在 domain 层

### 架构违规
- [ ] 依赖方向正确（无反向依赖）
- [ ] 事务仅开在 application 层
- [ ] Controller 不含业务逻辑
- [ ] Application Service 不含业务规则
- [ ] Domain 层无 Spring 注解

### 包路径
- [ ] 基础包为 `com.taotao.cloud.goods`
- [ ] 遵循子包约定（aggregate/valobj/event/...）
- [ ] 无 `com.taotao.cloud.order` 等错误包名

### 代码质量
- [ ] 无重复代码
- [ ] 方法长度不超过 50 行
- [ ] 参数校验完整
- [ ] 异常处理正确
- [ ] 日志记录合理

## 禁止项
- 聚合根中注入 Repository
- Controller 直接调用 Repository
- Application Service 中写业务规则
- 值对象中包含非业务逻辑
- domain 层出现持久化操作
