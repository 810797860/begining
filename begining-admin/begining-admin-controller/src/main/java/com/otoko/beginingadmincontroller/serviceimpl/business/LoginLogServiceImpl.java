package com.otoko.beginingadmincontroller.serviceimpl.business;

import com.otoko.beginingadminentity.entity.admin.business.LoginLog;
import com.otoko.beginingadminmapper.mapper.admin.business.LoginLogMapper;
import com.otoko.beginingadminservice.service.admin.business.LoginLogService;
import com.otoko.beginingcommon.base.BaseServiceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjk
 * @description : LoginLog 服务实现类
 * ---------------------------------
 * @since 2018-10-28
 */

@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public boolean fakeDeleteById(Long loginLogId) {
        return loginLogMapper.fakeDeleteById(loginLogId);
    }

    @Override
    public boolean fakeBatchDelete(List<Long> loginLogIds) {
        return loginLogMapper.fakeBatchDelete(loginLogIds);
    }

    @Override
    public Map<String, Object> selectMapById(Long loginLogId) {
        return loginLogMapper.selectMapById(loginLogId);
    }

    @Override
    public LoginLog loginLogCreateUpdate(LoginLog loginLog) {
        Long loginLogId = loginLog.getId();
        if (loginLogId == null) {
            loginLogMapper.insert(loginLog);
        } else {
            loginLogMapper.updateById(loginLog);
        }
        return loginLog;
    }
}
