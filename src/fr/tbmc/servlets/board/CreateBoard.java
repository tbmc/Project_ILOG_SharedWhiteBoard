package fr.tbmc.servlets.board;

import fr.tbmc.servlets.singleton.CreatedBoards;
import fr.tbmc.servlets.JsonResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tbmc on 29/04/2017.
 */
public class CreateBoard extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        CreatedBoards boards = CreatedBoards.getInstance();
        boolean creationSuccessful = boards.createBoard(name);

        resp.setContentType("text");
        PrintWriter out = resp.getWriter();

        if(creationSuccessful) {
            resp.setStatus(200);
            out.print("");
        }else {
            resp.setStatus(403);
            out.print("Error: board already exists");
        }
        out.flush();
    }

    /**
     * This method does not create a board, it just tell if it exists and if the pseudo is already taken
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String pseudo = req.getParameter("pseudo");
        CreatedBoards boards = CreatedBoards.getInstance();
        Map<String, Boolean> out = new HashMap<>();
        out.put("name", boards.boardExists(name));
        out.put("pseudo", boards.boardHasPseudo(name, pseudo));
        JsonResponse.r(resp, out);
    }
}
