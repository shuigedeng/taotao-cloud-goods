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
import com.taotao.boot.webagg.entity.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/** 自定义分词表 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = CustomWordsPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
        })
@TableName(CustomWordsPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = CustomWordsPO.TABLE_NAME)
public class CustomWordsPO extends BasePO<CustomWordsPO> {

    public static final String TABLE_NAME = "ttc_custom_words";

    /** 分词名称 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '分词名称'")
    private String name;

    /** 是否禁用 */
    @Column(name = "`disabled`", columnDefinition = "int null default 0 comment '是否禁用'")
    private Integer disabled;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name name
     * @return 无返回值
     * @since 2022.03
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getDisabled() {
        return disabled;
    }

    /**
     * 设置
     *
     * @param disabled disabled
     * @return 无返回值
     * @since 2022.03
     */
    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CustomWordsPO that = (CustomWordsPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
