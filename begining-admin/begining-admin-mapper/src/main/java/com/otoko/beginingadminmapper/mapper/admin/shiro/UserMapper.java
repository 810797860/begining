package com.otoko.beginingadminmapper.mapper.admin.shiro;

import com.otoko.beginingadminentity.entity.admin.shiro.User;
import com.otoko.beginingcommon.base.BaseDao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zhangjk
 * @description : UserMapper 接口
 * ---------------------------------
 * @since 2018-11-13
 */
@Repository
public interface UserMapper extends BaseDao<User> {

    /**
     * 获取Map数据（Obj）
     * 自定化MapSql到MysqlGenerator生成
     *
     * @param userId
     * @return
     */
    Map<String, Object> selectMapById(@Param("userId") Long userId);
}