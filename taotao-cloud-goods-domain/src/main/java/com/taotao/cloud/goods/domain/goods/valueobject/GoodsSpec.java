package com.taotao.cloud.goods.domain.goods.valueobject;

import com.taotao.boot.ddd.model.domain.ValueObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 商品规格
 *
 * @author luohq
 * @date 2022-11-27 18:06
 */
public class GoodsSpec implements ValueObject {

    /**
     * 生产日期
     */
    @NotNull
    @PastOrPresent
    private LocalDate manufactureDate;

    /**
     * 过期日期
     */
    @NotNull
    private LocalDate expirationDate;

    /**
     * 商品重量
     */
    @NotNull
    private GoodsWeight goodsWeight;

    /**
     * 商品介绍
     */
    @NotBlank
    @Length(min = 1, max = 1024)
    private String goodsDesc;

    GoodsSpec() {
    }

    private GoodsSpec(Builder builder) {
        manufactureDate = builder.manufactureDate;
        expirationDate = builder.expirationDate;
        goodsWeight = builder.goodsWeight;
        goodsDesc = builder.goodsDesc;
        this.validateSelf();
    }

    public static Builder builder() {
        return new Builder();
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public GoodsWeight getGoodsWeight() {
        return goodsWeight;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsSpec goodsSpec = (GoodsSpec) o;
        return Objects.equals(manufactureDate, goodsSpec.manufactureDate)
                && Objects.equals(expirationDate, goodsSpec.expirationDate)
                && Objects.equals(goodsWeight, goodsSpec.goodsWeight)
                && Objects.equals(goodsDesc, goodsSpec.goodsDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufactureDate, expirationDate, goodsWeight, goodsDesc);
    }

    @Override
    public String toString() {
        return "GoodsSpec{" +
                "manufactureDate=" + manufactureDate +
                ", expirationDate=" + expirationDate +
                ", goodsWeight=" + goodsWeight +
                ", goodsDesc='" + goodsDesc + '\'' +
                '}';
    }

	@Override
	public boolean sameValueAs(Object other) {
		return false;
	}


	public static final class Builder {
        private LocalDate manufactureDate;
        private LocalDate expirationDate;
        private GoodsWeight goodsWeight;
        private String goodsDesc;

        private Builder() {
        }

        public Builder manufactureDate(LocalDate val) {
            manufactureDate = val;
            return this;
        }

        public Builder expirationDate(LocalDate val) {
            expirationDate = val;
            return this;
        }

        public Builder goodsWeight(GoodsWeight val) {
            goodsWeight = val;
            return this;
        }

        public Builder goodsDesc(String val) {
            goodsDesc = val;
            return this;
        }

        public GoodsSpec build() {
            return new GoodsSpec(this);
        }
    }
}
