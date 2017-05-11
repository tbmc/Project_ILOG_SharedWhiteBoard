package fr.tbmc.boards.changes;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tbmc on 04/05/2017.
 */
public interface Change
{

    Type getType();
    User getUser();
    Date getDate();
    String getIdentifier();
    Color getColor();
    int getThickness();
    boolean isFilled();

    Map<String, Object> toMap();
    Collection<Point> getPoints();

    static Collection<Map<String, Object>> toMapList(Collection<Change> changeList) {
        List<Map<String, Object>> out = new ArrayList<>(changeList.size());
        for(Change change : changeList) {
            out.add(change.toMap());
        }
        return out;
    }

}
