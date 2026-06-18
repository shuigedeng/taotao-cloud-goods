---
name: security-review
description: 安全审查工作流，检查 SQL 注入、越权、敏感信息泄露、依赖漏洞
triggers:
  - "安全审查"
  - "漏洞扫描"
  - "安全审计"
  - "渗透测试"
---

# 安全审查工作流 — taotao-cloud-goods

适用于商品域（Goods）的安全审查，涵盖常见 Web 安全风险。

## 检查清单

### 1. SQL 注入

#### 检查点
- [ ] MyBatis 中是否使用 `${}` 替代 `#{}` 拼接参数
- [ ] 动态 SQL 是否使用 MyBatis-Plus 的 `Wrapper` 而非字符串拼接
- [ ] 排序字段是否做白名单校验（防止 `ORDER BY` 注入）

```java
// ❌ 危险：字符串拼接
String sql = "SELECT * FROM t_goods WHERE name LIKE '%" + keyword + "%'";

// ✅ 安全：参数绑定
LambdaQueryWrapper<GoodsPo> wrapper = Wrappers.lambdaQuery();
wrapper.like(GoodsPo::getName, keyword);

// ❌ 危险：排序字段未校验
queryWrapper.orderBy(true, isAsc, sortField); // sortField 来自前端

// ✅ 安全：白名单校验排序字段
private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "create_time", "price", "sales");
if (!ALLOWED_SORT_FIELDS.contains(sortField)) {
    sortField = "id"; // 默认排序
}
```

### 2. 越权访问

#### 检查点
- [ ] 卖家只能操作自己的商品（`storeId` 归属校验）
- [ ] 买家只能查看已发布的商品
- [ ] 内部 API 是否有调用方校验（`@Inner` 或 IP 白名单）
- [ ] 管理员接口是否区分角色（`@PreAuthorize`）

```java
// ❌ 危险：未校验卖家归属
@PostMapping("/{id}/publish")
public Result<Void> publish(@PathVariable Long id) {
    goodsCommandService.publish(id); // 任何卖家都可以发布其他卖家的商品
    return Result.success();
}

// ✅ 安全：校验卖家归属
@PostMapping("/{id}/publish")
public Result<Void> publish(@PathVariable Long id) {
    Long storeId = SecurityUtils.getCurrentUser().getStoreId();
    goodsCommandService.publish(id, storeId); // 传入 storeId 做归属校验
    return Result.success();
}
```

### 3. 敏感信息泄露

#### 检查点
- [ ] 配置文件是否有明文密码/密钥（应使用环境变量或配置中心）
- [ ] 接口响应是否返回了敏感字段（密码、手机号、token）
- [ ] 日志是否打印了敏感数据

```java
// ❌ 危险：日志打印敏感信息
log.info("用户登录成功：{}", user); // user.toString() 可能包含密码

// ✅ 安全：只打印必要信息
log.info("用户登录成功：userId={}", user.getId());

// ❌ 危险：接口返回敏感字段
public class UserResult {
    private String password; // 返回给前端
    private String phone;    // 应脱敏
}

// ✅ 安全：DTO 不包含敏感字段或做脱敏
public class UserResult {
    @JsonIgnore
    private String password;
    private String phone; // 或使用 @Sensitive 注解脱敏
}
```

### 4. 文件上传安全

- [ ] 文件类型是否做白名单校验（不依赖 `Content-Type`）
- [ ] 文件大小是否有限制
- [ ] 上传路径是否防止路径穿越（`../` 攻击）

### 5. 其他安全风险

- [ ] API 是否有限流保护（Sentinel）
- [ ] 用户输入是否做 XSS 过滤
- [ ] 依赖是否存在已知漏洞（OWASP Dependency Check）
- [ ] 前端传入 ID 是否有归属校验（防止水平越权）
- [ ] 接口是否区分 buyer/seller/manager 角色

## 扫描命令

```bash
# OWASP 依赖漏洞检查
./gradlew dependencyCheckAnalyze

# Checkstyle + SpotBugs + PMD 全量检查
./gradlew checkstyleMain spotbugsMain pmdMain
```

## 输出格式

```
🔒 安全审查报告

✅ 通过：
- [项目]

⚠️ 警告：
- [项目]

❌ 漏洞：
- [严重度] [位置] [风险描述]

💡 修复建议：
- [建议]
```
