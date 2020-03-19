package com.example.demo.service;

import com.example.demo.entity.UmsMember;

/**
 * @author Admin
 * @title: UserService
 * @projectName demo
 * @description: TODO
 * @date 2020/3/15 13:24
 */
public interface UserService {
    UmsMember login(UmsMember umsMember);

    void saveToken(String token, Long memberId);
}
