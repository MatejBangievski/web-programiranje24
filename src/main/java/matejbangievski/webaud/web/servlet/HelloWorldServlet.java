package matejbangievski.webaud.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello")

public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        //Se zima od query stringot
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        if (name == null || surname == null) {
            name = "X";
            surname = "Y";
        }

        writer.format("<html><head><body><h1>Hello %s %s!</h1></body></head></html>", name, surname);
        writer.flush();

    }
}
