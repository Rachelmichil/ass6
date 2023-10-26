import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) {
        int[] randomArray = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = random.nextInt(101); // Generates random integers between 0 and 100
        }
        return randomArray;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.println(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int[] readFileToArray(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int count = 0;
            while (scanner.hasNextInt()) {
                scanner.nextInt();
                count++;
            }
            
            int[] array = new int[count];
    
            // Close the first scanner
            scanner.close();
    
            // Open a new scanner
            try (Scanner newScanner = new Scanner(new File(filename))) {
                for (int i = 0; i < count; i++) {
                    array[i] = newScanner.nextInt();
                }
            }
            
            return array;
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }
    

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your choice:");
        System.out.println("1. Generate an array of random integers and store it in a file");
        System.out.println("2. Read an existing file containing a list of integers, sort it, and store the sorted array in another file");

        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Enter the length of the array:");
            int arrayLength = scanner.nextInt();

            int[] randomArray = createRandomArray(arrayLength);
            writeArrayToFile(randomArray, "random_array.txt");
            System.out.println("Random array generated and stored in random_array.txt");
        } else if (choice == 2) {
            System.out.println("Enter the filename to read the array from:");
            String inputFilename = scanner.next();

            int[] arrayToSort = readFileToArray(inputFilename);
            bubbleSort(arrayToSort);

            System.out.println("Enter the filename to store the sorted array:");
            String outputFilename = scanner.next();
            writeArrayToFile(arrayToSort, outputFilename);

            System.out.println("Array sorted and stored in " + outputFilename);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
