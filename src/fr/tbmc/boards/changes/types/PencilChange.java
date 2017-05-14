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
    /**
     * List of Points to draw the figure
     */
    protected List<Point> points = null;

    /**
     * Create a PencilChange from data in parameter
     * The main difference between the constructor is that with this method accepts a List of Map representing
     * the Points necessary, but the constructor needs a list of Points.
     * @param user
     * @param date
     * @param id
     * @param color
     * @param thickness
     * @param points List of Map. Maps have to contain "x" and "y" keys, corresponding respectively to the x and y parameters
     * @return Return a PencilChange from data in parameter
     */
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

    /**
     * Constructor which passes parameters to parent constructor
     * @param user
     * @param date
     * @param id
     * @param color
     * @param thickness
     * @param points List of Points need to draw the figure
     */
    public PencilChange(User user, Date date, String id, Color color, int thickness, List<Point> points)
    {
        super(Type.PENCIL, user, date, id, color, thickness, false);

        this.points = points;
    }

    /**
     * Same as {@link Change#toMap()}
     * @return
     */
    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = getMetaDataMap();
        map.put("points", getPoints());
        map.put("thickness", getThickness());
        map.put("color", getColorString());
        return map;
    }

    /**
     * Same as {@link Change#getPoints()}
     * @return
     */
    @Override
    public Collection<Point> getPoints()
    {
        return points;
    }
}
