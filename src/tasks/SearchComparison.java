package tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SearchComparison {

    public static int sequentialSearch(int[] arr, int key) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == key) {
                return comparisons;
            }
        }
        return comparisons;
    }

    public static int binarySearch(int[] arr, int key) {
        int comparisons = 0;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (arr[mid] == key) {
                return comparisons;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return comparisons;
    }

    public static int interpolationSearch(int[] arr, int key) {
        int comparisons = 0;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && key >= arr[low] && key <= arr[high]) {
            comparisons++;
            if (low == high) {
                if (arr[low] == key) {
                    return comparisons;
                }
                return comparisons;
            }

            int position = low + (((high - low) / (arr[high] - arr[low])) * (key - arr[low]));

            if (arr[position] == key) {
                return comparisons;
            }

            if (arr[position] < key) {
                low = position + 1;
            } else {
                high = position - 1;
            }
        }
        return comparisons;
    }

    public static void search(String[] args) {

        String inputFile = args[0];
        String outputFile = args[1];

        File input = new File(inputFile);
        File output = new File(outputFile);

        try {
            Scanner scanner = new Scanner(input);
            FileWriter writer = new FileWriter(output);

            int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = arr.length;

            int totalComparisonsSeq = 0;
            int totalComparisonsBinary = 0;
            int totalComparisonsInterpolation = 0;

            for (int i = 1; i <= n; i++) {
                totalComparisonsSeq += sequentialSearch(arr, i);
                Arrays.sort(arr);
                totalComparisonsBinary += binarySearch(arr, i);
                totalComparisonsInterpolation += interpolationSearch(arr, i);
            }

            double avgComparisonsSeq = (double) totalComparisonsSeq / n;
            double avgComparisonsBinary = (double) totalComparisonsBinary / n;
            double avgComparisonsInterpolation = (double) totalComparisonsInterpolation / n;

            writer.write("Average Comparisons for Sequential Search: " + avgComparisonsSeq + "\n");
            writer.write("Average Comparisons for Binary Search: " + avgComparisonsBinary + "\n");
            writer.write("Average Comparisons for Interpolation Search: " + avgComparisonsInterpolation + "\n");

            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}