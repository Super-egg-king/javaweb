package com.library.service;

import com.library.dao.UserDAO;
import com.library.entity.User;

/**
 * 用户服务类
 */
public class UserService {
    private UserDAO userDAO = new UserDAO();

    /**
     * 用户登录验证
     */
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            return null;
        }
        return userDAO.findByUsernameAndPassword(username.trim(), password);
    }

    /**
     * 根据用户名查询用户
     */
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    /**
     * 根据ID查询用户
     */
    public User getUserById(Integer id) {
        return userDAO.findById(id);
    }

    /**
     * 用户注册
     */
    public boolean register(User user) {
        // Check if username already exists
        if (userDAO.findByUsername(user.getUsername()) != null) {
            return false;
        }
        return userDAO.insert(user);
    }

    /**
     * 更新用户信息
     */
    public boolean updateUser(User user) {
        return userDAO.update(user);
    }
}
