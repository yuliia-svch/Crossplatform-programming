package com.threads;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BlockDiagMatrix {
    private double[][][] blocks;
    private int[] blockPositions;
    private boolean[] solvedBlocks;
    private int nextBlock;
    private int dimension;
    private final Object mutex = new Object();

    public double[][][] getBlocks() {
        return blocks;
    }

    public int getBlockCount() {
        return blocks.length;
    }

    public void readFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        List<List<Double>> matrix = new ArrayList<>();
        List<Double> row = new ArrayList<>();

        int side = -1;
        while (scanner.hasNextLine()) {
            String sRow = scanner.nextLine();
            row  = Arrays.stream(sRow.split(" ")).map(Double::parseDouble).collect(Collectors.toList());

            if (side == -1) {
                side = row.size();
            } else {
                row = row.subList(0, side);
            }
            
            matrix.add(row);
            if (matrix.size() >= side) {
                break;
            }
        }

        List<Integer> blockPos = new ArrayList<>();
        List<double[][]> blocks = new ArrayList<>();
        for (int i = 0; i < matrix.size();) {
            int width;
            for (width = 0; width < matrix.size() - i; ++width) {
                if (matrix.get(i).get(i + width) == 0) {
                    break;
                }
            }
            if (width < 1) {
                break;
            }

            double[][] lastBlock = new double[width][];
            blocks.add(lastBlock);
            blockPos.add(i);

            for (int j = 0; j < width; ++j) {
                lastBlock[j] = new double[width];
                for (int k = 0; k < width; ++k) {
                    lastBlock[j][k] = matrix.get(i + j).get(i + k);
                }
            }
            i += width;
        }
        blockPositions = blockPos.stream().mapToInt(num -> num).toArray();
        this.blocks = blocks.toArray(double[][][]::new);
        this.solvedBlocks = new boolean[this.blocks.length];
        this.nextBlock = 0;
        this.dimension = side;
    }

    public void generateRand(int dimension) {
        Random random = new Random();
        List<double[][]> blocks = new ArrayList<>();
        List<Integer> blockPositions = new ArrayList<>();

        for (int i = 0, j = 0; i < dimension; ++j) {
            int blockSize;
            if (dimension - i < 4) {
                blockSize = dimension - i;
            } else if (dimension - i < 6) {
                blockSize = 2;
            } else {
                blockSize = random.nextInt(5) + 2;
            }
            double[][] lastBlock = new double[blockSize][blockSize];
            blocks.add(lastBlock);
            for (int k = 0; k < blockSize; ++k) {
                for (int l = 0; l < blockSize; ++l) {
                    lastBlock[k][l] = random.nextInt(20) - 10;
                }
            }

            blockPositions.add(i);
            i += blockSize;
        }

        this.blockPositions = blockPositions.stream().mapToInt(num -> num).toArray();
        this.blocks = blocks.toArray(double[][][]::new);
        this.dimension = dimension;
        this.nextBlock = 0;
        this.solvedBlocks = new boolean[this.blocks.length];
    }

    private void swapRaws(double[][] block, int row1, int row2) {
        double[] tempRow = block[row1];
        block[row1] = block[row2];
        block[row2] = tempRow;
    }

    private int indexOfMaximalValue(double[] array) {
        double max = -Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return index;
    }

    public void gaussMethod(int block) throws Exception {
        double[][] cBlock = blocks[block]; // current block
        for (int i = 0; i < cBlock.length - 1; ++i) {
            int rowMax = indexOfMaximalValue(Arrays.copyOfRange(cBlock[i], i,
                    cBlock.length)) + i;
            if (rowMax == -1) {
                System.out.println("Current block: " + Arrays.deepToString(cBlock));
                throw new Exception("Block #" + block + ", row #" + i + " is to be empty");
            }
            if (rowMax != i) {
                swapRaws(cBlock, i, rowMax);
            }

            for (int j = i + 1; j < cBlock.length; ++j) {
                double multiplier = cBlock[i][i] / cBlock[j][i];
                for (int k = i; k < cBlock.length; ++k) {
                    cBlock[j][k] -= cBlock[i][k] / multiplier;
                }
            }

        }
        solvedBlocks[block] = true;
    }

    public boolean workWithNextBlock() throws Exception {
        int localNextBlock;
        synchronized (mutex) {
            localNextBlock = nextBlock;
            if (localNextBlock >= blocks.length) {
                return true;
            }
            ++nextBlock;
        }
        gaussMethod(localNextBlock);
        return localNextBlock + 1 >= blocks.length;
    }

    public boolean allBlocksSolved() {
        synchronized (mutex) {
            return nextBlock >= blocks.length;
        }
    }

    public void writeToFile(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        StringBuilder row;

        for (int i = 0; i < blocks.length; ++i) {
            double[][] cBlock = blocks[i];
            for (int j = 0; j < cBlock.length; ++j) {
                row = new StringBuilder();
                int k;
                for (k = 0; k < blockPositions[i]; ++k) {
                    row.append("0.0 ");
                }
                for (k = 0; k < cBlock.length && k + blockPositions[i] < dimension; ++k) {
                    row.append(String.format("%.2f ", cBlock[j][k]));
                }
                for (k = blockPositions[i] + cBlock.length; k < dimension; ++k) {
                    row.append("0.0 ");
                }

                row.deleteCharAt(row.length() - 1);
                row.append("\n");
                writer.write(row.toString());
            }
        }
        writer.close();
    }
}
