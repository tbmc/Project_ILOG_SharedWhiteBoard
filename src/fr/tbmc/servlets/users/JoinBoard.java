package fr.tbmc.servlets.users;

import fr.tbmc.boards.User;
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
public class JoinBoard extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String boardName = req.getParameter("BoardName");
        String pseudo = req.getParameter("pseudo");

        if(boardName == null || pseudo == null) {
            Response.simple(resp, 404, "boardName and pseudo must be specified");
            return;
        }

        CreatedBoards cb = CreatedBoards.getInstance();

        User user = new User(pseudo);


        try
        {
            cb.addUserToBoard(boardName, user);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("boardName", boardName);

            Response.simple(resp);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            Response.simple(resp, 404, e.getMessage());
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            Response.simple(resp, 403, e.getMessage());
        }

    }
}
