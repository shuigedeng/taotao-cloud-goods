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
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import java.util.Objects;

/** 商品计量单位表 */

@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
@Entity
@Table(name = GoodsTagPO.TABLE_NAME,
	indexes = {
		@Index(name = "idx_create_date", columnList = "`create_date`"),
		@Index(name = "idx_name", columnList = "`name`"),
	})
@TableName(GoodsTagPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = GoodsTagPO.TABLE_NAME)
public class GoodsTagPO extends BaseSuperEntity<GoodsTagPO, Long> {

    public static final String TABLE_NAME = "ttc_goods_tag";

    /** 计量单位名称 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '计量单位名称'")
    private String name;

	@Column(name = "`desc`", columnDefinition = "varchar(255) not null comment '计量单位名称'")
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        GoodsTagPO goodsUnitPO = (GoodsTagPO) o;
        return getId() != null && Objects.equals(getId(), goodsUnitPO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
