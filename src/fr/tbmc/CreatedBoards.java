package fr.tbmc;

import java.util.*;

/**
 * Created by tbmc on 29/04/2017.
 */
public class CreatedBoards
{

    protected Map<String, Board> boardList = new HashMap<>();


    protected CreatedBoards() {}

    public List<String> getBoardList() {
        List<String> keys = new LinkedList<>();
        keys.addAll(boardList.keySet());
        Collections.sort(keys);
        return keys;
    }

    public boolean createBoard(String name) {
        if(boardExists(name)) {
            return false;
        }
        Board board = new Board(name);
        boardList.put(name, board);

        return true;
    }

    public boolean boardExists(String name) {
        if(boardList.get(name) != null) {
            return true;
        }
        return false;
    }

    public boolean boardHasPseudo(String name, String pseudo) {
        if(!boardExists(name))
            return false;
        Board b = boardList.get(name);
        return b.hasUser(pseudo);
    }


    /* Singleton */

    protected static CreatedBoards INSTANCE;

    public static CreatedBoards getInstance() {
        if(INSTANCE == null)
            INSTANCE = new CreatedBoards();
        return INSTANCE;
    }



}
