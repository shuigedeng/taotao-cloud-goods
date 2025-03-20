/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.goods.infrastructure.persistent.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 规格项表
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
@Entity
@Table(
        name = SpecificationPO.TABLE_NAME,
        uniqueConstraints = {
            @UniqueConstraint(name = "uniq_goods_no", columnNames = "goods_no"),
        },
        indexes = {
            @Index(name = "idx_create_date", columnList = "`create_date`"),
        })
@TableName(SpecificationPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = SpecificationPO.TABLE_NAME)
public class SpecificationPO extends BaseSuperEntity<SpecificationPO, Long> {

    /**
     * 规格项表
     */
    public static final String TABLE_NAME = "ttc_specification";

    /**
     * 规格名称
     */
    @Column(name = "`spec_name`", columnDefinition = "varchar(255) not null comment '会员规格名称ID'")
    private String specName;

    /**
     * 所属卖家 0属于平台
     *
     * <p>店铺自定义规格暂时废弃 2021-06-23 后续推出新配置方式
     */
    @Column(name = "`store_id`", columnDefinition = "bigint not null comment '所属卖家'")
    private Long storeId;

    /**
     * 规格值名字, 《,》分割
     */
    @Column(name = "`spec_value`", columnDefinition = "varchar(1024) not null comment '规格值名字'")
    private String specValue;

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        SpecificationPO that = (SpecificationPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
