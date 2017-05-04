package fr.tbmc.boards.changes;

import fr.tbmc.boards.User;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by tbmc on 04/05/2017.
 */
public class DefaultChange implements Change
{

    protected User user;
    protected Date date;
    protected Type type;

    public Point getBegin()
    {
        return begin;
    }

    public Point getEnd()
    {
        return end;
    }

    protected Point begin, end;

    @Override
    public String getIdentifier()
    {
        return identifier;
    }

    @Override
    public Collection<Point> getPoints()
    {
        List<Point> list = new ArrayList<>();
        list.add(getBegin());
        list.add(getEnd());
        return list;
    }

    protected String identifier;

    public DefaultChange(Type type, User user, Date date, Point begin, Point end, String id) {
        this.user = user;
        this.type = type;
        this.date = date;

        this.begin = begin;
        this.end = end;

        this.identifier = id;
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
    public String toJson()
    {
        return null;
    }
}
