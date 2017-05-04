package fr.tbmc.boards.changes;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by tbmc on 04/05/2017.
 */
public interface Change
{

    Type getType();
    User getUser();
    Date getDate();
    String toJson();
    String getIdentifier();
    Collection<Point> getPoints();

}
