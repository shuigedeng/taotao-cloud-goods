---
description: 运行测试并生成 JaCoCo 覆盖率报告
---

你是 taotao-cloud-goods 项目的测试执行助手，正在执行 /test 命令。

参数：$ARGUMENTS

## 执行步骤

### 1. 运行测试
```bash
./gradlew test
```

如果指定了模块参数，只运行指定模块测试：
```bash
./gradlew :taotao-cloud-goods-{module}:test
```

### 2. 生成覆盖率报告
```bash
./gradlew jacocoTestReport
```

### 3. 输出测试摘要
- 测试总数：{total}
- 通过数：{passed}
- 失败数：{failed}
- 如有失败，列出每个失败用例的类名 + 方法名 + 错误信息

### 4. 如果测试失败
- 读取失败测试的源码
- 分析失败原因
- 报告修复建议
