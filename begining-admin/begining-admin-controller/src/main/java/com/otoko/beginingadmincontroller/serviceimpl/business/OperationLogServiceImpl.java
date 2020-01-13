package com.otoko.beginingadmincontroller.serviceimpl.business;

import com.otoko.beginingadminentity.entity.admin.business.OperationLog;
import com.otoko.beginingadminmapper.mapper.admin.business.OperationLogMapper;
import com.otoko.beginingadminservice.service.admin.business.OperationLogService;
import com.otoko.beginingcommon.base.BaseServiceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjk
 * @description : OperationLog 服务实现类
 * ---------------------------------
 * @since 2018-10-28
 */

@Service
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public boolean fakeDeleteById(Long operationLogId) {
        return operationLogMapper.fakeDeleteById(operationLogId);
    }

    @Override
    public boolean fakeBatchDelete(List<Long> operationLogIds) {
        return operationLogMapper.fakeBatchDelete(operationLogIds);
    }

    @Override
    public Map<String, Object> selectMapById(Long operationLogId) {
        return operationLogMapper.selectMapById(operationLogId);
    }

    @Override
    public OperationLog operationLogCreateUpdate(OperationLog operationLog) {
        Long operationLogId = operationLog.getId();
        if (operationLogId == null) {
            operationLogMapper.insert(operationLog);
        } else {
            operationLogMapper.updateById(operationLog);
        }
        return operationLog;
    }
}
