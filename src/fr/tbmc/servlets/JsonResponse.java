package fr.tbmc.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tbmc on 29/04/2017.
 */
public class JsonResponse
{
    /**
     * Encode object in JSON and write it in the output stream
     * @param resp Response object pass in parameter at the method doGet or doPost or any other method doXxx
     * @param object Object to convert in JSON
     * @throws IOException
     */
    public static void r(HttpServletResponse resp, Object object) throws IOException
    {
        // Create a Gson object
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        // Define the content type of the response
        // We return json so we tell the client that the response is JSON
        resp.setContentType("application/json");

        // Write json in the output stream
        PrintWriter out = resp.getWriter();

        // Convert object in JSON
        String jsonToSend = gson.toJson(object);

        // Write JSON object in the output stream
        out.print(jsonToSend);

        // Force the writer to write in the output stream
        out.flush();
    }
}
