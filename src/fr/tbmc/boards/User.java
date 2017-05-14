package fr.tbmc.boards;

import java.util.UUID;

/**
 * Created by tbmc on 29/04/2017.
 *
 * Class representing a User
 *
 */
public class User
{
    /**
     * Pseudo of the User
     */
    protected String pseudo;

    /**
     * Identifier of the User
     * Identifier allows us to be sure that the User is unique
     */
    protected String identifier;

    public User(String pseudo) throws IllegalArgumentException {
        if(pseudo.contains("#"))
            throw new IllegalArgumentException("Illegal character");
        this.pseudo = pseudo;
        identifier = UUID.randomUUID().toString();
    }

    /**
     * Get the pseudo of the User
     * @return pseudo of the User
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Get a String representing the User with the identifier
     * @return String of the following format pseudo#identifier
     */
    @Override
    public String toString() {
        return getPseudo() + "#" + identifier;
    }
}
