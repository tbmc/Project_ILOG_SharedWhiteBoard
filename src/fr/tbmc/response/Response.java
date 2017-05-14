package fr.tbmc.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tbmc on 04/05/2017.
 */
public class Response
{

    /**
     * Same as {@link Response#simple(HttpServletResponse, int)} with the default status code 200
     * @param resp
     * @throws IOException
     */
    public static void simple(HttpServletResponse resp) throws IOException
    {
        simple(resp, 200);
    }

    /**
     * Same as {@link Response#simple(HttpServletResponse, int, String)} with the default text ""
     * @param resp
     * @param status
     * @throws IOException
     */
    public static void simple(HttpServletResponse resp, int status) throws IOException
    {
        simple(resp, status, "");
    }

    /**
     * Send response to the client with the status and the text
     * @param resp HttpServletResponse corresponding to the client request
     * @param status Status code to send to the client
     * @param text Text to send to the client
     * @throws IOException when there is a problem when writing in the response
     */
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
     * @param resp HttpServletResponse corresponding to the client response
     * @param object Object to convert to JSON {@link com.google.gson.Gson#toJson(Object)}
     * @throws IOException
     */
    public static void json(HttpServletResponse resp, Object object) throws IOException
    {
        JsonResponse jr = new JsonResponse();
        jr.respond(resp, object);
    }



}
