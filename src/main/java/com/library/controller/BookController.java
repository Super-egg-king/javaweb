package com.library.controller;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 图书管理控制器
 */
@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    /**
     * 图书列表页面
     */
    @GetMapping
    public String bookList(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("user", currentUser);
        return "books/list";
    }
    
    /**
     * 新书推荐页面
     */
    @GetMapping("/recommended")
    public String recommendedBooks(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        List<Book> recommendedBooks = bookService.getRecommendedBooks();
        model.addAttribute("books", recommendedBooks);
        model.addAttribute("user", currentUser);
        return "books/recommended";
    }
    
    /**
     * 添加图书页面（仅管理员）
     */
    @GetMapping("/add")
    public String addBookPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/home";
        }
        
        model.addAttribute("user", currentUser);
        return "books/add";
    }
    
    /**
     * 保存图书（仅管理员）
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/home";
        }
        
        bookService.save(book);
        return "redirect:/books";
    }
    
    /**
     * 编辑图书页面（仅管理员）
     */
    @GetMapping("/edit/{id}")
    public String editBookPage(@PathVariable Long id, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/home";
        }
        
        Book book = bookService.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/books";
        }
        
        model.addAttribute("book", book);
        model.addAttribute("user", currentUser);
        return "books/edit";
    }
    
    /**
     * 更新图书（仅管理员）
     */
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/home";
        }
        
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }
    
    /**
     * 删除图书（仅管理员）
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/home";
        }
        
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
