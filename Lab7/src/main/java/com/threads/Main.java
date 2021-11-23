package com.threads;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final String directory = "E:\\univer\\5semester\\cp\\Crossplatform-programming\\Lab7\\files\\";
    public static final int matrixDimension = 5000;

    public static void main(String[] args) {
        var blockMatrix = new BlockDiagMatrix();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of matrix: ");
        blockMatrix.generateRand(scanner.nextInt());

        System.out.print("Enter the quantity of threads: ");
        int threadCount = scanner.nextInt();
        scanner.nextLine();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; ++i) {
            threads.add(new Thread(()-> {
                try {
                    while (true) {
                        if (blockMatrix.workWithNextBlock()) {
                            return;
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }));
        }
        ThreadMonitor monitor = new ThreadMonitor(threads);


        JFrame frame = new JFrame("CP7");
        JTextArea area = new JTextArea();
        JScrollPane pane = new JScrollPane(area);
        frame.add(pane);
        monitor.startDisplaying(area, 10);

        System.out.print("Use ExecutorService (Y/N)? ");
        String str = scanner.nextLine();
        long startTime = System.nanoTime();
        if (str.equals("Y")) {
            ExecutorService executor = Executors.newFixedThreadPool(threads.size());
            for (var thread: threads) {
                executor.submit(thread);
            }
        } else {
            for (var thread: threads) {
                thread.start();
            }
        }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 500);


        var timer = new java.util.Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (blockMatrix.allBlocksSolved()) {
                    timer.cancel();
                    monitor.stopDisplaying();
                    long endTime = System.nanoTime();
                    double timeElapsed = (endTime - startTime) / 1E6;

                    System.out.println("Matrix Gauss solution took " + timeElapsed + "ms");
                    System.out.print("Save result matrix (Y/N)? ");
                    String str = scanner.nextLine();
                    if (str.equals("N")) {
                        return;
                    }
                    System.out.print("Enter filename: ");
                    str = scanner.nextLine();
                    try {
                        blockMatrix.writeToFile(directory + str);
                        System.out.println("Saved!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(task, 0, 1);
    }

}
