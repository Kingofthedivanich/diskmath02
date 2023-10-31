package tasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class InsertionSort {
    public static void search(String[] args) {


        String inputFile = args[2];
        String outputFile = args[3];

        try {
            int[] array = readArrayFromFile(inputFile);
            int comparisons = insertionSort(array);
            writeArrayAndComparisonsToFile(outputFile, array, comparisons);
        } catch (IOException e) {
            System.out.println("An error occurred while reading or writing the files.");
            e.printStackTrace();
        }
    }

    private static int[] readArrayFromFile(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = reader.readLine();
        String[] numbers = line.split(" ");
        int[] array = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            array[i] = Integer.parseInt(numbers[i]);
        }
        reader.close();
        return array;
    }

    private static int insertionSort(int[] array) {
        int comparisons = 0;
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
                comparisons++;
            }

            array[j + 1] = key;
        }
        return comparisons;
    }

    private static void writeArrayAndComparisonsToFile(String outputFile, int[] array, int comparisons) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(Arrays.toString(array));
        writer.newLine();
        writer.write(String.valueOf(comparisons));
        writer.close();
    }
}
