package com.hqei.common;

import java.io.Serializable;

public abstract class BaseServiceImpl<T extends IdDo, ID extends Serializable> implements BaseService<T,ID> {

    @Override
    public int deleteByPrimaryKey(ID id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    @Override
    public T selectByPrimaryKey(ID id) {
        return getBaseDao().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getBaseDao().updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record){
        return getBaseDao().updateByPrimaryKeySelective(record);
    }

    @Override
    public int insert(T record) {
        return getBaseDao().insert(record);
    }

}