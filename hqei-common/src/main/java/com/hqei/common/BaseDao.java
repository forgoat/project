package com.hqei.common;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends IdDo, ID extends Serializable> {

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);
}
