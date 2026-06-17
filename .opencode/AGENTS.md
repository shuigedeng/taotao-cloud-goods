# PROJECT KNOWLEDGE BASE

**Generated:** 2026-06-17
**Commit:** `56589ba`
**Branch:** (active branch)

## OVERVIEW

订单领域 DDD 单体服务，基于 Spring Boot 4.1.0 / JDK 25 / Gradle 9.5。严格遵循六边形架构 + 领域驱动设计。

## STRUCTURE

```
.opencode/
├── commands/        # 工作流命令（9个）
├── instructions/    # 编码规范
├── skills/          # 技能脚本
├── AGENTS.md        # 代理知识库
└── opencode.json    # OpenCode 配置
```

```
taotao-cloud-goods/
├── api/               # RPC/gRPC 接口 + DTO
├── application/       # 应用层：编排、事务、DTO转换
├── assembly/          # 启动器 + 环境配置
├── common/            # 公共工具、枚举
├── domain/            # ★ 领域层（零外部依赖）
├── facade/            # 防腐层（ACL）
├── infrastructure/    # 持久化、MQ、事件
└── interfaces/        # REST / RPC / gRPC
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| 新增业务功能 | `application/service/command/` 定义接口 + `interfaces/controller/` |
| 修改领域模型 | `domain/aggregate/` 或 `domain/entity/` |
| 值对象 | `domain/valobj/` — 所有字段 final，无 setter |
| 领域事件 | `domain/event/` — 聚合内 registerEvent，仓储 flush |
| 仓储实现 | `infrastructure/persistent/repository/` |
| API 定义 | `api/rpc/` 或 `api/inner/` |
| 外部接口适配 | `facade/` |
| 消息监听 | `infrastructure/event/` |
| 定时任务 | `infrastructure/job/` |

## CONVENTIONS

- 分层依赖方向：`interfaces → application → domain ← infrastructure`
- 跨聚合通过 ID 引用，非对象引用
- 事务边界仅开在 `application/` 层
- Controller 按角色 buyer / seller / manager 分包
- 命令/查询命名：`{动词}{名词}{Command|Query}`
- 领域模型与持久化模型分离（domain entity ≠ PO）

## ANTI-PATTERNS (THIS PROJECT)

- Controller 中写业务逻辑判断
- 聚合根中注入 Repository 或 Domain Service
- 值对象中包含业务行为以外的逻辑
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态

## UNIQUE STYLES

- **API/Interfaces 分离**：`api/` 模块只放接口定义和 DTO，`interfaces/` 模块放实现，区别于常规的单模块做法
- **Controller 按端分层**：buyer / seller / manager 三个子包，各端 API 完全隔离
- **事件驱动仓储**：聚合根内 `registerEvent()`，仓储 `save()` 时自动 flush 发布
- **防腐层独立为模块**：`facade/` 作为独立 gradle module，而非 application 的子包
- **MapStruct + Record Builder + Lombok 三件套**：减少样板代码的同时保持不可变性

## COMMANDS

```bash
gradlew build                              # 编译
gradlew :...assembly:bootRun --args='--spring.profiles.active=dev'  # 启动dev
gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain          # 质量检查
gradlew test                               # 测试
gradlew publishToMavenLocal                # 发布到本地
```

## NOTES

- JDK 25 预览特性，`--enable-preview` + 大量 `--add-exports`
- `taotao-cloud-dependencies:2026.07` BOM 未开源，外部构建需要私有仓库凭据
- 四个环境配置：dev / test / pre / pro
- 代码质量门禁：Checkstyle + SpotBugs + PMD + Spotless + OWASP
