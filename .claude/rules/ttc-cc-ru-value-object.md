# 值对象设计规范 — taotao-cloud-goods

## 核心特性

### 1. 不可变性
- 所有字段声明为 `final`
- 无 setter 方法
- 构造时自验证

### 2. 无标识
- 通过属性值判断相等性
- 覆写 `equals()` / `hashCode()`（基于所有属性）

### 3. 行为内聚
- 包含与自身相关的业务方法
- 不包含持久化逻辑

## 代码规范

```java
@Embeddable
public class GoodsWeight {
    private final BigDecimal value;
    private final WeightUnit unit;

    public GoodsWeight(BigDecimal value, WeightUnit unit) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("商品重量必须为正数");
        }
        if (unit == null) {
            throw new DomainException("重量单位不能为空");
        }
        this.value = value;
        this.unit = unit;
    }

    // 业务行为
    public GoodsWeight convertTo(WeightUnit targetUnit) {
        if (this.unit == targetUnit) return this;
        // 单位转换逻辑
        BigDecimal converted = convert(this.value, this.unit, targetUnit);
        return new GoodsWeight(converted, targetUnit);
    }

    // 只有 getter，无 setter
    public BigDecimal getValue() { return value; }
    public WeightUnit getUnit() { return unit; }

    // JPA 要求
    protected GoodsWeight() {}

    @Override
    public boolean equals(Object o) { ... }
    @Override
    public int hashCode() { ... }
}
```

## 项目中的值对象

| 值对象 | 字段 | 包路径 |
|--------|------|--------|
| `GoodsWeight` | value, unit | `domain/valobj/` |
| `GoodsStatus` | 枚举 | `domain/valobj/` |
| `GoodsSpec` | specJson | `domain/valobj/` |
| `GoodsName` | value | `domain/valobj/` |
| `CategoryName` | value | `domain/valobj/` |
| `CategoryDesc` | value | `domain/valobj/` |
| `WeightUnit` | 枚举 | `domain/valobj/` |

## 禁止做法
```java
// ❌ 非 final 字段 + setter
private BigDecimal value;
public void setValue(BigDecimal value) { this.value = value; }

// ❌ 值对象中注入其他 bean
@Autowired private SomeService service;

// ❌ 构造时不验证
public GoodsWeight() {}
```
