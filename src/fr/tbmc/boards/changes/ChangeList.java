package fr.tbmc.boards.changes;

import fr.tbmc.boards.changes.types.Change;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by tbmc on 04/05/2017.
 *
 * List of Changes
 *
 */
public class ChangeList implements Collection<Change>
{
    /**
     * Default maximum number of elements in the list
     */
    public static int MAX_SIZE = 500;

    /**
     * Maximum number of elements in the list
     */
    public int maxSize = MAX_SIZE;

    /**
     * List of elements
     * Choose a LinkedList because we need to add in first place
     */
    protected LinkedList<Change> list = new LinkedList<>();

    /**
     * Add a Change to the list
     * @param change Change to add
     * @return Boolean to know if the add is successful or not
     */
    @Override
    public boolean add(Change change) {
        if(change == null)
            return false;
        // To be sure that list is accessed by only one thread at the time
        synchronized (list) {
            list.addFirst(change);
            // To be sure the number of elements in the list is not exceeded
            gc();
        }
        return true;
    }

    /**
     * Check if the maximum number of elements is exceeded or not.
     * If the number is exceeded, it will remove the older elements.
     */
    public void gc() {
        synchronized (list) {
            while(list.size() > maxSize) {
                list.removeLast();
            }
        }
    }

    /**
     * Return the identifier of the last element added in the list.
     * This element is in first position in the list.
     * @return identifier of the last element added in the list
     */
    public String getLastIdentifier() {
        String id;
        synchronized (list) {
            id = list.getFirst().getIdentifier();
        }
        return id;
    }

    /**
     * Return another ChangeList containing all the Changes made
     * since the identifier in parameter
     * @param id identifier from which return the list of Changes
     * @return ChangeList containing all the Changes
     */
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

    /**
     * Set the maximum size of elements in the list
     * @param maxSize Maximum number elements in the list
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        gc();
    }

    /**
     * Return the maximum size of elements in the list
     * @return Maximum number of elements in the list
     */
    public int getMaxSize() {
        return maxSize;
    }


    /**
     *
     * Following function are methods that is necessary to
     * implements Collection interface
     * {@link Collection}
     *
     */




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
