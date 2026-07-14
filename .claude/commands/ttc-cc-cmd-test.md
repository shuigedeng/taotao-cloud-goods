---
description: 运行测试并生成 JaCoCo 覆盖率报告
---

# 测试执行

## 执行步骤

### 1. 运行测试
```bash
./gradlew test
```

如果指定模块：
```bash
./gradlew :taotao-cloud-goods-{module}:test
```

### 2. 生成覆盖率报告
```bash
./gradlew jacocoTestReport
```

### 3. 输出测试结果
- 测试总数：{total}
- 通过：{passed}
- 失败：{failed}
- 跳过：{skipped}

### 4. 如果测试失败
- 读取失败测试的源码
- 分析失败原因
- 报告修复建议

## 测试规范

- 领域层测试：纯 POJO，无 Spring 上下文
- 应用层测试：@SpringBootTest
- 单元测试覆盖率 >= 80%（JaCoCo）
