package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.*;

/**
 * Created by tbmc on 04/05/2017.
 *
 * Class representing different types of Change
 * Represents all the types of Change which need the same parameters
 *
 */
public class DefaultChange extends AbstractChange
{

    /**
     * Points which are the begin and the end of the figure
     */
    private Point begin, end;

    /**
     * Pass parameters to parent constructor
     * {@link AbstractChange#AbstractChange(Type, User, Date, String, Color, int, boolean)}
     * @param type
     * @param user
     * @param date
     * @param id
     * @param color
     * @param thickness
     * @param isFilled
     * @param begin Point which is the begin of the figure
     * @param end Point which is the end of the figure
     */
    public DefaultChange(
            Type type, User user, Date date,
            String id, Color color, int thickness,
            boolean isFilled,
            Point begin, Point end
    ) {
        super(type, user, date, id, color, thickness, isFilled);

        this.begin = begin;
        this.end = end;
    }

    /**
     * Return the Point corresponding at the begin of the figure
     * @return Point corresponding at the begin of the figure
     */
    public Point getBegin()
    {
        return begin;
    }

    /**
     * Return the Point corresponding at the end of the figure
     * @return Point corresponding at the end of the figure
     */
    public Point getEnd()
    {
        return end;
    }

    /**
     * Implementing the method of the interface {@link Change}
     * {@link Change#getPoints()}
     * @return
     */
    @Override
    public Collection<Point> getPoints()
    {
        java.util.List<Point> list = new ArrayList<>();
        list.add(getBegin());
        list.add(getEnd());
        return list;
    }

    /**
     * Implementing the method of the interface {@link Change}
     * {@link Change#toMap()}
     * @return
     */
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = getMetaDataMap();
        map.put("color", getColorString());
        map.put("thickness", getThickness());
        map.put("fill", isFilled());
        map.put("points", getPoints());
        return map;
    }

}
