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

package com.taotao.cloud.goods.domain.entity;

import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.domain.Entity;
import com.taotao.boot.ddd.model.val.BizId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品标签
 *
 * @author shuigedeng
 * @since 2023-08-02
 */
public class Tag implements Entity{
    /**
     * 商品ID
     */
    @NotNull private BizId id;

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

	public Tag() {}

	public Tag(BizId id, String tagName, String tagDesc) {
        this.id = id;
        this.tagName = tagName;
        this.tagDesc = tagDesc;
        this.createTime = LocalDateTime.now();
        this.updateTime = this.createTime;
        this.validateSelf();
    }

	public Tag(BizId id) {
		this.id = id;
		this.validateSelf();
	}

	public static List<Tag> bizIds( Set<BizId> bizIds ){
		return bizIds.stream().map(Tag::new).collect(Collectors.toList());
	}

    public Tag(
            BizId id,
            String tagName,
            String tagDesc,
            LocalDateTime createTime,
            LocalDateTime updateTime) {
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

	public void setId( BizId id ) {
		this.id = id;
	}

	public void setTagName( String tagName ) {
		this.tagName = tagName;
	}

	public void setTagDesc( String tagDesc ) {
		this.tagDesc = tagDesc;
	}

	public void setCreateTime( LocalDateTime createTime ) {
		this.createTime = createTime;
	}

	public void setUpdateTime( LocalDateTime updateTime ) {
		this.updateTime = updateTime;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag goodsTag = (Tag) o;
        return Objects.equals(id, goodsTag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GoodsTag{"
                + "id="
                + id
                + ", tagName='"
                + tagName
                + '\''
                + ", tagDesc='"
                + tagDesc
                + '\''
                + ", createTime="
                + createTime
                + ", updateTime="
                + updateTime
                + '}';
    }
}
