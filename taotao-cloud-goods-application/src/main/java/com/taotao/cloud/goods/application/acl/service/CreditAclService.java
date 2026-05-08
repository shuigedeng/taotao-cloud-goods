package com.taotao.cloud.goods.application.acl.service;

import com.taotao.cloud.goods.application.acl.dto.credit.req.CreditReq;
import com.taotao.cloud.goods.application.acl.dto.credit.res.CreditRes;

public interface CreditAclService {
	CreditRes credit(CreditReq creditReq);
}
