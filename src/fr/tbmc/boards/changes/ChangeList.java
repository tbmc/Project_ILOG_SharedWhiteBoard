package fr.tbmc.boards.changes;

import fr.tbmc.boards.changes.types.Change;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by tbmc on 04/05/2017.
 */
public class ChangeList implements Collection<Change>
{

    public static int MAX_SIZE = 500;
    public int maxSize = MAX_SIZE;

    protected LinkedList<Change> list = new LinkedList<>();

    @Override
    public boolean add(Change change) {
        if(change == null)
            return false;
        synchronized (list) {
            list.addFirst(change);
            gc();
        }
        return true;
    }


    public void gc() {
        synchronized (list) {
            while(list.size() > maxSize) {
                list.removeLast();
            }
        }
    }

    public String getLastIdentifier() {
        String id;
        synchronized (list) {
            id = list.getFirst().getIdentifier();
        }
        return id;
    }

    public ChangeList getListOfChangeSince(String id) {
        ChangeList cl = new ChangeList();
        for(Change c : list) {
            cl.add(c);
            if(c.getIdentifier().equals(id)) {
                break;
            }
        }
        return cl;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        gc();
    }

    public int getMaxSize() {
        return maxSize;
    }


    @Override
    public boolean remove(Object o)
    {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Change> c)
    {
        boolean b = list.addAll(c);
        gc();
        return b;
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return list.retainAll(c);
    }

    @Override
    public void clear()
    {
        list.clear();
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return list.contains(o);
    }

    @Override
    public Iterator<Change> iterator()
    {
        return list.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return list.toArray();
    }

    @Override
    public <Change> Change[] toArray(Change[] a)
    {
        return list.toArray(a);
    }

}
