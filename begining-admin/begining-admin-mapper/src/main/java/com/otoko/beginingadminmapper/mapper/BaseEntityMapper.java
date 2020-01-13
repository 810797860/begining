package com.otoko.beginingadminmapper.mapper;

import com.otoko.beginingcommon.base.BaseDao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 81079
 */

@Repository
public interface BaseEntityMapper<T> extends BaseDao<T> {
}