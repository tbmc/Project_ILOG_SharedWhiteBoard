package fr.tbmc.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by tbmc on 10/05/2017.
 */
public class SessionListener implements HttpSessionListener
{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {
        HttpSession session = httpSessionEvent.getSession();
        SessionBindingListener sbl = SessionBindingListener.getInstance();
        sbl.registerSession(session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {

    }
}
