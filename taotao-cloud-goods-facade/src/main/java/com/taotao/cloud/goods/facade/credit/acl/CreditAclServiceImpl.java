package com.taotao.cloud.goods.facade.credit.acl;

import com.taotao.cloud.goods.application.acl.CreditAclService;
import com.taotao.cloud.goods.application.dto.credit.req.CreditReq;
import com.taotao.cloud.goods.application.dto.credit.res.CreditRes;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CreditAclServiceImpl
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
@RequiredArgsConstructor
@Service
public class CreditAclServiceImpl implements CreditAclService {

    @Override
    public CreditRes credit( CreditReq creditReq ) {
        return null;
    }
}
