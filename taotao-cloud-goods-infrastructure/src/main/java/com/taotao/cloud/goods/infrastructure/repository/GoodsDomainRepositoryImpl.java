package com.taotao.cloud.goods.infrastructure.repository;

import com.taotao.boot.data.datasource.tx.TransactionalUtil;
import com.taotao.boot.data.mybatis.utils.MybatisUtil;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.repository.GoodsDomainRepository;
import com.taotao.cloud.goods.domain.goods.valobj.GoodsStatus;
import com.taotao.cloud.goods.infrastructure.assembler.GoodsAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.GoodsPO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
@AllArgsConstructor
public class GoodsDomainRepositoryImpl implements GoodsDomainRepository {


	private final TransactionalUtil transactionalUtil;
	private final MybatisUtil mybatisUtil;
	private final GoodsMapper goodsMapper;
	private final GoodsSkuMapper goodsSkuMapper;

	@Override
	public void save(GoodsAgg goods) {
		GoodsPO goodsPo = GoodsAssembler.INSTANCE.convert(goods);

		goodsMapper.insert(goodsPo);
	}


	@Override
	public Integer countByIdIn(Collection<BizId> ids) {
		return 0;
	}

	@Override
	public Boolean existsByCategoryIdIn(Collection<BizId> categoryIds) {
		return null;
	}

	@Override
	public Boolean existsByIdInAndGoodsStatus(Collection<BizId> goodsIds, GoodsStatus goodsStatus) {
		return null;
	}

	@Override
	public Boolean existsShelvedGoodsByIdIn(Collection<BizId> goodsIds) {
		return null;
	}

	@Override
	public void batchModifyGoodsStatus(Collection<BizId> goodsIds, GoodsStatus goodsStatus) {

	}

	@Override
	public GoodsAgg findGoodsWithCNameById(Long id) {
		return null;
	}

	@Override
	public Boolean existsByTagIds(Collection<Long> tagIds) {
		return null;
	}

	@Override
	public Page<GoodsAgg> findGoodsWithCNamePageJpa(String goodsName, Long categoryId, BigDecimal startGoodsPrice, BigDecimal endGoodsPrice, Integer goodsStatus, LocalDate beforeExpirationDate, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, Pageable pageable) {
		return null;
	}
}
