package task2_10;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\julia\\Desktop\\Stepik\\src\\task2_10\\input.txt";
        String outputFilePath = "C:\\Users\\julia\\Desktop\\Stepik\\src\\task2_10\\output.txt";

        processFile(inputFilePath, outputFilePath);
    }

    private static void processFile(String inputFilePath, String outputFilePath) {
        try (Scanner fileScanner = new Scanner(new File(inputFilePath));
             FileWriter writer = new FileWriter(outputFilePath)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue; // Пропускаем пустые строки

                String result = processExpression(line);
                writer.write(line + " = " + result + "\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found: " + inputFilePath);
        } catch (IOException e) {
            System.out.println("Error! Unable to write to file: " + outputFilePath);
        }
    }

    private static String processExpression(String input) {
        String[] parts = input.split("\\s+");

        if (parts.length != 3) {
            return "Error! Invalid input format";
        }

        String num1Str = parts[0];
        String operator = parts[1];
        String num2Str = parts[2];

        double num1, num2;

        try {
            num1 = Double.parseDouble(num1Str);
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            return "Error! Not number";
        }

        switch (operator) {
            case "+":
                return String.format("%.1f", num1 + num2);
            case "-":
                return String.format("%.1f", num1 - num2);
            case "*":
                return String.format("%.1f", num1 * num2);
            case "/":
                if (num2 == 0) {
                    return "Error! Division by zero";
                } else {
                    return String.format("%.1f", num1 / num2);
                }
            default:
                return "Operation Error!";
        }
    }
}