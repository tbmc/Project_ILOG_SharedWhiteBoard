package fr.tbmc.boards.changes;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.*;

/**
 * Created by tbmc on 04/05/2017.
 */
public class DefaultChange extends AbstractChange
{

    private Point begin, end;

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

    public Point getBegin()
    {
        return begin;
    }

    public Point getEnd()
    {
        return end;
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = getJsonStructure();
        map.put("points", getPoints());
        return map;
    }

    @Override
    public Collection<Point> getPoints()
    {
        java.util.List<Point> list = new ArrayList<>();
        list.add(getBegin());
        list.add(getEnd());
        return list;
    }


}
