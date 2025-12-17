package com.library.controller;

import com.library.entity.BorrowRecord;
import com.library.entity.User;
import com.library.service.BookService;
import com.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 借阅管理控制器
 */
@Controller
@RequestMapping("/borrow")
public class BorrowController {
    
    @Autowired
    private BorrowRecordService borrowRecordService;
    
    @Autowired
    private BookService bookService;
    
    /**
     * 借阅图书
     */
    @PostMapping("/book/{bookId}")
    public String borrowBook(@PathVariable Long bookId, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        try {
            borrowRecordService.borrowBook(currentUser, bookId);
            return "redirect:/borrow/current";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/books";
        }
    }
    
    /**
     * 当前借阅页面
     */
    @GetMapping("/current")
    public String currentBorrowRecords(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        List<BorrowRecord> records = borrowRecordService.getCurrentBorrowRecords(currentUser);
        model.addAttribute("records", records);
        model.addAttribute("user", currentUser);
        return "borrow/current";
    }
    
    /**
     * 借阅记录页面
     */
    @GetMapping("/history")
    public String borrowHistory(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        List<BorrowRecord> records = borrowRecordService.getAllBorrowRecords(currentUser);
        model.addAttribute("records", records);
        model.addAttribute("user", currentUser);
        return "borrow/history";
    }
    
    /**
     * 归还图书
     */
    @PostMapping("/return/{recordId}")
    public String returnBook(@PathVariable Long recordId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        try {
            borrowRecordService.returnBook(recordId);
        } catch (Exception e) {
            // 处理异常
        }
        return "redirect:/borrow/current";
    }
    
    /**
     * 所有借阅记录（仅管理员）
     */
    @GetMapping("/all")
    public String allBorrowRecords(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/home";
        }
        
        List<BorrowRecord> records = borrowRecordService.getAllRecords();
        model.addAttribute("records", records);
        model.addAttribute("user", currentUser);
        return "borrow/all";
    }
}
