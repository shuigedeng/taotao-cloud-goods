package com.taotao.cloud.goods.application.acl.service;

import com.taotao.cloud.goods.application.acl.dto.credit.req.CreditAclReq;
import com.taotao.cloud.goods.application.acl.dto.credit.res.CreditAclRes;

public interface CreditAclService {
	CreditAclRes credit(CreditAclReq creditAclReq);
}
