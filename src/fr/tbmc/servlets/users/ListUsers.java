package fr.tbmc.servlets.users;

import fr.tbmc.boards.singleton.CreatedBoards;
import fr.tbmc.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        String pseudo = (String) session.getAttribute("pseudo");
        if(boardName == null) {
            Response.simple(resp, 404, "boardName must be specified");
            return;
        }
        CreatedBoards cb = CreatedBoards.getInstance();
        try
        {
            Map<String, Object> map = new HashMap<>();
            map.put("pseudo", pseudo);
            map.put("boardName", boardName);
            map.put("list", cb.listUsersFromBoard(boardName));
            Response.json(resp, map);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            Response.simple(resp, 404, e.getMessage());
        }
    }

}
