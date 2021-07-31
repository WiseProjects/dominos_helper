package com.wise.dominoshelper;

import org.eclipse.jetty.servlet.ServletHandler;

/**
 *
 * @author Wise
 */
public class Main {

    public static void main(String args[]) throws Exception {
        MyServer server = new MyServer();
        server.start();
    }
}
