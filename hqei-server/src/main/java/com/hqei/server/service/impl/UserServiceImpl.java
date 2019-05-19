package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseServiceImpl;
import com.hqei.server.dao.UserDao;
import com.hqei.server.domain.UserDo;
import com.hqei.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDo, Long> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public BaseDao<UserDo, Long> getBaseDao() {
        return userDao;
    }

}
