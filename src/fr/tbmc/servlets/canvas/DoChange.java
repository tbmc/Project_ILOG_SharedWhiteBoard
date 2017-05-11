package fr.tbmc.servlets.canvas;

import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.types.Change;
import fr.tbmc.boards.changes.singleton.ChangeFactory;
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
public class DoChange extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String data = req.getParameter("data");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String boardName = (String) session.getAttribute("boardName");
        if(user == null || boardName == null) {
            Response.simple(resp, 403, "Error, you are not connected");
            return;
        }

        ChangeFactory changeFactory = ChangeFactory.getInstance();
        Change change = changeFactory.createFromJson(data, user);
        CreatedBoards createdBoards = CreatedBoards.getInstance();
        try
        {
            createdBoards.addChangeToBoard(boardName, change);
            Response.simple(resp, 200, change.getIdentifier());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            Response.simple(resp, 403, e.getMessage());
        }
    }

}
