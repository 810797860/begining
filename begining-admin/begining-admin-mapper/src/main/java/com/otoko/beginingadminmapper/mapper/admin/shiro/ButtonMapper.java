package com.otoko.beginingadminmapper.mapper.admin.shiro;

import com.otoko.beginingadminentity.entity.admin.shiro.Button;
import com.otoko.beginingcommon.base.BaseDao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zhangjk
 * @description : ButtonMapper 接口
 * ---------------------------------
 * @since 2018-12-04
 */
@Repository
public interface ButtonMapper extends BaseDao<Button> {

    /**
     * 获取Map数据（Obj）
     * 自定化MapSql到MysqlGenerator生成
     *
     * @param buttonId
     * @return
     */
    Map<String, Object> selectMapById(@Param("buttonId") Long buttonId);
}