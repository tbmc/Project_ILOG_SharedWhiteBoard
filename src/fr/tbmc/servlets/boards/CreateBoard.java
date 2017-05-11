package fr.tbmc.servlets.boards;

import fr.tbmc.boards.singleton.CreatedBoards;
import fr.tbmc.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tbmc on 29/04/2017.
 */
public class CreateBoard extends HttpServlet
{
    /**
     * Create a new boards if the name is not already taken
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

        // Try to create a new boards
        boolean creationSuccessful = boards.createBoard(name);

        if(creationSuccessful) {
            // If the creation successful
            // Send a "OK 200" status code and send no text
            Response.simple(resp);
        }else {
            // If the creation is not successful
            Response.simple(resp, 403, "Error: boards already exists or name is incorrect");
        }
    }

    /**
     * This method does not create a boards, it just tell if it exists and if the pseudo is already taken
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
        Response.json(resp, out);
    }
}
