package fr.tbmc.servlets.canvas;

import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.Change;
import fr.tbmc.boards.singleton.CreatedBoards;
import fr.tbmc.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by tbmc on 04/05/2017.
 */
public class GetChange extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String boardName = (String) session.getAttribute("boardName");
        if(user == null || boardName == null) {
            Response.simple(resp, 403, "Error, you are not connected");
            return;
        }

        String lastIdentifier = req.getParameter("lastId");
        CreatedBoards createdBoards = CreatedBoards.getInstance();
        try
        {
            Collection<Change> list = createdBoards.getChangesSinceLastIdentifierFromBoard(boardName, lastIdentifier);
            Response.json(resp, Change.toMapList(list));
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            Response.simple(resp, 404, e.getMessage());
        }

    }
}
