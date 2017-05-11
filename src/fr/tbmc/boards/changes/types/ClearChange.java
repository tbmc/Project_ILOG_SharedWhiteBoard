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

    public ClearChange(User user, Date date, String id) {
        super(Type.CLEAR, user, date, id,null, 0, false);
    }

    @Override
    public Collection<Point> getPoints()
    {
        return null;
    }

    @Override
    public Map<String, Object> toMap() {
        return getMetaDataMap();
    }

}