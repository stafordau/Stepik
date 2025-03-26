package task2_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\julia\\Desktop\\Stepik\\src\\task2_10\\input.txt";
        String input = readInputFromFile(filePath);

        if (input == null) {
            System.out.println("Error! Cannot read input file");
            return;
        }

        processExpression(input);
    }

    private static String readInputFromFile(String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            if (fileScanner.hasNextLine()) {
                return fileScanner.nextLine().trim();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found at: " + filePath);
        }
        return null;
    }

    private static void processExpression(String input) {
        String[] parts = input.split("\\s+");

        if (parts.length != 3) {
            System.out.println("Error! Invalid input format");
            return;
        }

        String num1Str = parts[0];
        String operator = parts[1];
        String num2Str = parts[2];

        double num1, num2;

        try {
            num1 = Double.parseDouble(num1Str);
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            System.out.println("Error! Not number");
            return;
        }

        switch (operator) {
            case "+":
                System.out.println(String.format("%.1f", num1 + num2));
                break;
            case "-":
                System.out.println(String.format("%.1f", num1 - num2));
                break;
            case "*":
                System.out.println(String.format("%.1f", num1 * num2));
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("Error! Division by zero");
                } else {
                    System.out.println(String.format("%.1f", num1 / num2));
                }
                break;
            default:
                System.out.println("Operation Error!");
        }
    }
}