package com.taotao.cloud.goods.application.acl;

import com.taotao.cloud.goods.application.dto.connect.req.ConnectReq;
import com.taotao.cloud.goods.application.dto.connect.res.ConnectRes;

public interface ConnectAclService {
	ConnectRes connect(ConnectReq connectReq);
}
