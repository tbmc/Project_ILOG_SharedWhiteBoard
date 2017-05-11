package fr.tbmc.boards.changes.types;

import fr.tbmc.boards.User;

import java.awt.*;
import java.util.*;

/**
 * Created by tbmc on 10/05/2017.
 */
public abstract class AbstractChange implements Change
{

    private User user;
    private Date date;
    private Type type;

    private String identifier;
    private Color color;
    private int thickness;
    private boolean isFilled;

    public AbstractChange(Type type, User user, Date date, String id,
                          Color color, int thickness, boolean isFilled
    ) {
        this.user = user;
        this.type = type;
        this.date = date;

        this.identifier = id;

        this.color = color;
        this.thickness = thickness;
        this.isFilled = isFilled;
    }

    @Override
    public String getIdentifier()
    {
        return identifier;
    }
    @Override
    public Type getType()
    {
        return type;
    }
    @Override
    public User getUser()
    {
        return user;
    }
    @Override
    public Date getDate()
    {
        return date;
    }
    @Override
    public Color getColor() {
        return color;
    }
    public String getColorString() {
        return colorToHexString(getColor());
    }
    @Override
    public int getThickness() {
        return thickness;
    }
    @Override
    public boolean isFilled() {
        return isFilled;
    }

    public Map<String, Object> getMetaDataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", getType());
        map.put("date", getDate());
        map.put("user", getUser().toString());
        map.put("id", getIdentifier());
        return map;
    }

    public static String colorToHexString(Color color) {
        if(color == null)
            return "";
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

}