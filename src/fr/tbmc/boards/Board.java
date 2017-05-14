package fr.tbmc.boards;

import fr.tbmc.boards.changes.types.Change;
import fr.tbmc.boards.changes.ChangeList;

import java.util.*;

/**
 * Created by tbmc on 29/04/2017.
 *
 * Class representing a Board
 *
 */
public class Board
{
    /**
     * Name of the Board
     */
    protected String name;

    /**
     * Map of users
     * key is the pseudo of the user
     */
    protected Map<String, User> users = new HashMap<>();

    /**
     * List of Changes
     * {@link ChangeList}
     */
    protected ChangeList changeList = new ChangeList();

    /**
     * Constructor
     * @param name Name of the Board
     */
    public Board(String name) {
        this.name = name;
    }

    /**
     * Check if the Board contains the user which has the pseudo in parameter
     * @param pseudo Pseudo of the user to check if he exists
     * @return true if he already exists in the Board, false otherwise
     */
    public boolean hasUser(String pseudo) {
        return users.containsKey(pseudo);
    }

    /**
     * Add a user to the Board
     * @param user User to add
     * @throws IllegalArgumentException when another user with the same pseudo is already in the Board
     */
    public void addUser(User user) throws IllegalArgumentException
    {
        String pseudo = user.getPseudo();
        if(hasUser(pseudo)) {
            throw new IllegalArgumentException(String.format("User (%s) already exists in this boards (%s)", name, pseudo));
        }
        users.put(pseudo, user);
    }

    /**
     * Get a Collection of Users in the Board
     * {@link Collection}
     * {@link User}
     * @return Collection of Users in the Board
     */
    public Collection<String> listUsers() {
        Set<String> keySet = users.keySet();
        List<String> keys = new ArrayList<>();
        keys.addAll(keySet);
        Collections.sort(keys);
        return keys;
    }

    /**
     * Get the Changes since the identifier
     * {@link ChangeList#getListOfChangeSince(String)}
     * @param id identifier of the Change
     * @return Collection of Changes since id
     */
    public Collection<Change> getChangesSinceLastIdentifier(String id) {
        ChangeList changes = changeList.getListOfChangeSince(id);
        return changes;
    }

    /**
     * Add a Change to the Board
     * @param change Change to add
     */
    public void addChange(Change change) {
        changeList.add(change);
    }

    /**
     * Get identifier of the last Change in the Board
     * {@link ChangeList#getLastIdentifier()}
     * @return Identifier of the last Change in the Board
     */
    public String getLastChangeIdentifier() {
        return changeList.getLastIdentifier();
    }

}
