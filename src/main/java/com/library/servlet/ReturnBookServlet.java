package com.library.servlet;

import com.library.service.BorrowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 归还图书Servlet
 */
@WebServlet("/return")
public class ReturnBookServlet extends HttpServlet {
    private BorrowService borrowService = new BorrowService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String recordIdStr = req.getParameter("recordId");
        
        try {
            Integer recordId = Integer.parseInt(recordIdStr);
            boolean success = borrowService.returnBook(recordId);
            
            if (success) {
                req.setAttribute("message", "归还成功！");
            } else {
                req.setAttribute("error", "归还失败！");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "无效的记录ID！");
        }

        // Redirect to current borrows
        resp.sendRedirect(req.getContextPath() + "/currentBorrows");
    }
}
