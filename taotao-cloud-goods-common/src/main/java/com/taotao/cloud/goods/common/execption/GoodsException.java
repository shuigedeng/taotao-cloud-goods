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

package com.taotao.cloud.goods.common.execption;

import com.taotao.boot.common.enums.ResultEnum;
import com.taotao.boot.common.exception.BusinessException;

public class GoodsException extends BusinessException {

    public GoodsException(String message) {
        super(message);
    }

    public GoodsException(Integer code, String message) {
        super(code, message);
    }

    public GoodsException(String message, Throwable e) {
        super(message, e);
    }

    public GoodsException(Throwable e) {
        super(e);
    }

    public GoodsException(Integer code, String message, Throwable e) {
        super(code, message, e);
    }

    public GoodsException(ResultEnum result) {
        super(result);
    }

    public GoodsException(ResultEnum result, Throwable e) {
        super(result, e);
    }
}
