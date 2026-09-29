package com.taotao.cloud.goods.application.acl.credit.service;


import com.taotao.cloud.goods.application.acl.credit.dto.req.CreditAclReq;
import com.taotao.cloud.goods.application.acl.credit.dto.res.CreditAclRes;

public interface CreditAclService {
	CreditAclRes credit(CreditAclReq creditAclReq);
}
