package com.taotao.cloud.goods.application.acl.connect.service;


import com.taotao.cloud.goods.application.acl.connect.dto.req.ConnectAclReq;
import com.taotao.cloud.goods.application.acl.connect.dto.res.ConnectAclRes;

public interface ConnectAclService {
	ConnectAclRes connect(ConnectAclReq connectAclReq);
}
