package com.wise.dominoshelper.pizza;

import com.wise.dominoshelper.utils.DoughTypes;
import com.wise.dominoshelper.utils.Env;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wise
 */
public class PizzaTypeServlet extends HttpServlet {

    DoughTypes dt = new DoughTypes();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String size = "22";
        if (request.getParameter("size") != null) {
            size = request.getParameter("size");
        }
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        switch (size) {
            case "30":
                response.getWriter().println(dt.getTypes30());
                break;
            case "36":
                response.getWriter().println(dt.getTypes36());
                break;
            default:
                response.getWriter().println(dt.getTypes22());
        }

    }
}
