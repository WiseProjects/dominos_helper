package com.wise.dominoshelper;

import com.wise.dominoshelper.http.MyServer;
import com.wise.dominoshelper.utils.DoughTypes;
import com.wise.dominoshelper.utils.Env;
import java.io.File;

/**
 *
 * @author Wise
 */
public class Main {

    public static void main(String args[]) throws Exception {
        new File(Env.MENU_PIZZA_PATH).getParentFile().mkdirs();
        Thread menu = new Thread() {
            DominosUpdater du = new DominosUpdater();

            @Override
            public void run() {
                try {
                    while (true) {
                        du.getMenu();
                        System.out.println("Menu was updated");
                        Thread.sleep(1000 * 60 * 60);
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
