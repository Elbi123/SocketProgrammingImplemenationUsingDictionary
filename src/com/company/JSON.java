package com.company;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;

public class JSON {
    private static PrintWriter pw;
    public static void main(String[] args) {
        writeToJSON();
    }

    public static void writeToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("word_1", "meaning_1");
        jo.put("word_2", "meaning_2");
        jo.put("word_3", "meaning_3");

        try {
            pw = new PrintWriter("JSONExample.json");
            pw.write(jo.toJSONString());
            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex){
            System.out.println(ex);
        }
    }
}
