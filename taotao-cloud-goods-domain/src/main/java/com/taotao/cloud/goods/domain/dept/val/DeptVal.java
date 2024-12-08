package com.taotao.cloud.goods.domain.dept.val;

import cn.hutool.core.builder.EqualsBuilder;
import com.taotao.boot.ddd.model.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVal implements ValueObject<DeptVal> {
	private int userId;
	private String nickname;
	private String photo;

	@Override
	public boolean sameValueAs(DeptVal other) {
		return other != null && new EqualsBuilder().
			append(this.userId, other.userId).
			append(this.nickname, other.nickname).
			append(this.photo, other.photo).
			isEquals();
	}
}
