package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by tbmc on 11/05/2017.
 */
public class ClearChange extends AbstractChange
{

    /**
     * Pass parameters to parent constructor
     * {@link AbstractChange#AbstractChange(Type, User, Date, String, Color, int, boolean)}
     * @param user
     * @param date
     * @param id
     */
    public ClearChange(User user, Date date, String id) {
        super(Type.CLEAR, user, date, id,null, 0, false);
    }

    /**
     * Implementing the method of interface {@link Change}
     * @return Every time null because it does not require any coordinate
     */
    @Override
    public Collection<Point> getPoints()
    {
        return null;
    }

    /**
     * Same as {@link AbstractChange#getMetaDataMap()}
     * @return
     */
    @Override
    public Map<String, Object> toMap() {
        return getMetaDataMap();
    }

}
