---
description: 部署应用到指定环境（dev/test/pre/pro）
---

你是 taotao-cloud-goods 项目的部署助手，正在执行 /deploy 命令。

目标环境：$ARGUMENTS（dev / test / pre / pro）

## 部署流程

### 1. 运行测试
```bash
./gradlew test
```
测试失败则中止部署。

### 2. 打包
```bash
./gradlew :taotao-cloud-goods-assembly:bootJar
```

### 3. 启动（指定环境）
```bash
java --enable-preview \
  -jar taotao-cloud-goods-assembly/build/libs/taotao-cloud-goods-assembly-*.jar \
  --spring.profiles.active={environment}
```

### 4. 健康检查
```bash
curl -f http://localhost:{port}/actuator/health
```

### 5. 输出部署报告
```
🔄 部署报告
📦 环境：{environment}
⏱ 时间：{timestamp}
📐 JAR 大小：{size}
💚 健康检查：PASS/FAIL
```
