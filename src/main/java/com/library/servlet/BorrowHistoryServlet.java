package com.library.servlet;

import com.library.entity.BorrowRecord;
import com.library.entity.User;
import com.library.service.BorrowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 借阅记录Servlet
 */
@WebServlet("/borrowHistory")
public class BorrowHistoryServlet extends HttpServlet {
    private BorrowService borrowService = new BorrowService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        List<BorrowRecord> records;
        if (user.isAdmin()) {
            records = borrowService.getAllBorrowRecords();
        } else {
            records = borrowService.getBorrowHistory(user.getId());
        }
        
        req.setAttribute("records", records);
        req.getRequestDispatcher("/history.jsp").forward(req, resp);
    }
}
