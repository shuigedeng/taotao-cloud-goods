# PROJECT KNOWLEDGE BASE — taotao-cloud-goods

**Generated:** 2026-06-18
**Commit:** `56589ba`
**Branch:** (active branch)

## OVERVIEW

商品域 DDD 单体服务，基于 Spring Boot 4.1.1 / JDK 25 / Gradle 9.5。
严格遵循六边形架构 + 领域驱动设计，属于 taotao-cloud 微服务体系的商品领域服务。

核心业务：商品管理、分类管理、品牌管理、规格参数、标签管理、SKU 库存。

## PROJECT STRUCTURE

```
taotao-cloud-goods/
├── api/                        # RPC/gRPC 接口 + DTO（纯定义，无实现）
│   ├── rpc/query/             # Dubbo/gRPC 查询接口
│   ├── rpc/command/           # Dubbo/gRPC 命令接口
│   ├── rpc/dto/               # RPC 数据传输对象
│   ├── inner/query/           # 内部 API 查询接口
│   ├── inner/command/         # 内部 API 命令接口
│   ├── inner/dto/             # 内部 API 数据传输对象
│   └── dto/                   # 基础 DTO（BaseResponse, BaseQuery, BaseCommand）
├── application/                # 应用层：编排、事务、DTO 转换
│   ├── service/command/       # 命令服务（写操作）
│   ├── service/query/         # 查询服务（读操作）
│   ├── dto/command/           # 命令 DTO
│   ├── dto/query/             # 查询 DTO
│   ├── dto/result/            # 结果 DTO
│   └── assembler/             # 应用层 Assembler（DTO ↔ 领域对象）
├── assembly/                   # 启动器 + 环境配置
│   └── src/main/resources/
│       ├── config/            # 环境配置（dev/test/pre/pro）
│       ├── bootstrap.yml      # 启动配置
│       └── logback-spring.xml # 日志配置
├── common/                     # 公共工具、枚举、常量
│   └── src/main/java/com/taotao/cloud/goods/common/
│       ├── enums/             # 业务枚举
│       ├── constant/          # 业务常量
│       └── util/              # 工具类
├── domain/                     # ★ 领域层（零外部依赖）
│   ├── aggregate/             # 聚合根（GoodsAgg）
│   ├── entity/                # 实体（Category, Tag）
│   ├── valobj/                # 值对象（GoodsWeight, GoodsStatus, GoodsSpec, GoodsName, CategoryName, CategoryDesc, WeightUnit）
│   ├── event/                 # 领域事件（GoodsCreateEvent, CategoryCreateEvent, FreightTemplateChangedEvent, GoodsAggSnapshot）
│   ├── repository/            # 仓储接口
│   ├── service/               # 领域服务
│   ├── factory/               # 工厂（GoodsFactory, CategoryFactory, DraftGoodsFactory, GoodsTagFactory）
│   └── assembler/             # 领域层 Assembler
├── facade/                     # 防腐层（ACL）：适配外部系统
├── infrastructure/             # 持久化、MQ、事件、配置
│   ├── persistent/repository/ # 仓储实现（JPA/MyBatis-Plus）
│   ├── persistent/po/         # 持久化 PO
│   ├── persistent/assembler/  # PO ↔ 领域对象转换
│   ├── event/                 # 事件订阅/发布
│   ├── job/                   # 定时任务
│   ├── config/                # 基础设施配置
│   └── adapter/               # 外部适配器
└── interfaces/                 # REST / RPC / gRPC
    ├── controller/
    │   ├── buyer/             # 买家端
    │   ├── seller/            # 卖家端
    │   ├── manager/           # 管理端
    │   ├── inner/             # 内部 API
    │   └── open/              # 开放平台（如微信/支付宝通知）
    ├── rpc/                   # Dubbo RPC 实现
    └── grpc/                  # gRPC 实现
```

## PACKAGE BASE

```
com.taotao.cloud.goods
```

## DEPENDENCY FLOW

```
api (pure DTO) → interfaces → application → facade (ACL)
                         ↓
                    domain ← infrastructure
```

- `domain`: 零外部依赖，不依赖 Spring、不依赖数据库
- `application`: 依赖 `domain`，可依赖 `facade` 接口，不依赖 `infrastructure`
- `infrastructure`: 依赖 `domain` 实现仓储，依赖 `application` 实现事件订阅
- `interfaces`: 依赖 `application`，不直接依赖 `infrastructure`
- `api`: 纯 DTO + 接口定义，不依赖任何业务模块
- `facade`: 独立模块，适配外部系统（ACL）

## WHERE TO LOOK

| 任务 | 位置 |
|------|------|
| 新增业务功能 | `application/service/command/` 定义接口 → `interfaces/controller/` 实现 |
| 修改领域模型 | `domain/aggregate/` 或 `domain/entity/` |
| 值对象 | `domain/valobj/` — 所有字段 final，无 setter |
| 领域事件 | `domain/event/` — 聚合内 registerEvent，仓储 flush |
| 仓储实现 | `infrastructure/persistent/repository/` |
| API 定义 | `api/rpc/` 或 `api/inner/` |
| 外部接口适配 | `facade/` |
| 消息监听 | `infrastructure/event/` |
| 定时任务 | `infrastructure/job/` |
| REST Controller | `interfaces/controller/{buyer|seller|manager}/` |
| RPC 实现 | `interfaces/rpc/` |
| gRPC 实现 | `interfaces/grpc/` |

## CONVENTIONS

- 分层依赖方向：`interfaces → application → domain ← infrastructure`
- 跨聚合通过 ID 引用，非对象引用
- 事务边界仅开在 `application/` 层
- Controller 按角色 buyer / seller / manager 分包
- 命令/查询命名：`{动词}{名词}{Command|Query}`
- 领域模型与持久化模型分离（domain entity ≠ PO）
- 聚合根命名：`{Entity}Agg`（如 GoodsAgg）
- API 返回统一使用 `com.taotao.boot.common.model.result.Result<T>`
- Controller 继承 `com.taotao.boot.webagg.controller.BusinessController`

## ANTI-PATTERNS (THIS PROJECT)

- Controller 中写业务逻辑判断
- 聚合根中注入 Repository 或 Domain Service
- 值对象中包含业务行为以外的逻辑
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- domain 层出现 Spring 注解或持久化依赖

## UNIQUE STYLES

- **API/Interfaces 分离**：`api/` 模块只放接口定义和 DTO，`interfaces/` 模块放实现，区别于常规的单模块做法
- **Controller 按端分层**：buyer / seller / manager 三个子包，各端 API 完全隔离
- **事件驱动仓储**：聚合根内 `registerEvent()`，仓储 `save()` 时自动 flush 发布
- **防腐层独立为模块**：`facade/` 作为独立 gradle module，而非 application 的子包
- **MapStruct + Record Builder + Lombok 三件套**：减少样板代码的同时保持不可变性
- **多重 RPC 支持**：同时支持 Dubbo RPC 和 gRPC，接口定义在 `api/`，实现在 `interfaces/`

## DOMAIN MODEL OVERVIEW

```
Goods (Aggregate Root)
├── GoodsWeight (Value Object)
├── GoodsSpec (Value Object)
├── GoodsStatus (Value Object)
├── GoodsName (Value Object)
├── Category (Entity)
│   ├── CategoryName (Value Object)
│   ├── CategoryDesc (Value Object)
│   └── CategoryCreateEvent (Domain Event)
└── Tag (Entity)

Domain Events:
  - GoodsCreateEvent
  - CategoryCreateEvent
  - FreightTemplateChangedEvent
  - GoodsAggSnapshot

Factories:
  - GoodsFactory
  - CategoryFactory
  - DraftGoodsFactory
  - GoodsTagFactory
```

## KEY TECHNOLOGIES

| 技术 | 版本 |
|------|------|
| JDK | 25 (预览特性, `--enable-preview`) |
| Gradle | 9.5 |
| Spring Boot | 4.1.1 |
| Spring Cloud | 2025.1.1 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| MyBatis-Plus | 3.5.16 |
| MapStruct | 1.6.3 |
| Record Builder | 52 |
| Lombok | 1.18.46 |
| Knife4j | 4.5.0 |
| Swagger | 3.0.0 |
| Redisson | 4.3.1 |
| Hutool | 5.8.44 |

## COMMANDS

```bash
# 编译
./gradlew build

# 启动 dev 环境
./gradlew :taotao-cloud-goods-assembly:bootRun --args='--spring.profiles.active=dev'

# 代码质量检查
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 运行测试
./gradlew test

# 指定模块测试
./gradlew :taotao-cloud-goods-domain:test

# 发布到本地 Maven
./gradlew publishToMavenLocal

# JaCoCo 覆盖率报告
./gradlew jacocoTestReport
```

## NOTES

- JDK 25 预览特性，`--enable-preview` + 大量 `--add-exports`
- `taotao-cloud-dependencies:2026.10` BOM 未开源，外部构建需要私有仓库凭据
- 四个环境配置：dev / test / pre / pro
- 代码质量门禁：Checkstyle + SpotBugs + PMD + Spotless + OWASP
- Maven 仓库：阿里云私有仓库（需要凭据）+ GitHub Packages
- CI/CD：GitHub Actions + Jenkins + Rancher
