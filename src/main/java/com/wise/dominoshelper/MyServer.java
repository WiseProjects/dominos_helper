package com.wise.dominoshelper;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 *
 * @author Wise
 */
public class MyServer {

    private Server server;
    int maxThreads = 100;
    int minThreads = 10;
    int idleTimeout = 120;

    public void start() throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);
        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        //connector.setHost("127.0.0.1");
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(TestServlet.class, "/status");

        server.start();
    }

    void stop() throws Exception {
        server.stop();
    }
}
