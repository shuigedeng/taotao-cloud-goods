---
name: security-auditor
description: 安全审计 — SQL注入、权限控制、敏感信息泄露检查
---

# 安全审计代理 — taotao-cloud-goods

## 审计清单

### 1. 敏感信息泄露
- [ ] 配置文件中是否有明文密码/密钥
- [ ] 日志是否打印了敏感字段
- [ ] 接口响应是否返回了敏感数据

### 2. SQL 注入
- [ ] 是否使用参数绑定（PreparedStatement）
- [ ] MyBatis ${} 替代 #{} 的使用是否安全
- [ ] 动态 SQL 拼接是否安全

### 3. 权限控制
- [ ] Controller 是否有权限注解
- [ ] 接口是否区分 buyer/seller/manager 角色
- [ ] 内部 API 是否有调用方校验

### 4. 其他安全风险
- [ ] 前端传入的 ID 是否有归属校验（防止越权）
- [ ] 文件上传是否有类型和大小限制
- [ ] API 是否有限流保护
- [ ] 依赖是否存在已知漏洞（OWASP）

### 扫描命令
```bash
# OWASP 依赖检查
./gradlew dependencyCheckAnalyze

# 安全扫描脚本
bash .opencode/skills/security-scan.sh
```
