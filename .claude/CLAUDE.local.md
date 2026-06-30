# Local — taotao-cloud-goods

> 个人本地配置，不提交到 git。

## 本地环境

- **操作系统**: Windows
- **IDE**: IntelliJ IDEA 2026.1
- **JDK**: graalvm-jdk-25
- **构建**: Gradle 9.5 (Wrapper)

## 本地 Maven 仓库

```
C:/Users/Lenovo/.gradle/caches/
```

## 本地数据库

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/taotao_cloud_goods?useSSL=false
    username: root
    password: local_dev_password
```

## 个人偏好

- 先写领域层单元测试，再写应用层集成测试
- 使用 Lombok + MapStruct + Record Builder 生成样板代码
- 调试时开启 SQL 日志（`logging.level.sql: DEBUG`）
- 代码提交前运行 `./gradlew spotlessCheck` 确保格式一致

## 开发流程

1. `/ttc-propose` — 创建变更提案
2. `/ttc-apply` — 按 Spec 编码
3. `/ttc-review` — DDD 审查
4. `/ttc-fix` — 修正审查问题
5. `/ttc-test` — 运行测试
6. `/ttc-archive` — 归档变更
