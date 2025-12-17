package com.library.config;

import com.library.service.BookService;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 数据初始化配置类
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BookService bookService;
    
    @Override
    public void run(String... args) throws Exception {
        // 初始化默认用户
        userService.initDefaultUsers();
        
        // 初始化默认图书数据
        bookService.initDefaultBooks();
        
        System.out.println("===========================================");
        System.out.println("数据初始化完成！");
        System.out.println("默认管理员账号: admin / admin123");
        System.out.println("默认用户账号: user / user123");
        System.out.println("===========================================");
    }
}
