package fr.tbmc.servlets.singleton;

import fr.tbmc.Board;

import java.util.*;

/**
 * Created by tbmc on 29/04/2017.
 *
 * This class is a singleton that contains the list of all
 * the boards
 *
 */
public class CreatedBoards
{

    /**
     * List of boards
     */
    protected Map<String, Board> boardList = new HashMap<>();

    /**
     * We don't want anyone can instantiate this singleton
     */
    protected CreatedBoards() {}

    /**
     * Get the list of boards
     * @return
     */
    public List<String> getBoardList() {
        List<String> keys = new LinkedList<>();
        keys.addAll(boardList.keySet());
        Collections.sort(keys);
        return keys;
    }

    /**
     * Create a new board
     * @param name Name of the board
     * @return true if the process is successful or false if it fail, by example because name is already taken
     */
    public boolean createBoard(String name) {
        if(boardExists(name)) {
            return false;
        }
        Board board = new Board(name);
        boardList.put(name, board);

        return true;
    }

    /**
     * Check if the board name exists
     * @param name
     * @return true if exists, false if not
     */
    public boolean boardExists(String name) {
        if(boardList.get(name) != null) {
            return true;
        }
        return false;
    }

    /**
     * Check if a board contains a user
     * @param name Name of the board that contains user
     * @param pseudo Name of the user to check if exists
     * @return false if the pseudo is not used in the board or if the board does not exists
     *          true if the pseudo is used in the board
     */
    public boolean boardHasPseudo(String name, String pseudo) {
        if(!boardExists(name))
            return false;
        Board b = boardList.get(name);
        return b.hasUser(pseudo);
    }


    /* Singleton */
    /**
     * static instance
     */
    protected static CreatedBoards INSTANCE;

    /**
     * Return the static instance and if instance is not initialised, it instantiates the instance
     * @return
     */
    public static CreatedBoards getInstance() {
        if(INSTANCE == null)
            INSTANCE = new CreatedBoards();
        return INSTANCE;
    }



}
