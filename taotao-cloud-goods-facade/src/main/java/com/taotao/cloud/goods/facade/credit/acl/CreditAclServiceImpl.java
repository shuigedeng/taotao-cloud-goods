package com.taotao.cloud.goods.facade.credit.acl;

import com.taotao.cloud.goods.application.acl.service.CreditAclService;
import com.taotao.cloud.goods.application.acl.dto.credit.req.CreditAclReq;
import com.taotao.cloud.goods.application.acl.dto.credit.res.CreditAclRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CreditAclServiceImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@RequiredArgsConstructor
@Service
public class CreditAclServiceImpl implements CreditAclService {

    @Override
    public CreditAclRes credit(CreditAclReq creditAclReq) {
        return null;
    }
}
