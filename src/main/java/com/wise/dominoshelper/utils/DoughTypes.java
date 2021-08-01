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
        types.put("ХотДог борт", "HD");
        types.put("Классика", "");
        types.put("Ультратонкое", "ULT");
        types.put("Тонкое", "IT");
        types.put("Сырный борт", "CR");
    }

    public String getTypes22() {
        return "{\n"
                + "  \"Классика\": \"\",\n"
                + "}";
    }

    public String getTypes36() {
        Map res = new HashMap(types);
        res.remove("Ультратонкое");
        return g.toJson(res);
    }

    public String getTypes30() {
        return g.toJson(types);
    }
}
