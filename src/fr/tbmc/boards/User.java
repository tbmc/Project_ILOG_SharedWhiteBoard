package fr.tbmc.boards;

/**
 * Created by tbmc on 29/04/2017.
 */
public class User
{
    protected String pseudo;
    protected String identifiant;

    public User(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }
}
