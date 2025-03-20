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

package com.taotao.cloud.goods.common.constant;

/**
 * 国际化使用到的 redis 缓存key
 *
 * @author hccake
 */
public final class I18nRedisKeyConstants {

    private I18nRedisKeyConstants() {}

    /**
     * i18nData 的数据存储缓存 key
     * <ul>
     * <li>type: String</li>
     * <li>fullKey: prefix:code:languageTag</li>
     * <ul/>
     */
    public static final String I18N_DATA_PREFIX = "i18n-data";

    /**
     * 删除 i18n data 消息的 channel 名
     */
    public static final String CHANNEL_I18N_DATA_UPDATED = "channel:i18n-data-updated";
}
