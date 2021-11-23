package com.threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGenerator {
    public static void main(String[] args) throws IOException {
        genFile("E:\\univer\\5semester\\cp\\Crossplatform-programming\\Lab7\\files\\5000.txt", 5000, 10);
    }

    public static void genFile(String filename, int dimension, double maxNumber) throws IOException {
        Random random = new Random();
        int blockBeginning = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        StringBuilder row;

        for (int i = 0; i < dimension;) {
            int blockSize;
            if (dimension - i < 4) {
               blockSize = dimension - i;
            } else if (dimension - i < 6) {
                blockSize = 2;
            } else {
                blockSize = random.nextInt(5) + 2;
            }
            for (int k = 0; k < blockSize; ++k) {
                row = new StringBuilder();
                int j;
                for (j = 0; j < blockBeginning; ++j) {
                    row.append("0.0 ");
                }
                for (j = blockBeginning; j < blockBeginning + blockSize && j < dimension; ++j) {
                    row.append(random.nextInt((int) maxNumber * 2) - maxNumber);
                    row.append(" ");
                }
                for (j = blockBeginning + blockSize; j < dimension; ++j) {
                    row.append("0.0 ");
                }
                row.deleteCharAt(row.length() - 1);
                row.append("\n");
                writer.write(row.toString());
            }
            blockBeginning += blockSize;
            i += blockSize;
        }

        writer.close();
    }
}
