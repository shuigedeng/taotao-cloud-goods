---
description: 部署应用到指定环境（dev/test/pre/pro）
---

# 部署流程

## 前置检查
1. 运行测试：`./gradlew test`
2. 代码质量：`./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain`
3. 测试失败或质量门禁不通过则中止部署

## 打包
```bash
./gradlew :taotao-cloud-goods-assembly:bootJar
```

## 启动
```bash
java --enable-preview \
  -jar taotao-cloud-goods-assembly/build/libs/taotao-cloud-goods-assembly-*.jar \
  --spring.profiles.active={environment}
```

## 健康检查
```bash
curl -f http://localhost:{port}/actuator/health
```

## 部署报告
- 环境：{environment}
- JAR 大小：{size}
- 健康检查：PASS/FAIL
