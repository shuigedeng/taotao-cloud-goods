package com.taotao.cloud.goods.facade.acl;

import com.taotao.cloud.goods.application.acl.CreditAclService;
import com.taotao.cloud.goods.application.dto.credit.req.CreditReq;
import com.taotao.cloud.goods.application.dto.credit.res.CreditRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreditAclServiceImpl implements CreditAclService {
	@Override
	public CreditRes credit(CreditReq creditReq) {
		return null;
	}
}
