package matejbangievski.webaud.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matejbangievski.webaud.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CategoryServlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService;
    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        String ip_address = req.getRemoteAddr();
        String client_agent = req.getHeader("User-Agent");

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<body>");
        writer.println("<h3>User info:<h3>");
        writer.format("Ip Address: %s<br/>", ip_address);
        writer.format("User Agent: %s<br/>", client_agent);
        writer.println("<h3>Category list:<h3>");
        writer.println("<ul>");
        categoryService.listCategories().stream().forEach(category -> writer.format("<li>%s (%s)</li>", category.getName(), category.getDescription()));
        writer.println("</ul>");
        writer.println("<h3>Add new category:<h3>");
        writer.println("<form method='POST' action='/servlet/category'>");
        writer.println("<label for='name'>Name:</label>");
        writer.println("<input id='name type='text' name='name' /><br/>");
        writer.println("<label for='desc'>Description:</label>");
        writer.println("<input id='desc type='text' name='desc' />");
        writer.println("<input type='submit' value='submit' />");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</head>");
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        categoryService.create(name, description);
        resp.sendRedirect("/servlet/category");
    }
}
