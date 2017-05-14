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
 * Singleton to get only one list of all the boards
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

    /**
     * Get the board if it exist, if not, it throws an exception
     * @param boardName Name of the board
     * @return Board which has the name "boardName"
     * @throws ClassNotFoundException Exception which is thew when Board doesn't exist
     */
    protected Board getVerifiedBoard(String boardName) throws ClassNotFoundException
    {
        Board board = boardList.get(boardName);
        if(board == null)
            throw new ClassNotFoundException(String.format("Board %s doesn't exists", boardName));
        return board;
    }

    /**
     * Add a user to a board
     * {@link Board#addUser(User)}
     * @param boardName Name of the board to which add the user
     * @param user User to add to the Board
     * @throws IllegalArgumentException when the User already exists
     * @throws ClassNotFoundException when the Board doesn't exist
     */
    public void addUserToBoard(String boardName, User user)
            throws IllegalArgumentException, ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        board.addUser(user);
    }

    /**
     * List Users from a Board
     * {@link Board#listUsers()}
     * @param boardName Name of the Board
     * @return Collection of pseudo which are in the Board
     * @throws ClassNotFoundException when the board doesn't exist
     */
    public Collection<String> listUsersFromBoard(String boardName) throws ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        return board.listUsers();
    }

    /**
     * Add a Change to a Board
     * {@link Board#addChange(Change)}
     * @param boardName Name of the Board
     * @param change Change to add to the Board
     * @throws ClassNotFoundException when the Board doesn't exists
     */
    public void addChangeToBoard(String boardName, Change change) throws ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        board.addChange(change);
    }

    /**
     * Get all the Change since the id parameter
     * {@link Board#getChangesSinceLastIdentifier(String)}
     * @param boardName Name of the Board
     * @param id identifier if the Changes after which to get all the Changes
     * @return List containing all the Changes since id
     * @throws ClassNotFoundException when the Board doesn't exist
     */
    public Collection<Change> getChangesSinceLastIdentifierFromBoard(String boardName, String id) throws ClassNotFoundException
    {
        Board board = getVerifiedBoard(boardName);
        return board.getChangesSinceLastIdentifier(id);
    }

    /**
     * Get identifier from the last Change in the Board
     * {@link Board#getLastChangeIdentifier()}
     * @param boardName Name of the Board
     * @return identifier of the last Change in the Board
     * @throws ClassNotFoundException when the Board doesn't exist
     */
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
