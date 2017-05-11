package fr.tbmc.boards.changes;

import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.singleton.ChangeFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by tbmc on 10/05/2017.
 */
public class TestChangeList
{

    User user = new User("testPseudo"), user2 = new User("User2");
    ChangeFactory cf = ChangeFactory.getInstance();
    Change c = cf.createFromJson(TestChange.jsonInString, user);
    Change c2 = cf.createFromJson(TestChange.jsonInString, user2);
    ChangeList cl = new ChangeList();

    @Test
    public void testLength() {
        cl.add(c);
        cl.clear();
        assertEquals(0, cl.size());
        for(int i = 0; i < 600; i++) {
            cl.add(c);
        }
        assertEquals(500, cl.size());
        cl.setMaxSize(300);
        assertEquals(300, cl.size());
    }

    @Test
    public void testFromLastIdentifier() {
        cl.clear();
        cl.add(c);
        cl.add(c2);
        cl.add(c);
        cl.add(c);
        assertEquals(4, cl.size());
        ChangeList cl2 = cl.getListOfChangeSince(c2.getIdentifier());
        assertEquals(3, cl2.size());
        assertEquals(c.getIdentifier(), cl.getLastIdentifier());
    }

}
