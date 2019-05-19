package com.hqei.common;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public interface BaseService<T extends IdDo, ID extends Serializable> {

    BaseDao<T, ID> getBaseDao();

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

}
