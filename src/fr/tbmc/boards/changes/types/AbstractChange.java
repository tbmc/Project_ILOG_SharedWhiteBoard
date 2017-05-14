package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.*;

/**
 * Created by tbmc on 10/05/2017.
 *
 * Abstract class which implements Change interface
 * and implements some methods and let necessary method
 * to be redefined to child classes
 *
 */
public abstract class AbstractChange implements Change
{

    /**
     * User of the Change
     */
    private User user;

    /**
     * Date of the Change
     */
    private Date date;

    /**
     * Type of the Change
     */
    private Type type;

    /**
     * Identifier of the Change
     */
    private String identifier;

    /**
     * Color of the Change
     */
    private Color color;

    /**
     * Thickness of the Change
     */
    private int thickness;

    /**
     * Boolean indicating if the boolean is filled or not
     */
    private boolean isFilled;

    /**
     * Constructor necessary to get values to each parameters
     * @param type Type of the Change
     * @param user User of the Change
     * @param date Date of the Change
     * @param id identifier of the Change
     * @param color Color of the Change
     * @param thickness thickness of the Change
     * @param isFilled boolean indicating if the Change is filled
     */
    public AbstractChange(Type type, User user, Date date, String id,
                          Color color, int thickness, boolean isFilled
    ) {
        /**
         * Attributing all parameter values to attributes
         */
        this.user = user;
        this.type = type;
        this.date = date;

        this.identifier = id;

        this.color = color;
        this.thickness = thickness;
        this.isFilled = isFilled;
    }

    /**
     * Same as {@link Change#getIdentifier()}
     * @return
     */
    @Override
    public String getIdentifier()
    {
        return identifier;
    }

    /**
     * Same as {@link Change#getType()}
     * @return
     */
    @Override
    public Type getType()
    {
        return type;
    }

    /**
     * Same as {@link Change#getUser()}
     * @return
     */
    @Override
    public User getUser()
    {
        return user;
    }

    /**
     * Same as {@link Change#getDate()}
     * @return
     */
    @Override
    public Date getDate()
    {
        return date;
    }

    /**
     * Same as {@link Change#getColor()}
     * @return
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Return the Color of the Change in a String
     * @return String representing the Color in parameter
     */
    public String getColorString() {
        return colorToHexString(getColor());
    }

    /**
     * Same as {@link Change#getThickness()}
     * @return
     */
    @Override
    public int getThickness() {
        return thickness;
    }

    /**
     * Same as {@link Change#isFilled()}
     * @return
     */
    @Override
    public boolean isFilled() {
        return isFilled;
    }

    /**
     * Get a Map which is designed to be converted to a JSON object
     * Map contains following data: type, date, user and id
     * @return Map containing all the data necessary to all Changes
     */
    public Map<String, Object> getMetaDataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", getType());
        map.put("date", getDate());
        map.put("user", getUser().toString());
        map.put("id", getIdentifier());
        return map;
    }

    /**
     * Convert the Color in parameter to a String
     * @param color Color to convert
     * @return String representing the Color in parameter
     */
    public static String colorToHexString(Color color) {
        if(color == null)
            return "";
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

}
