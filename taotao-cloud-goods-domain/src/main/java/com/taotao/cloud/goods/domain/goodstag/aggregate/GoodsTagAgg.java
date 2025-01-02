package com.taotao.cloud.goods.domain.goodstag.aggregate;


import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.types.BizId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 商品标签
 *
 * @author luohq
 * @date 2023-08-02
 */

public class GoodsTagAgg extends AggregateRoot<BizId> {
    /**
     * 商品ID
     */
    @NotNull
    private BizId id;

    /**
     *标签名称
     */
    @NotBlank
    @Size(max = 64)
    private String tagName;

    /**
     *标签描述
     */
    @Size(max = 512)
    private String tagDesc;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    GoodsTagAgg() {
    }

    GoodsTagAgg(BizId id, String tagName, String tagDesc) {
        this.id = id;
        this.tagName = tagName;
        this.tagDesc = tagDesc;
        this.createTime = LocalDateTime.now();
        this.updateTime = this.createTime;
        this.validateSelf();
    }

    public GoodsTagAgg(BizId id, String tagName, String tagDesc, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.tagName = tagName;
        this.tagDesc = tagDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.validateSelf();
    }

    public void modifyBasicInfo(String tagName, String tagDesc) {
        this.tagName = tagName;
        this.tagDesc = tagDesc;
        this.updateTime = LocalDateTime.now();
        this.validateSelf();
    }

    @Override
    public BizId getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsTagAgg goodsTag = (GoodsTagAgg) o;
        return Objects.equals(id, goodsTag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GoodsTag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", tagDesc='" + tagDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
