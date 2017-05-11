package fr.tbmc.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by tbmc on 10/05/2017.
 */
public class SessionBindingListener implements HttpSessionBindingListener
{
    private static SessionBindingListener Instance;

    protected SessionBindingListener() {}

    public static SessionBindingListener getInstance() {
        if(Instance == null) {
            Instance = new SessionBindingListener();
        }
        return Instance;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent)
    {

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent)
    {

    }

    public void registerSession(HttpSession session) {
        //TODO: register to detect timeout from session
    }
}
