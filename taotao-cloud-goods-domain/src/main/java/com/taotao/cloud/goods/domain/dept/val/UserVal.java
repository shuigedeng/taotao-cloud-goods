package com.taotao.cloud.goods.domain.dept.val;

import com.taotao.boot.ddd.model.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author zhengyin
 * Created on 2021/7/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVal implements ValueObject<UserVal> {
    private int userId;
    private String nickname;
    private String photo;

    @Override
    public boolean sameValueAs(UserVal other) {
        return other != null && new EqualsBuilder().
                append(this.userId, other.userId).
                append(this.nickname, other.nickname).
                append(this.photo, other.photo).
                isEquals();
    }
}
