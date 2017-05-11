package fr.tbmc.boards.changes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.tbmc.boards.User;
import fr.tbmc.boards.changes.singleton.ChangeFactory;
import fr.tbmc.boards.changes.types.Change;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tbmc on 10/05/2017.
 */
public class TestChange
{

    public static String jsonInString = "{\n" +
            "\t\"type\": \"LINE\",\n" +
            "\t\"points\": [\n" +
            "\t\t{ \"x\": 0, \"y\": 0 },\n" +
            "\t\t{ \"x\": 1, \"y\": 1 }\n" +
            "\t],\n" +
            "\t\"color\": \"#00ff00\",\n" +
            "\t\"thickness\": 2,\n" +
            "\t\"fill\": false\n" +
            "}";
    User user = new User("testPseudo");

    @Before
    public void setUp() {

    }

    @Test
    public void testChanges() {
        ChangeFactory cf = ChangeFactory.getInstance();
        Change c = cf.createFromJson(jsonInString, user);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // System.out.println(jsonInString);
        // System.out.println(gson.toJson(c.toMap()));
        //TODO: finir ce test
    }

}
