# 架构规范 — taotao-cloud-goods

基础包：`com.taotao.cloud.goods`

## DDD 分层架构

```
api  ←  interfaces  →  application  →  facade
                          ↓
                     domain  ←  infrastructure
```

### 各层职责

#### Domain 层（零外部依赖）
- 聚合根、实体、值对象、领域事件
- 仓储接口、领域服务、工厂
- 不依赖 Spring、数据库、任何外部框架
- 纯业务逻辑

#### Application 层
- 命令服务（`@Transactional`）：编排领域对象
- 查询服务（`@Transactional(readOnly = true)`）
- DTO 转换（Assembler）
- 不含业务规则判断

#### Infrastructure 层
- 仓储实现（JPA / MyBatis-Plus）
- PO 与领域对象映射
- 事件订阅/发布
- 消息监听、定时任务、配置

#### Interfaces 层
- REST Controller：`controller/{buyer|seller|manager}/`
- RPC 实现：`rpc/`
- gRPC 实现：`grpc/`
- 仅做 HTTP 解析 + 参数校验 + Result 封装

#### API 层
- 纯接口定义 + DTO
- `rpc/query/`, `rpc/command/` — 远程 RPC 接口
- `inner/query/`, `inner/command/` — 内部 API 接口

#### Facade 层
- 防腐层（ACL）
- 适配外部系统

### 禁止违反的依赖

```java
// ❌ Controller 直接调用 Repository
@Autowired private GoodsDomainRepository repo;

// ❌ Application Service 直接调用 Mapper
@Autowired private GoodsMapper mapper;

// ❌ Domain Service 注入 Repository
@Autowired private GoodsDomainRepository repo;

// ✅ 正确：Application Service 通过仓储接口
private final GoodsDomainRepository goodsRepository;
```

### 分层检查清单
- [ ] Domain 层无 Spring 注解
- [ ] Application 层不含业务规则
- [ ] Controller 不含业务逻辑
- [ ] 跨聚合通过 ID 引用
- [ ] 事务仅开在 application 层
