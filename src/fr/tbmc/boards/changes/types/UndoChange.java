package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by tbmc on 11/05/2017.
 *
 * Class representing an undo Change
 *
 */
public class UndoChange extends AbstractChange
{
    /**
     * id of the Change to return to
     * Undo all the Changes between this one and the last others
     */
    protected String previousId;

    /**
     * Pass parameters to parent constructor
     * @param user
     * @param date
     * @param id
     * @param previousId id of the Change to return
     */
    public UndoChange(User user, Date date, String id, String previousId)
    {
        super(Type.UNDO, user, date, id, null, 0, false);
        this.previousId = previousId;
    }

    /**
     * id of the Change to return to
     * @return Change to return to
     */
    public String getPreviousId() {
        return previousId;
    }

    /**
     * Same as {@link Change#toMap()} but add the previousId parameter
     * @return
     */
    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = getMetaDataMap();
        map.put("previousId", getPreviousId());
        return map;
    }

    /**
     *
     * @return Every time null because it does not require Points
     */
    @Override
    public Collection<Point> getPoints()
    {
        return null;
    }
}
