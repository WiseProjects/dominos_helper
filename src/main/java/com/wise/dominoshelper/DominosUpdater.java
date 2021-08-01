package com.wise.dominoshelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wise.dominoshelper.http.HttpClient;
import com.wise.dominoshelper.pizza.PizzaInfo;
import com.wise.dominoshelper.utils.Env;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpGet;

/**
 *
 * @author Wise
 */
public class DominosUpdater {

    private static final String MENU_PIZZA_URL = "https://dominos.by/pizza";
    private final HttpClient http = new HttpClient();
    private final Map<String, PizzaInfo> pizzaMenu = new HashMap<>();

    public DominosUpdater() {

    }

    public void getMenu() {
        try {
            HttpGet request = new HttpGet(MENU_PIZZA_URL);
            http.get(request);
            String body = http.getData();

            Pattern p = Pattern.compile("product-card product-card--vertical.*?modifications");
            Matcher m = p.matcher(body);
            pizzaMenu.clear();
            while (m.find()) {
                String name = m.group(0).replaceAll(".*product-card__title\">(.*?)<.*", "$1").trim().toLowerCase().replace("zz", "ц");
                String descr = m.group(0).replaceAll(".*card__description\">(.*?)<.*", "$1").trim().replace("&#x27;", "'");
                String code = m.group(0).replaceAll(".*data-code=\"(.*?)\".*", "$1");
                pizzaMenu.put(name, new PizzaInfo(name, code, descr));
            }

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            Env.MENU_PIZZA = g.toJson(pizzaMenu);
            //Files.write(Paths.get(Env.MENU_PIZZA_PATH), menu.getBytes("utf-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
