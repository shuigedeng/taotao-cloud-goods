package com.taotao.cloud.goods.facade.acl;

import com.taotao.cloud.goods.application.acl.ConnectAclService;
import com.taotao.cloud.goods.application.dto.connect.req.ConnectReq;
import com.taotao.cloud.goods.application.dto.connect.res.ConnectRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConnectAclServiceImpl implements ConnectAclService {
	@Override
	public ConnectRes connect(ConnectReq connectReq) {
		return null;
	}
}
