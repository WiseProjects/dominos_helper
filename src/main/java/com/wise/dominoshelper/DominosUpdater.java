package com.wise.dominoshelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import com.wise.dominoshelper.http.HttpClient;
import com.wise.dominoshelper.pizza.PizzaInfo;
import com.wise.dominoshelper.utils.Env;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
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

    private static final String MENU_PIZZA_URL = "https://dominos.by/api/web/products?city_id=2&lang=ru";
    private static final String KEEP_ALIVE_URL = "https://alicepizza.herokuapp.com/pizzamenu";
    private final HttpClient http = new HttpClient();
    private final Map<String, PizzaInfo> pizzaMenu = new HashMap<>();
    private final HttpGet keepalive = new HttpGet(KEEP_ALIVE_URL);
    private final HttpGet getMenu = new HttpGet(MENU_PIZZA_URL);

    public void getMenu() {
        try {
            http.get(getMenu);
            String body = http.getData();

            List<Map<String, Object>> menu = JsonPath.read(body, "$.entities.products.*[?(@.category == 'pizza')]");

            for (Map<String, Object> map : menu) {
                String title = map.get("title").toString().toLowerCase().trim().replace("zz", "ц");
                String description = map.get("toppingDescription").toString();
                String code = getCode(map.get("modifications"));
                pizzaMenu.put(title, new PizzaInfo(title, code, description));
            }

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            Env.MENU_PIZZA = g.toJson(pizzaMenu);
            
            http.get(keepalive);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private String getCode(Object mod) {
        return mod.toString().replaceAll(".*?\\\"(.*?)\\\".*", "$1").replaceAll("\\d+", "");
    }
}
