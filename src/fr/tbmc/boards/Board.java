package fr.tbmc.boards;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tbmc on 29/04/2017.
 */
public class Board
{
    protected String name;

    protected Map<String, User> users = new HashMap<>();

    public Board(String name) {
        this.name = name;
    }

    public boolean hasUser(String pseudo) {
        return users.containsKey(pseudo);
    }

}
