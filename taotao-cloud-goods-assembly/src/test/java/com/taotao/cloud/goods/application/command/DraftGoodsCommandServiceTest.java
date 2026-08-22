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
import com.taotao.cloud.goods.application.dto.draft.command.CreateDraftGoodsSkuParamsCommand;
import com.taotao.cloud.goods.application.dto.draft.command.SaveDraftGoodsSkuParamsCommand;
import com.taotao.cloud.goods.application.dto.draft.command.UpdateDraftGoodsSkuParamsCommand;
import com.taotao.cloud.goods.application.service.command.DraftGoodsCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 草稿商品命令服务集成测试
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:56
 */
@DisplayName("草稿商品命令服务集成测试")
class DraftGoodsCommandServiceTest extends TaoTaoCloudGoodsApplicationTests {

    @Autowired
    private DraftGoodsCommandService draftGoodsCommandService;

    @Nested
    @DisplayName("添加草稿商品")
    class AddGoodsDraft {

        @Test
        @DisplayName("正常添加草稿商品")
        void shouldAddGoodsDraft() {
            CreateDraftGoodsSkuParamsCommand command = new CreateDraftGoodsSkuParamsCommand(
                    List.of("image1.jpg"), null, List.of(Map.of("key", "value")));
            boolean result = draftGoodsCommandService.addGoodsDraft(command);
            assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("更新草稿商品")
    class UpdateGoodsDraft {

        @Test
        @DisplayName("正常更新草稿商品")
        void shouldUpdateGoodsDraft() {
            UpdateDraftGoodsSkuParamsCommand command = new UpdateDraftGoodsSkuParamsCommand(
                    List.of("image1.jpg"), null, List.of(Map.of("key", "value")));
            boolean result = draftGoodsCommandService.updateGoodsDraft(command);
            assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("保存草稿商品")
    class SaveGoodsDraft {

        @Test
        @DisplayName("正常保存草稿商品")
        void shouldSaveGoodsDraft() {
            SaveDraftGoodsSkuParamsCommand command = new SaveDraftGoodsSkuParamsCommand(
                    List.of("image1.jpg"), null, List.of(Map.of("key", "value")));
            boolean result = draftGoodsCommandService.saveGoodsDraft(command);
            assertThat(result).isTrue();
        }
    }

    @Nested
    @DisplayName("删除草稿商品")
    class DeleteGoodsDraft {

        @Test
        @DisplayName("正常删除草稿商品")
        void shouldDeleteGoodsDraft() {
            boolean result = draftGoodsCommandService.deleteGoodsDraft(1L);
            assertThat(result).isTrue();
        }
    }
}
