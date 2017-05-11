package fr.tbmc.boards;

import fr.tbmc.boards.changes.Change;
import fr.tbmc.boards.changes.ChangeList;

import java.util.*;

/**
 * Created by tbmc on 29/04/2017.
 */
public class Board
{
    protected String name;

    protected Map<String, User> users = new HashMap<>();
    protected ChangeList changeList = new ChangeList();

    public Board(String name) {
        this.name = name;
    }

    public boolean hasUser(String pseudo) {
        return users.containsKey(pseudo);
    }

    public void addUser(User user) throws IllegalArgumentException
    {
        String pseudo = user.getPseudo();
        if(hasUser(pseudo)) {
            throw new IllegalArgumentException(String.format("User (%s) already exists in this boards (%s)", name, pseudo));
        }
        users.put(pseudo, user);
    }

    public Collection<String> listUsers() {
        Set<String> keySet = users.keySet();
        List<String> keys = new ArrayList<>();
        keys.addAll(keySet);
        Collections.sort(keys);
        return keys;
    }

    public Collection<Change> getChangesSinceLastIdentifier(String id) {
        ChangeList changes = changeList.getListOfChangeSince(id);
        return changes;
    }

    public void addChange(Change change) {
        changeList.add(change);
    }

    public String getLastChangeIdentifier() {
        return changeList.getLastIdentifier();
    }

}
