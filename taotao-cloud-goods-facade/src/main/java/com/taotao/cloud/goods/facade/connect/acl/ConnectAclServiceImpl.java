package com.taotao.cloud.goods.facade.connect.acl;

import com.taotao.boot.ddd.acl.BaseAclService;
import com.taotao.cloud.goods.application.acl.ConnectAclService;
import com.taotao.cloud.goods.application.dto.connect.req.ConnectReq;
import com.taotao.cloud.goods.application.dto.connect.res.ConnectRes;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ConnectAclServiceImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@RequiredArgsConstructor
@Service
public class ConnectAclServiceImpl extends BaseAclService implements ConnectAclService {

    @Override
    public ConnectRes connect( ConnectReq connectReq ) {
        return null;
    }
}
