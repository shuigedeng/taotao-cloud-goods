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

import static org.assertj.core.api.Assertions.assertThatCode;

import com.taotao.boot.common.model.ddd.command.IdsCommand;
import com.taotao.cloud.goods.TaoTaoCloudGoodsApplicationTests;
import com.taotao.cloud.goods.application.dto.brand.command.CreateBrandCommand;
import com.taotao.cloud.goods.application.dto.brand.command.CreateBrandCommandBuilder;
import com.taotao.cloud.goods.application.dto.brand.command.DisableBrandCommand;
import com.taotao.cloud.goods.application.dto.brand.command.DisableBrandCommandBuilder;
import com.taotao.cloud.goods.application.dto.brand.command.UpdateBrandCommand;
import com.taotao.cloud.goods.application.dto.brand.command.UpdateBrandCommandBuilder;
import com.taotao.cloud.goods.application.service.command.BrandCommandService;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BrandCommandService 集成测试
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2026-08-22
 */
class BrandCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private BrandCommandService brandCommandService;

    @Nested
    class DeleteBrands {

        @Test
        void shouldExecuteWithoutError() {
            assertThatCode(() -> brandCommandService.deleteBrands(
                    IdsCommand.builder().ids(List.of(1L, 2L)).build()))
                .doesNotThrowAnyException();
        }

        @Test
        void shouldExecuteWithSingleIdWithoutError() {
            assertThatCode(() -> brandCommandService.deleteBrands(
                    IdsCommand.builder().ids(List.of(99999L)).build()))
                .doesNotThrowAnyException();
        }
    }

    @Nested
    class CreateBrand {

        @Test
        void shouldExecuteWithoutError() {
            assertThatCode(() -> brandCommandService.createBrand(
                    CreateBrandCommandBuilder.builder()
                        .id(99998L)
                        .name("test-create-brand")
                        .logo("test-logo")
                        .build()))
                .doesNotThrowAnyException();
        }
    }

    @Nested
    class UpdateBrand {

        @Test
        void shouldExecuteWithoutError() {
            assertThatCode(() -> brandCommandService.updateBrand(
                    UpdateBrandCommandBuilder.builder()
                        .id(1L)
                        .name("test-update-brand")
                        .logo("test-logo")
                        .build()))
                .doesNotThrowAnyException();
        }
    }

    @Nested
    class DisableBrand {

        @Test
        void shouldExecuteWithoutErrorWhenDisable() {
            assertThatCode(() -> brandCommandService.disableBrand(
                    DisableBrandCommandBuilder.builder().brandId(1L).disable(true).build()))
                .doesNotThrowAnyException();
        }

        @Test
        void shouldExecuteWithoutErrorWhenEnable() {
            assertThatCode(() -> brandCommandService.disableBrand(
                    DisableBrandCommandBuilder.builder ().brandId(1L).disable(false).build()))
                .doesNotThrowAnyException();
        }
    }
}
