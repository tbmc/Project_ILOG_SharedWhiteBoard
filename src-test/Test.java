import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.List;

/**
 * Created by tbmc on 04/05/2017.
 */
public class Test
{

    /**
     * TODO: remove these test
     * they are just experimentation
     */

    public static void testHashMap() {
        GsonBuilder gson = new GsonBuilder();
        Gson g = gson.create();
        HashMap<String, Object> m = new HashMap<>();
        m.put("test", 2);
        m.put("truc", 4);
        List<String> ls = new LinkedList<>();
        ls.add("orjkhiop");
        ls.add("ôdfg,njkoln");
        m.put("list", ls);
        System.out.println(g.toJson(m));
        Object r = g.fromJson(g.toJson(m), HashMap.class);
        System.out.println(((HashMap) r).get("list").getClass());
        System.out.println(r);
    }

    public static void testPoint() {
        GsonBuilder gson = new GsonBuilder().setPrettyPrinting();
        Gson g = gson.create();

        /*ChangeFactory cf = ChangeFactory.getInstance();
        Change c = cf.createDefaultChange(Type.CIRCLE, new User("test"), new Point(1, 2), new Point(3, 4));
        TestChangeList cl = new TestChangeList();
        cl.add(c);


        System.out.println(g.toJson(cl));*/
    }

    public static void main(String[] args)
    {


        testPoint();
    }

}
