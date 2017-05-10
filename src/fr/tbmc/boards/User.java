package fr.tbmc.boards;

import java.util.UUID;

/**
 * Created by tbmc on 29/04/2017.
 */
public class User
{
    protected String pseudo, identifier;

    public User(String pseudo) {
        this.pseudo = pseudo;
        identifier = UUID.randomUUID().toString();
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public String toString() {
        return getPseudo() + "#" + identifier;
    }
}
