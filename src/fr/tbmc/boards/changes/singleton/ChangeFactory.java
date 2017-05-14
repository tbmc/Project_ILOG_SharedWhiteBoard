package fr.tbmc.boards.changes.singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.types.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import static fr.tbmc.boards.changes.types.Type.*;
import static fr.tbmc.boards.changes.types.Type.CIRCLE;
import static fr.tbmc.boards.changes.types.Type.RECTANGLE;

/**
 * Created by tbmc on 04/05/2017.
 *
 */
public class ChangeFactory
{

    /**
     * protected constructor to prevent user to instantiate it
     */
    protected ChangeFactory() {}

    /**
     * Static instance to return when needed
     */
    protected static ChangeFactory INSTANCE;

    /**
     * Return instance of ChangeFactory
     * @return Instance of ChangeFactory
     */
    public static ChangeFactory getInstance() {
        if(INSTANCE == null)
            INSTANCE = new ChangeFactory();
        return INSTANCE;
    }

    /**
     * Create a DefaultChange
     * {@link DefaultChange#DefaultChange(Type, User, Date, String, Color, int, boolean, Point, Point)}
     * @param type
     * @param user
     * @param color
     * @param thickness
     * @param isFilled
     * @param begin
     * @param end
     * @return
     */
    public Change createDefaultChange(Type type, User user, Color color, int thickness, boolean isFilled, Point begin, Point end)
    {
        Date date = new Date();
        String id = UUID.randomUUID().toString();

        DefaultChange dc = new DefaultChange(type, user, date, id, color, thickness, isFilled, begin, end);
        return dc;
    }

    /**
     * Create a change from a JSON text.
     * {@link ChangeFactory#createDefaultChange(Type, User, Color, int, boolean, Point, Point)}
     * {@link ChangeFactory#createFromJson(String, User)}
     * {@link ChangeFactory#createClearChange(User)}
     * {@link ChangeFactory#createPencilChange(User, Color, int, List)}
     * {@link ChangeFactory#createUndoChange(User, String)}
     *
     * @param json JSON String
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
     * @param user User who send the changes
     * @return Change created from JSON
     */
    public Change createFromJson(String json, User user) {
        Gson gson = new GsonBuilder().create();
        // Convert JSON object to a HashMap
        HashMap map = gson.fromJson(json, HashMap.class);

        // Get the type of in the Map
        String strType = (String) map.get("type");
        Type type = Type.getTypeFromString(strType);

        Color color = null;
        int thickness = 0;
        boolean isFilled = false;

        // If the type has the following parameters
        if(type != CLEAR && type != UNDO) {
            color = hexStringToColor((String) map.get("color"));
            thickness = (int) Math.round((double) map.get("thickness"));
            isFilled = (boolean) map.get("fill");
        }


        List points;

        // Select the good method corresponding to the type
        switch(type) {
            case RECTANGLE:
            case CIRCLE:
            case LINE:
                points = (List) map.get("points");
                Map p1 = (Map) points.get(0);
                Map p2 = (Map) points.get(1);
                return createDefaultChange(type, user, color, thickness, isFilled, pointFromMap(p1), pointFromMap(p2));

            case PENCIL:
                points = (List) map.get("points");
                return createPencilChange(user, color, thickness, points);

            case CLEAR:
                return createClearChange(user);

            case UNDO:
                String previousId = (String) map.get("previousId");
                return createUndoChange(user, previousId);


            // If the type is not good
            default:
                return null;
        }
    }

    /**
     * Create a PencilChange
     * {@link PencilChange#PencilChange(User, Date, String, Color, int, List)}
     * @param user
     * @param color
     * @param thickness
     * @param list
     * @return
     */
    public Change createPencilChange(User user, Color color, int thickness, List list) {
        Date date = new Date();
        String id = UUID.randomUUID().toString();
        PencilChange pc = PencilChange.PencilChangeFromListHashMap(user, date, id, color, thickness, list);
        return pc;
    }

    /**
     * Create an UndoChange
     * {@link UndoChange#UndoChange(User, Date, String, String)}
     * @param user
     * @param previousId
     * @return
     */
    public Change createUndoChange(User user, String previousId) {
        Date date = new Date();
        String id = UUID.randomUUID().toString();
        UndoChange uc = new UndoChange(user, date, id, previousId);
        return uc;
    }

    /**
     * Create a ClearChange
     * {@link ClearChange#ClearChange(User, Date, String)}
     * @param user
     * @return
     */
    public Change createClearChange(User user) {
        Date date = new Date();
        String id = UUID.randomUUID().toString();
        ClearChange cc = new ClearChange(user, date, id);
        return cc;
    }

    /**
     * Create Point from a Map
     * @param m Map to convert in Point
     * @return Point corresponding to the Map parameter
     */
    protected static Point pointFromMap(Map m) {
        int x, y;
        x = (int) Math.round((double) m.get("x"));
        y = (int) Math.round((double) m.get("y"));
        return new Point(x, y);
    }

    /**
     * Convert a String of the form "#AABBCC"
     * @param colorStr e.g. "#AABBCC"
     * @return Color corresponding to the String in parameter
     */
    public static Color hexStringToColor(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

}
