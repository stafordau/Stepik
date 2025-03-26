package task2_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\julia\\Desktop\\Stepik\\src\\task2_10\\input.txt";
        String outputFilePath = "C:\\Users\\julia\\Desktop\\Stepik\\src\\task2_10\\output.txt";

        String input = readInputFromFile(inputFilePath);
        if (input == null) {
            writeOutputToFile(outputFilePath, "Error! Cannot read input file");
            return;
        }

        String result = processExpression(input);
        writeOutputToFile(outputFilePath, result);
    }

    private static String readInputFromFile(String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            if (fileScanner.hasNextLine()) {
                return fileScanner.nextLine().trim();
            }
        } catch (FileNotFoundException e) {
            return "Error! File not found at: " + filePath;
        }
        return null;
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

    private static void writeOutputToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error! Unable to write to file: " + filePath);
        }
    }
}