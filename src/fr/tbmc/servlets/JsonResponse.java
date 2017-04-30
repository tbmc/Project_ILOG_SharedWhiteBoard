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
    public static void r(HttpServletResponse resp, Object o) throws IOException
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(o));
        out.flush();
    }
}
