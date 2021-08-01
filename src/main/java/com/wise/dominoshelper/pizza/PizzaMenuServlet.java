package com.wise.dominoshelper.pizza;

import com.wise.dominoshelper.utils.Env;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wise
 */
public class PizzaMenuServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String menu = new String(Files.readAllBytes(Paths.get(Env.MENU_PIZZA_PATH)), "utf-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(Env.MENU_PIZZA);
    }
}
