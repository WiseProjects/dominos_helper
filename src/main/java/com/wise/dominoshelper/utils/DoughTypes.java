package com.wise.dominoshelper.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Wise
 */
public class DoughTypes {

    private Map<String, String> types = new HashMap();
    private Gson g = new GsonBuilder().setPrettyPrinting().create();

    public DoughTypes() {
        types.put("хотдог борт", "HD");
        types.put("классика", "");
        types.put("ультратонкое", "ULT");
        types.put("тонкое", "IT");
        types.put("сырный борт", "CR");
    }

    public String getTypes22() {
        return "{\n"
                + "  \"классика\": \"\",\n"
                + "}";
    }

    public String getTypes36() {
        Map res = new HashMap(types);
        res.remove("ультратонкое");
        return g.toJson(res);
    }

    public String getTypes30() {
        return g.toJson(types);
    }
}
