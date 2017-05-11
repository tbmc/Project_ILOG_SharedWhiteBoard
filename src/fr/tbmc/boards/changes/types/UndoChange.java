package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by tbmc on 11/05/2017.
 */
public class UndoChange extends AbstractChange
{
    protected String previousId;

    public UndoChange(User user, Date date, String id, String previousId)
    {
        super(Type.UNDO, user, date, id, null, 0, false);
        this.previousId = previousId;
    }

    public String getPreviousId() {
        return previousId;
    }

    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = getMetaDataMap();
        map.put("previousId", getPreviousId());
        return map;
    }

    @Override
    public Collection<Point> getPoints()
    {
        return null;
    }
}
