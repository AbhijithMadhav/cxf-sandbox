package org.abhijith.rest.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "PlainServlet",
        urlPatterns = "/employees"
)
public class EmployeeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.getWriter().println("Hello " + req.getPathInfo() + ". I am the response from a get plain servlet");
        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
