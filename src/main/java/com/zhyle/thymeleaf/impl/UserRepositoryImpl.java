package com.zhyle.thymeleaf.impl;


import com.zhyle.thymeleaf.dao.UserRepository;
import com.zhyle.thymeleaf.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long,User> userMap = new ConcurrentHashMap<>();

    @Override
    public User SaveOrUpdate(User user) {
        Long id = user.getId();
        if(id == null){//新建
            id = counter.incrementAndGet();
            user.setId(id);
        }
        this.userMap.put(id,user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        this.userMap.remove(id);
    }

    @Override
    public User getUserId(Long id) {
        return this.userMap.get(id);
    }

    @Override
    public List<User> listUser() {
        return new ArrayList<User>(this.userMap.values());
    }
}
