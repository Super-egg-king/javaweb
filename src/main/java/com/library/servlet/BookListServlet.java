package com.library.servlet;

import com.library.entity.Book;
import com.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 图书列表Servlet
 */
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        
        if ("search".equals(action)) {
            String keyword = req.getParameter("keyword");
            List<Book> books = bookService.searchBooks(keyword);
            req.setAttribute("books", books);
            req.setAttribute("keyword", keyword);
        } else if ("recommended".equals(action)) {
            List<Book> books = bookService.getRecommendedBooks();
            req.setAttribute("books", books);
            req.setAttribute("isRecommended", true);
        } else {
            List<Book> books = bookService.getAllBooks();
            req.setAttribute("books", books);
        }

        req.getRequestDispatcher("/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
