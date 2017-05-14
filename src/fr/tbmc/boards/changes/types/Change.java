package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tbmc on 04/05/2017.
 *
 * Interface which represents necessary methods to a Change
 *
 */
public interface Change
{

    /**
     * Return the type of the Change
     * @return Type of this Change
     */
    Type getType();

    /**
     * Return the User of who did the Change
     * @return User who did the Change
     */
    User getUser();

    /**
     * Return the Date of the Change
     * @return Date of the Change
     */
    Date getDate();

    /**
     * Return the identifier of the Change
     * @return identifier of the Change
     */
    String getIdentifier();

    /**
     * Return the Color of the Change
     * @return Color of the Change
     */
    Color getColor();

    /**
     * Return the thickness of the Change
     * @return thickness of the Change
     */
    int getThickness();

    /**
     * Return a boolean which indicate if the Change is in "fill" mode
     * @return Boolean which indicate if the Change is "fill"
     */
    boolean isFilled();

    /**
     * Return a Map containing all the data of the Change which will be converted in a JSON object
     * @return Map which will be converted in a JSON object
     */
    Map<String, Object> toMap();

    /**
     * Return a Collection of all coordinates necessary to draw the figure
     * @return Collection of all coordinates necessary to draw the figure
     */
    Collection<Point> getPoints();

    /**
     * Return a Collection of Maps corresponding to the Collection of Changes in parameter
     * @param changeList Collection of Changes to convert to Collection of Maps
     * @return Collection of Maps which represents each Change in Map with this function
     * {@link Change#toMap()}
     */
    static Collection<Map<String, Object>> toMapList(Collection<Change> changeList) {
        List<Map<String, Object>> out = new ArrayList<>(changeList.size());
        for(Change change : changeList) {
            out.add(change.toMap());
        }
        return out;
    }

}
