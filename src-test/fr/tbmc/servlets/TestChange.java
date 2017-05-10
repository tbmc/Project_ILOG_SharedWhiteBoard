package fr.tbmc.servlets;

import fr.tbmc.boards.User;
import fr.tbmc.boards.singleton.CreatedBoards;
import fr.tbmc.servlets.boards.BoardList;
import fr.tbmc.servlets.boards.CreateBoard;
import fr.tbmc.servlets.canvas.DoChange;
import fr.tbmc.servlets.canvas.GetChange;
import fr.tbmc.servlets.users.JoinBoard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by tbmc on 10/05/2017.
 */
public class TestChange extends Mockito
{

    @Test
    public void test() throws IOException, ServletException, ClassNotFoundException
    {
        createBoard();
        joinBoard();
        testDoChange();
        testDoChange();
        testGetChange();
    }

    public void createBoard() throws IOException, ServletException
    {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        when(request.getParameter("name")).thenReturn("boardName");
        TestCreateBoard tcb = new TestCreateBoard();
        tcb.doPostTest(request, response);
    }


    public void joinBoard() throws IOException, ServletException
    {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        when(request.getParameter("BoardName")).thenReturn("boardName");
        when(request.getParameter("pseudo")).thenReturn("testPseudo");
        TestJoinBoard tjb = new TestJoinBoard();
        tjb.doGetTest(request, response);
    }


    public void testDoChange() throws ServletException, IOException
    {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();

        when(request.getParameter("data")).thenReturn(fr.tbmc.boards.changes.TestChange.jsonInString);

        TestChangeDoPost tcdp = new TestChangeDoPost();
        tcdp.doPostTest(request, response);

        System.out.println();
    }

    public void testGetChange() throws ClassNotFoundException, IOException, ServletException
    {
        CreatedBoards cb = CreatedBoards.getInstance();
        String id = cb.getLastChangeIdentifierFromBoard("boardName");

        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();

        when(request.getParameter("lastId")).thenReturn(id);

        TestChangeGetGet tcgg = new TestChangeGetGet();
        tcgg.doGetTest(request, response);

    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpSession session = mock(HttpSession.class);

        when(session.getAttribute("user")).thenReturn(new User("test"));
        when(session.getAttribute("boardName")).thenReturn("boardName");

        when(request.getSession()).thenReturn(session);

        return request;
    }

    public HttpServletResponse getResponse() throws IOException
    {
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = new PrintWriter(System.out);
        when(response.getWriter()).thenReturn(writer);

        return response;
    }

    public class TestChangeDoPost extends DoChange {
        public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            doPost(req, resp);
        }
    }
    public class TestCreateBoard extends CreateBoard {
        public void doPostTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            doPost(req, resp);
        }
    }
    public class TestJoinBoard extends JoinBoard {
        public void doGetTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            doGet(req, resp);
        }
    }
    public class TestChangeGetGet extends GetChange {
        public void doGetTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            doGet(req, resp);
        }
    }

}
