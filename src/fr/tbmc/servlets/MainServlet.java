package fr.tbmc.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tbmc on 22/04/2017.
 */
public class MainServlet extends HttpServlet
{

    //todo: remove this servlet

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
