package fr.tbmc.boards.singleton;

import fr.tbmc.boards.Board;
import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.types.Change;

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
     * Create a new boards
     * @param name Name of the boards
     * @return true if the process is successful or false if it fail, by example because name is already taken
     */
    public boolean createBoard(String name) {
        if(name == null || name.length() < 1) {
            return false;
        }
        if(boardExists(name)) {
            return false;
        }
        Board board = new Board(name);
        boardList.put(name, board);

        return true;
    }

    /**
     * Check if the boards name exists
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
     * Check if a boards contains a users
     * @param name Name of the boards that contains users
     * @param pseudo Name of the users to check if exists
     * @return false if the pseudo is not used in the boards or if the boards does not exists
     *          true if the pseudo is used in the boards
     */
    public boolean boardHasPseudo(String name, String pseudo) {
        if(!boardExists(name))
            return false;
        Board b = boardList.get(name);
        return b.hasUser(pseudo);
    }

    protected Board getVerifiedBoard(String boardName) throws ClassNotFoundException
    {
        Board board = boardList.get(boardName);
        if(board == null)
            throw new ClassNotFoundException(String.format("Board %s doesn't exists", boardName));
        return board;
    }

    public void addUserToBoard(String boardName, User user)
            throws IllegalArgumentException, ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        board.addUser(user);
    }

    public Collection<String> listUsersFromBoard(String boardName) throws ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        return board.listUsers();
    }

    public void addChangeToBoard(String boardName, Change change) throws ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        board.addChange(change);
    }

    public Collection<Change> getChangesSinceLastIdentifierFromBoard(String boardName, String id) throws ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        return board.getChangesSinceLastIdentifier(id);
    }

    public String getLastChangeIdentifierFromBoard(String boardName) throws ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        return board.getLastChangeIdentifier();
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
