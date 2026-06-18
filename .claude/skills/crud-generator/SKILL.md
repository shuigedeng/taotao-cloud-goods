---
name: crud-generator
description: 自动生成 DDD 分层 CRUD 代码（聚合根、值对象、仓储、应用服务、Controller）
triggers:
  - "生成CRUD"
  - "创建增删改查"
  - "新建模块"
  - "DDD 代码生成"
---

# CRUD 代码生成器 — taotao-cloud-goods

适用于商品域（Goods）及其相关子域（Category, Brand, Tag, Specification 等）。

## 工作流程

### 1. 收集信息
- 实体名称（如 Goods, Category, Brand）
- 核心字段列表
- 业务规则（状态流转、唯一约束等）

### 2. DDD 分层生成

按以下结构生成：

```
taotao-cloud-goods-{module}/
├── domain/
│   ├── aggregate/{Entity}Agg.java
│   ├── valobj/{Entity}Status.java
│   ├── event/{Entity}CreatedEvent.java
│   └── repository/{Entity}DomainRepository.java
├── application/
│   ├── dto/command/Create{Entity}Command.java
│   ├── dto/command/Update{Entity}Command.java
│   ├── dto/result/{Entity}Result.java
│   └── service/command/{Entity}CommandService.java
├── infrastructure/
│   └── persistent/
│       └── po/{Entity}Po.java
│       └── repository/{Entity}RepositoryImpl.java
└── interfaces/
    └── controller/{buyer|seller|manager}/{Entity}{Role}Controller.java
```

### 3. 代码模板

#### 聚合根
```java
@AggregateRoot
public class {Entity}Agg {
    @Id
    private Long id;
    protected {Entity}Agg() {}
    public static {Entity}Agg create() { ... }
}
```

#### 值对象
```java
public enum {Entity}Status {
    DRAFT("草稿"), PUBLISHED("已发布"), OFFLINE("已下架");
    private final String description;
}
```

#### 应用服务
```java
@ApplicationService
@Service
@Transactional
public class {Entity}CommandServiceImpl implements {Entity}CommandService {
    private final {Entity}DomainRepository repository;
}
```

### 4. 生成工具
```bash
python .opencode/skills/generate-crud.py --entity={Entity} --module={module}
```
