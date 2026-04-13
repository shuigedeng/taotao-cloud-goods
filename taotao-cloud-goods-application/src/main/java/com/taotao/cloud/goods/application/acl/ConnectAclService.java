package com.taotao.cloud.goods.application.acl;

import com.taotao.cloud.goods.application.dto.external.connect.req.ConnectReq;
import com.taotao.cloud.goods.application.dto.external.connect.res.ConnectRes;

public interface ConnectAclService {
	ConnectRes connect(ConnectReq connectReq);
}
