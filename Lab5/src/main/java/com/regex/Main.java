package com.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        String text = "";
        String line = sc.nextLine();
        while(!line.equals("stop")){
            text += line;
            line = sc.nextLine();
        }
        List<String> list = find(text);
        for(String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> find(String text) {
        List<String> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^.?!])*\\s(\\w+)\\s(\\w+)([.!?])");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            list.add(text.substring(matcher.start(), matcher.end()));
        }
        pattern = Pattern.compile("\\w+(?=\\s+\\w+[.!?])");
        for(String s : list) {
            matcher = pattern.matcher(s);
            while (matcher.find()) {
                result.add(s.substring(matcher.start(), matcher.end()));
            }
        }
        return result;
    }
}
