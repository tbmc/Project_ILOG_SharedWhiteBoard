package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.Color;
import java.awt.Point;
import java.util.*;

/**
 * Created by tbmc on 11/05/2017.
 */
public class PencilChange extends AbstractChange
{

    protected List<Point> points = null;

    public static PencilChange PencilChangeFromListHashMap(User user, Date date, String id, Color color, int thickness, List<Map> points) {
        List<Point> list = new ArrayList<>(points.size());
        for(Map map : points) {
            int x = (int) Math.round((Double) map.get("x"));
            int y = (int) Math.round((Double) map.get("y"));
            Point p = new Point(x, y);
            list.add(p);
        }
        PencilChange pc = new PencilChange(user, date, id, color, thickness, list);
        return pc;
    }

    public PencilChange(User user, Date date, String id, Color color, int thickness, List<Point> points)
    {
        super(Type.PENCIL, user, date, id, color, thickness, false);

        this.points = points;

    }

    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = getMetaDataMap();
        map.put("points", getPoints());
        return map;
    }

    @Override
    public Collection<Point> getPoints()
    {
        return points;
    }
}
