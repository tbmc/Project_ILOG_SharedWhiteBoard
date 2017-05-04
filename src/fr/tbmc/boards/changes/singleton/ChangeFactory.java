package fr.tbmc.boards.changes.singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.Change;
import fr.tbmc.boards.changes.DefaultChange;
import fr.tbmc.boards.changes.Type;

import java.awt.Point;
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

    public Change createDefaultChange(Type type, User user, Point begin, Point end)
    {
        Date d = new Date();
        String id = UUID.randomUUID().toString();

        DefaultChange dc = new DefaultChange(type, user, d, begin, end, id);
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
     *          ]
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

        if(RECTANGLE == type || CIRCLE == type || LINE == type) {
            List points = (List) map.get("points");
            HashMap p1 = (HashMap) points.get(0);
            HashMap p2 = (HashMap) points.get(1);
            return createDefaultChange(type, user, pointFromMap(p1), pointFromMap(p2));
        }

        return null;
    }

    protected Point pointFromMap(Map m) {
        int x, y;
        x = (int) m.get("x");
        y = (int) m.get("y");
        return new Point(x, y);
    }

}
