package com.lab.text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        String text = readFileInOneString("text.txt");

        // splitting into sentences
        List<String> sentences = new ArrayList<>();
        int startInd = 0;
        int endInd;
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == '.'
                    || text.charAt(i) == '!'
                    || text.charAt(i) == '?'
                    || i == text.length() - 1) {
                endInd = i;
                if(text.charAt(startInd) == ' ') startInd++;
                if(text.charAt(startInd) == '\r') startInd += 2;
                sentences.add(text.substring(startInd, endInd+1));
                startInd = i+1;
            }
        }
        for(String s : sentences) {
            System.out.println(s);
        }

        // deleting sentences that take more than 1 line
        List<String> deleted = new ArrayList<>();
        for (String sentence : sentences) {
            boolean flag = false;
            for (int j = 0; j < sentence.length(); j++) {
                if (sentence.charAt(j) == '\n') {
                    flag = true;
                    break;
                }
            }
            if (flag) continue;
            deleted.add(sentence);
        }
        System.out.println("Deleting sentences that take more than 1 line:");
        for(String s : deleted)
            System.out.println(s);

        // finding possible file paths
        List<String> paths = new ArrayList<>();
        for(String sentence : sentences) {
            startInd = 0;
            for(int i = 0; i < sentence.length(); i++) {
                String word;
                if(sentence.charAt(i) == ' '
                        || sentence.charAt(i) == '\r'
                        || sentence.charAt(i) == ',') {
                    endInd = i;
                    if(text.charAt(startInd) == ' ') startInd++;
                    if(text.charAt(startInd) == '\r') startInd += 2;
                    word = sentence.substring(startInd, endInd+1);
                    for(int k = 0; k < word.length(); k++) {
                        if(word.charAt(k) == '/') {
                            paths.add(word);
                            break;
                        }
                    }
                    startInd = i + 1;
                }
            }
        }

        System.out.println("Possible paths: ");
        for(String path : paths) {
            System.out.println(path);
        }
    }

    public static String readFileInOneString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "UTF-8");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
        return sb.toString();
    }
}
