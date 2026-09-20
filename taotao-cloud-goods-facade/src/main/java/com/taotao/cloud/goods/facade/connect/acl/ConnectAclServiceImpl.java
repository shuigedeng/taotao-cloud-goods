package com.taotao.cloud.goods.facade.connect.acl;

import com.taotao.boot.ddd.acl.AclBaseService;
import com.taotao.cloud.goods.application.acl.service.ConnectAclService;
import com.taotao.cloud.goods.application.acl.dto.connect.req.ConnectAclReq;
import com.taotao.cloud.goods.application.acl.dto.connect.res.ConnectAclRes;
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
public class ConnectAclServiceImpl extends AclBaseService implements ConnectAclService {

    @Override
    public ConnectAclRes connect(ConnectAclReq connectAclReq) {
        return null;
    }
}
