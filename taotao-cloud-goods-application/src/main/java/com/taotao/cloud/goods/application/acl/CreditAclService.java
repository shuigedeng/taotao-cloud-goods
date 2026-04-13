package com.taotao.cloud.goods.application.acl;

import com.taotao.cloud.goods.application.dto.external.credit.req.CreditReq;
import com.taotao.cloud.goods.application.dto.external.credit.res.CreditRes;

public interface CreditAclService {
	CreditRes credit(CreditReq creditReq);
}
