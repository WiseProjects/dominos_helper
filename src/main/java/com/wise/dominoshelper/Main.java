package com.wise.dominoshelper;

import com.wise.dominoshelper.http.MyServer;

/**
 *
 * @author Wise
 */
public class Main {

    public static void main(String args[]) throws Exception {
        Thread menu = new Thread() {
            DominosUpdater du = new DominosUpdater();

            @Override
            public void run() {
                try {
                    while (true) {
                        du.getMenu();
                        System.out.println("Menu was updated");
                        Thread.sleep(1000 * 60 * 29);
                    }
                } catch (InterruptedException v) {
                    v.printStackTrace();
                }
            }
        };

        menu.start();

        MyServer server = new MyServer();
        server.start();
    }
}
