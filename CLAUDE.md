# taotao-cloud-goods — DDD 商品域单体服务

## 技术栈

- **JDK 25** (预览特性，`--enable-preview`)
- **Gradle 9.5** (构建工具)
- **Spring Boot 4.1.0** / **Spring Cloud 2025.1.1**
- **Spring Cloud Alibaba 2025.1.0.0** (Nacos, Sentinel, Seata)
- **MyBatis-Plus 3.5.16** / **JPA** (持久化)
- **MapStruct 1.6.3** + **Record Builder 52** + **Lombok 1.18.46** (代码生成)
- **Knife4j 4.5.0** (API 文档)
- **RocketMQ / Kafka** (消息)
- **Checkstyle + SpotBugs + PMD + Spotless + OWASP** (质量门禁)

## 项目结构

```
taotao-cloud-goods/           # 八模块 DDD 架构
├── api/                      # RPC/gRPC 接口 + DTO（纯定义）
├── application/              # 应用层：编排、事务、DTO 转换
├── assembly/                 # 启动器 + 环境配置
├── common/                   # 公共工具、枚举
├── domain/                   # ★ 领域层（零外部依赖）
├── facade/                   # 防腐层（ACL）
├── infrastructure/           # 持久化、MQ、事件
└── interfaces/               # REST / RPC / gRPC
```

## 基础包

```
com.taotao.cloud.goods
```

## 分层依赖方向

```
api → interfaces → application → facade
                    ↓
               domain ← infrastructure
```

- `domain`：零外部依赖，纯业务逻辑
- `application`：事务边界，编排领域对象
- `infrastructure`：实现仓储、事件订阅
- `interfaces`：Controller 按 buyer/seller/manager 分包
- `api`：纯接口 + DTO，不依赖业务模块

## 领域模型（key classes）

| 类型 | 类 | 位置 |
|------|-----|------|
| 聚合根 | `GoodsAgg` | `domain/aggregate/` |
| 实体 | `Category`, `Tag` | `domain/entity/` |
| 值对象 | `GoodsWeight`, `GoodsStatus`, `GoodsSpec`, `GoodsName`, `CategoryName`, `WeightUnit` | `domain/valobj/` |
| 领域事件 | `GoodsCreateEvent`, `CategoryCreateEvent`, `FreightTemplateChangedEvent` | `domain/event/` |
| 仓储接口 | `GoodsDomainRepository` | `domain/repository/` |
| 领域服务 | `GoodsDomainService` | `domain/service/` |
| 工厂 | `GoodsFactory`, `CategoryFactory`, `DraftGoodsFactory`, `GoodsTagFactory` | `domain/factory/` |

## 常用命令

```bash
# 编译
./gradlew build

# 启动（dev 环境）
./gradlew :taotao-cloud-goods-assembly:bootRun --args='--spring.profiles.active=dev'

# 运行测试
./gradlew test

# 代码质量检查
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 生成覆盖率报告
./gradlew jacocoTestReport

# 发布到本地 Maven
./gradlew publishToMavenLocal
```

## 全局编码约定

### 聚合根
- 命名：`{Entity}Agg`（如 `GoodsAgg`）
- 聚合内实体用对象引用，跨聚合用 ID 引用
- 聚合内 `registerEvent()`，仓储 `save()` 时 flush 发布

### 值对象
- 所有字段 `final`，无 setter
- 构造时自验证
- 覆写 `equals`/`hashCode`（基于所有属性）

### Controller
- 按角色分包：`buyer/`, `seller/`, `manager/`
- 继承 `BusinessController`
- 统一返回 `Result<T>`

### 命名
- 命令 DTO：`{动词}{名词}Command`
- 查询 DTO：`{名词}PageQuery`
- 结果 DTO：`{名词}Result`
- 命令服务：`{Entity}CommandService`
- 查询服务：`{Entity}QueryService`

### 依赖注入
- 使用构造器注入（`private final` + `@RequiredArgsConstructor`）
- 严禁在聚合根中注入 Repository
- 严禁在值对象中添加非业务逻辑

## 环境

| 环境 | 配置文件位置 |
|------|------------|
| dev | `assembly/src/main/resources/config/application-dev.yml` |
| test | `assembly/src/main/resources/config/application-test.yml` |
| pre | `assembly/src/main/resources/config/application-pre.yml` |
| pro | `assembly/src/main/resources/config/application-pro.yml` |

## 注意

- JDK 25 预览特性，大量 `--add-exports`
- `taotao-cloud-dependencies:2026.07` BOM 需私有仓库凭据
- 阿里云私有仓库发布需配置 `mavenUsernameNew` / `mavenPasswordNew`
