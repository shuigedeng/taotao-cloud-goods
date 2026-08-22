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

package com.taotao.cloud.goods.application.command;

import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.service.command.GoodsGalleryCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商品相册命令服务集成测试
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:12
 */
@DisplayName("商品相册命令服务集成测试")
class GoodsGalleryCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private GoodsGalleryCommandService goodsGalleryCommandService;

    @Nested
    @DisplayName("添加商品相册")
    class Add {

        @Test
        @DisplayName("正常添加商品相册")
        void shouldAddGoodsGallery() {
            List<String> galleryList = List.of("image1.jpg", "image2.jpg");
            boolean result = goodsGalleryCommandService.add(galleryList, 1L);
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("空相册列表时返回成功")
        void shouldReturnSuccessWithEmptyList() {
            boolean result = goodsGalleryCommandService.add(List.of(), 1L);
            assertThat(result).isTrue();
        }
    }
}
