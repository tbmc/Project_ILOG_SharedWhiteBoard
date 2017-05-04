package fr.tbmc.servlets.users;

import fr.tbmc.boards.singleton.CreatedBoards;
import fr.tbmc.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by tbmc on 04/05/2017.
 */
public class ListUsers extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        String boardName = (String) session.getAttribute("boardName");
        if(boardName == null) {
            Response.simple(resp, 404, "boardName must be specified");
            return;
        }
        CreatedBoards cb = CreatedBoards.getInstance();
        try
        {
            Response.json(resp, cb.listUsersFromBoard(boardName));
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            Response.simple(resp, 404, e.getMessage());
        }
    }

}
