package fr.tbmc.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tbmc on 04/05/2017.
 */
public class Response
{

    public static void simple(HttpServletResponse resp) throws IOException
    {
        simple(resp, 200);
    }

    public static void simple(HttpServletResponse resp, int status) throws IOException
    {
        simple(resp, status, "");
    }

    public static void simple(HttpServletResponse resp, int status, String text) throws IOException
    {
        resp.setContentType("text");
        resp.setStatus(status);

        PrintWriter out = resp.getWriter();
        out.print(text);

        // Force the writer to write in the output stream
        out.flush();
    }

    /**
     * Same as method
     * @see JsonResponse#respond(HttpServletResponse, Object)
     * except that it instantiate the class necessary to encore to JSON
     *
     * @param resp
     * @param object
     * @throws IOException
     */
    public static void json(HttpServletResponse resp, Object object) throws IOException
    {
        JsonResponse jr = new JsonResponse();
        jr.respond(resp, object);
    }



}
