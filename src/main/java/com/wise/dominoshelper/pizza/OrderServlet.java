package com.wise.dominoshelper.pizza;

import com.wise.dominoshelper.utils.Env;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wise
 */
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("OK");

        Env.ORDER_BODY = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(Env.ORDER_BODY);
    }

}
