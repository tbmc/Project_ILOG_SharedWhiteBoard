package fr.tbmc.servlets.boards;

import fr.tbmc.boards.singleton.CreatedBoards;
import fr.tbmc.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tbmc on 29/04/2017.
 */
public class BoardList extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CreatedBoards board = CreatedBoards.getInstance();
        Response.json(resp, board.getBoardList());
    }

}