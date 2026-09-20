package com.taotao.cloud.goods.application.acl.service;

import com.taotao.cloud.goods.application.acl.dto.connect.req.ConnectAclReq;
import com.taotao.cloud.goods.application.acl.dto.connect.res.ConnectAclRes;

public interface ConnectAclService {
	ConnectAclRes connect(ConnectAclReq connectAclReq);
}
