#!/bin/bash
# taotao-cloud-goods 安全扫描脚本
# 用于检查 DDD 项目中的常见安全问题

echo "================================================"
echo "  taotao-cloud-goods 安全扫描"
echo "================================================"

# 扫描配置
BASE_DIR="."
MODULES=("taotao-cloud-goods-api" "taotao-cloud-goods-application" "taotao-cloud-goods-domain" "taotao-cloud-goods-infrastructure" "taotao-cloud-goods-interfaces")
HAS_WARNINGS=0

# 1. 检查敏感信息泄露
echo ""
echo "[1/4] 检查敏感信息泄露..."
for module in "${MODULES[@]}"; do
    if [ -d "$BASE_DIR/$module/src/main/resources" ]; then
        found=$(grep -r "password\|secret\|token\|jwt-secret\|access-key" \
            "$BASE_DIR/$module/src/main/resources" \
            --include="*.yml" --include="*.yaml" --include="*.properties" \
            2>/dev/null | grep -v "ENC(" | grep -v "#" || true)
        if [ -n "$found" ]; then
            echo "  ⚠️  发现明文敏感信息 in $module:"
            echo "$found"
            HAS_WARNINGS=1
        fi
    fi
done
if [ $HAS_WARNINGS -eq 0 ]; then
    echo "  ✅ 未发现明文敏感信息"
fi

# 2. 检查 SQL 注入风险
echo ""
echo "[2/4] 检查 SQL 注入风险..."
found_sql=0
for module in "${MODULES[@]}"; do
    if [ -d "$BASE_DIR/$module/src/main/java" ]; then
        found=$(grep -rn "nativeQuery\|createNativeQuery\|sql:\|@Select\|@Update\|@Delete\|@Insert" \
            "$BASE_DIR/$module/src/main/java" \
            --include="*.java" 2>/dev/null || true)
        if [ -n "$found" ]; then
            echo "  ℹ️  发现 SQL 注解 in $module（确认使用参数绑定）"
            found_sql=1
        fi
    fi
done
echo "  ✅ SQL 注入检查完成"

# 3. 检查权限控制
echo ""
echo "[3/4] 检查权限控制注解..."
found_auth=0
for module in "${MODULES[@]}"; do
    if [ -d "$BASE_DIR/$module/src/main/java" ]; then
        count=$(grep -rn "@PreAuthorize\|@Secured" \
            "$BASE_DIR/$module/src/main/java" \
            --include="*.java" 2>/dev/null | wc -l || echo 0)
        if [ "$count" -gt 0 ]; then
            echo "  ✅ $module: 发现 $count 处权限控制"
            found_auth=1
        fi
    fi
done
if [ $found_auth -eq 0 ]; then
    echo "  ⚠️  未发现权限控制注解"
    HAS_WARNINGS=1
fi

# 4. 检查跨聚合直接访问数据表
echo ""
echo "[4/4] 检查跨聚合数据访问..."
for module in "${MODULES[@]}"; do
    if [ -d "$BASE_DIR/$module/src/main/java" ]; then
        found=$(grep -rn "\.save\|\.update\|\.delete\|\.findById\|@Table\|@Entity" \
            "$BASE_DIR/$module/src/main/java/com/taotao/cloud/goods/domain" \
            --include="*.java" 2>/dev/null || true)
        if [ -n "$found" ]; then
            echo "  ⚠️  domain 层发现持久化操作（违反 DDD 规范）"
            echo "$found"
            HAS_WARNINGS=1
        fi
    fi
done

echo ""
echo "================================================"
if [ $HAS_WARNINGS -eq 0 ]; then
    echo "  ✅ 安全扫描通过，未发现问题"
else
    echo "  ⚠️  扫描完成，存在需要关注的问题"
fi
echo "================================================"
