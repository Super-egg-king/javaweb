package com.library.service;

import com.library.entity.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 用户服务类
 */
@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    /**
     * 用户登录验证
     */
    public User login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 简单密码验证（实际项目应该使用加密密码）
            if (user.getPassword().equals(password) && user.getEnabled()) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * 初始化默认用户
     */
    public void initDefaultUsers() {
        if (!existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRealName("管理员");
            admin.setRole("ADMIN");
            admin.setEnabled(true);
            save(admin);
        }
        
        if (!existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("user123");
            user.setRealName("张三");
            user.setStudentId("2021001");
            user.setRole("USER");
            user.setEnabled(true);
            save(user);
        }
    }
}
