package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fix: Ensure getWriter() is called exactly once to satisfy Mockito verification
        if (resp != null) {
            resp.setContentType("text/plain");
            PrintWriter writer = resp.getWriter();
            if (writer != null) {
                writer.write("Hello from HelloServlet");
            }
        }
    }
}