package fr.tbmc.boards.changes.singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.Change;
import fr.tbmc.boards.changes.DefaultChange;
import fr.tbmc.boards.changes.Type;

import java.awt.*;
import java.util.*;
import java.util.List;

import static fr.tbmc.boards.changes.Type.*;
import static fr.tbmc.boards.changes.Type.CIRCLE;
import static fr.tbmc.boards.changes.Type.RECTANGLE;

/**
 * Created by tbmc on 04/05/2017.
 */
public class ChangeFactory
{

    protected ChangeFactory() {}
    protected static ChangeFactory INSTANCE;

    public static ChangeFactory getInstance() {
        if(INSTANCE == null)
            INSTANCE = new ChangeFactory();
        return INSTANCE;
    }

    public Change createDefaultChange(Type type, User user, Color color, int thickness, boolean isFilled, Point begin, Point end)
    {
        Date d = new Date();
        String id = UUID.randomUUID().toString();

        DefaultChange dc = new DefaultChange(type, user, d, id, color, thickness, isFilled, begin, end);
        return dc;
    }

    /**
     *
     * @param json
     *
     *      need json of this structure (values are example)
     *      {
     *          type: "LINE",
     *          points: [
     *              { x: 0, y: 0 }, // Point begin
     *              { x: 1, y: 1 }  // Point end
     *          ],
     *          color: "#00ff00",
     *          thickness: 2,
     *          fill: false,
     *      }
     *
     * @param user
     * @return
     */
    public Change createFromJson(String json, User user) {
        Gson gson = new GsonBuilder().create();
        HashMap map = gson.fromJson(json, HashMap.class);
        String strType = (String) map.get("type");
        Type type = Type.valueOf(strType);
        Color color = hexStringToColor((String) map.get("color"));

        int thickness = (int) Math.round((double) map.get("thickness"));
        boolean isFilled = (boolean) map.get("fill");

        if(RECTANGLE == type || CIRCLE == type || LINE == type) {
            List points = (List) map.get("points");
            Map p1 = (Map) points.get(0);
            Map p2 = (Map) points.get(1);
            return createDefaultChange(type, user, color, thickness, isFilled, pointFromMap(p1), pointFromMap(p2));
        }

        return null;
    }

    protected Point pointFromMap(Map m) {
        int x, y;
        x = (int) Math.round((double) m.get("x"));
        y = (int) Math.round((double) m.get("y"));
        return new Point(x, y);
    }

    /**
     *
     * @param colorStr e.g. "#FFFFFF"
     * @return
     */
    public static Color hexStringToColor(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

}
