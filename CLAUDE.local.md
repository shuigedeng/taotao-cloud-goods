# 个人 DDD 开发配置

## 本地环境

- **操作系统**: Windows
- **IDE**: IntelliJ IDEA 2026.1
- **JDK**: graalvm-jdk-25
- **构建工具**: Gradle 9.5 (Wrapper)

## 本地 Maven 仓库

```
~/.gradle/caches/
```

## 个人偏好

- **测试优先**: 先写领域层单元测试
- **代码生成**: Lombok + MapStruct + Record Builder
- **调试**: 开启 SQL 日志 (logging.level.sql: DEBUG)

## 本地配置覆盖

```yaml
# 仅本地生效，不提交
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/taotao_cloud_goods?useSSL=false
    username: root
    password: local_dev_password
```

## 开发流程

1. `/propose` — 创建变更提案
2. `/apply` — 按 Spec 编码
3. `/review` — DDD 代码审查
4. `/fix` — 修正问题
5. `/test` — 运行测试
6. `/archive` — 归档变更
