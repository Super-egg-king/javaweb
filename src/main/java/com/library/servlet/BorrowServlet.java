package com.library.servlet;

import com.library.entity.User;
import com.library.service.BorrowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 图书借阅Servlet
 */
@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    private BorrowService borrowService = new BorrowService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        String bookIdStr = req.getParameter("bookId");
        
        try {
            Integer bookId = Integer.parseInt(bookIdStr);
            boolean success = borrowService.borrowBook(user.getId(), bookId);
            
            if (success) {
                req.setAttribute("message", "借阅成功！");
            } else {
                req.setAttribute("error", "借阅失败，图书可能已无库存！");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "无效的图书ID！");
        }

        // Redirect to book list
        resp.sendRedirect(req.getContextPath() + "/bookList");
    }
}
