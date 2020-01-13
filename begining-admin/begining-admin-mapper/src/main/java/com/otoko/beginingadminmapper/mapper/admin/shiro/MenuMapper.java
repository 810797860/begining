package com.otoko.beginingadminmapper.mapper.admin.shiro;

import com.otoko.beginingadminentity.entity.admin.shiro.Menu;
import com.otoko.beginingcommon.base.BaseDao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zhangjk
 * @description : MenuMapper 接口
 * ---------------------------------
 * @since 2018-11-29
 */
@Repository
public interface MenuMapper extends BaseDao<Menu> {

    /**
     * 获取Map数据（Obj）
     * 自定化MapSql到MysqlGenerator生成
     *
     * @param menuId
     * @return
     */
    Map<String, Object> selectMapById(@Param("menuId") Long menuId);
}