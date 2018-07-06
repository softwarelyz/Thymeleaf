package com.zhyle.thymeleaf.dao;

import com.zhyle.thymeleaf.entity.User;

import java.util.List;
//User Repository 接口
public interface UserRepository {

    //创建或者修改用户
    User SaveOrUpdate(User user);

    //删除用户
    void deleteUser(Long id);

    //根据id查询用户
    User getUserId(Long id);

    //获取用户列表
    List<User> listUser();
}


