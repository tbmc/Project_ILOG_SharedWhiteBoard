package fr.tbmc.servlets.board;

import fr.tbmc.boards.singleton.CreatedBoards;
import fr.tbmc.utils.JsonResponse;

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
    /**
     * Create a new board if the name is not already taken
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Get the name pass in parameter
        String name = req.getParameter("name");

        // Get the instance of CreatedBoards
        CreatedBoards boards = CreatedBoards.getInstance();

        // Try to create a new board
        boolean creationSuccessful = boards.createBoard(name);

        // Set the type of the response
        resp.setContentType("text");
        PrintWriter out = resp.getWriter();


        if(creationSuccessful) {
            // If the creation successful
            // Send a "OK 200" status code and send no text
            resp.setStatus(200);
            out.print("");
        }else {
            // If the creation is not successful
            resp.setStatus(403);
            out.print("Error: board already exists");
        }
        // Force the writer to write in the output stream
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
        // Get the parameters
        String name = req.getParameter("name");
        String pseudo = req.getParameter("pseudo");

        // Get the instance of CreatedBoards
        CreatedBoards boards = CreatedBoards.getInstance();

        // Create a HashMap to return a JSON object
        Map<String, Boolean> out = new HashMap<>();
        out.put("name", boards.boardExists(name));
        out.put("pseudo", boards.boardHasPseudo(name, pseudo));

        // Send the response to the client
        JsonResponse.r(resp, out);
    }
}
