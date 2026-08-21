# taotao-cloud-goods

DDD 商品域单体服务 — 基于 Spring Boot 4.1 / Spring Cloud Alibaba 2025.1。

> 详细架构规范参见 @.claude/rules/ttc-architecture.md
> 代码风格规范参见 @.claude/rules/ttc-code-style.md
> 测试规范参见 @.claude/rules/ttc-testing.md
> API 设计规范参见 @.claude/rules/ttc-api-conventions.md
> 聚合设计规范参见 @.claude/rules/ttc-aggregate-design.md
> 领域服务规范参见 @.claude/rules/ttc-domain-service.md
> 值对象规范参见 @.claude/rules/value-object.md

---

## 技术栈

- **JDK 25**（预览特性，`--enable-preview`）
- **Gradle 9.5**（构建工具）
- **Spring Boot 4.1.1** / **Spring Cloud 2025.1.1**
- **Spring Cloud Alibaba 2025.1.0.0**（Nacos, Sentinel, Seata）
- **MyBatis-Plus 3.5.16** / **JPA**（持久化）
- **MapStruct 1.6.3** + **Record Builder** + **Lombok 1.18.46**
- **Knife4j 4.5.0**（API 文档）
- **RocketMQ / Kafka**（消息）
- **Checkstyle + SpotBugs + PMD + Spotless + OWASP**（质量门禁）

## 项目结构

```
taotao-cloud-goods/
├── api/                  # RPC / gRPC 接口 + DTO（纯定义）
├── application/          # 应用服务：命令 + 查询 + DTO 转换
├── assembly/             # 启动器 + 环境配置
├── common/               # 公共工具、枚举
├── domain/               # ★ 领域层（零外部依赖）
├── facade/               # 防腐层（ACL）
├── infrastructure/       # 持久化、MQ、事件发布
└── interfaces/           # REST Controller（buyer/seller/manager/inner/open）
```

分层依赖方向：`domain` 不依赖任何外部框架，`infrastructure` 依赖 `domain`，`application` 编排领域对象。

## 常用命令

```bash
# 编译
./gradlew build

# 启动（dev 环境）
./gradlew :taotao-cloud-goods-assembly:bootRun --args='--spring.profiles.active=dev'

# 运行所有测试
./gradlew test

# 指定模块测试
./gradlew :taotao-cloud-goods-domain:test

# 代码质量检查
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 覆盖率报告
./gradlew jacocoTestReport

# 本地 Maven 发布
./gradlew publishToMavenLocal
```

## 全局约定

### 命名规则
- 命令 DTO：`{动词}{名词}Command`（如 `CreateGoodsCommand`）
- 查询 DTO：`{名词}PageQuery`（如 `GoodsPageQuery`）
- 结果 DTO：`{名词}Result`（如 `GoodsResult`）
- 命令服务：`{Entity}CommandService`
- 查询服务：`{Entity}QueryService`
- 聚合根：`{Entity}Agg`

### 依赖注入
- 使用 `private final` + `@RequiredArgsConstructor` 构造器注入
- 严禁在聚合根中注入 Repository
- 严禁在值对象中添加非业务逻辑

### Controller
- 按角色分包：`buyer/`, `seller/`, `manager/`, `inner/`, `open/`
- 继承 `BusinessController`（inner 层继承 `InnerController`）
- 统一返回 `Result<T>`（inner 层返回 `Response<T>`）

## 环境配置

| 环境 | 配置文件 |
|------|---------|
| dev | `assembly/src/main/resources/config/application-dev.yml` |
| test | `assembly/src/main/resources/config/application-test.yml` |
| pre | `assembly/src/main/resources/config/application-pre.yml` |
| pro | `assembly/src/main/resources/config/application-pro.yml` |

## 可用命令

- `/ttc-test` — 运行测试 + 覆盖率报告
- `/ttc-review` — DDD 代码审查
- `/ttc-deploy` — 部署到 dev/test/pre/pro
- `/ttc-swagger` — 生成 OpenAPI 文档

## 可用 Agents

- `ttc-domain-expert` — DDD 领域专家（澄清需求、验证领域模型）
- `ttc-aggregate-designer` — 聚合设计（聚合根、实体、值对象建模）
- `ttc-backend-architect` — 后端架构师（六边形架构、分层依赖）
- `ttc-code-reviewer` — 代码审查（DDD 合规、架构违规）
- `ttc-db-expert` — 数据库专家（表设计、索引优化、查询性能）
- `ttc-security-auditor` — 安全审计（SQL 注入、权限控制）

## 注意事项

- JDK 25 预览特性需 `--enable-preview`，大量 `--add-exports` JVM 参数
- `taotao-cloud-dependencies:2026.09` BOM 需私有仓库凭据
- 阿里云私有仓库发布需配置 `mavenUsernameNew` / `mavenPasswordNew`
- 使用 `./gradlew` 而非 `gradle` 命令（Wrapper 确保版本一致）
