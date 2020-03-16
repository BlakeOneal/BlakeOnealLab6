/*
Title: Using user input to convert hex (0x) or binary (0b) to decimal numbers
Author: Kiki
Date: Feb 17, 2015
Description: Helps replace the 0b prefix with ""
Code Version: 1.0
Availability: Public
In lines 48-49, source code is from Stackoverflow.com
(and my awesome TA Andrew helped too)
 */
// This is a comment for my lab 6
import java.util.Scanner;
public class NumericConversion {

    public static long hexStringDecode(String hex) {
        // Decodes a hexadecimal string and returns its value
        long decodedHex = 0;
        for (int i = hex.length() - 1; i >= 0; i--) {
            char character = hex.charAt(hex.length() - (i + 1));
            char lowerCaseChar = Character.toLowerCase(character);
            hexCharDecode(lowerCaseChar);
            decodedHex = (long) (decodedHex + (Math.pow(16, i) * hexCharDecode(lowerCaseChar)));
        }
// Returns the decoded Hex value
        return (decodedHex);
    }

    public static short hexCharDecode(char digit) {
        // Decodes a single hexadecimal digit and returns its value
        // 0 -> 48, 1 -> 49, 2 -> 50, etc.
        // hex digit: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        char lowerCaseDigit = Character.toLowerCase(digit);
        if ('0' <= lowerCaseDigit && lowerCaseDigit <= '9') {
            return (short) (digit - '0');
        }
        else if ('a' <= lowerCaseDigit && lowerCaseDigit <= 'f') {
            return (short) (digit - 'a' + 10);
        }
        else {
            return(0);
        }
    }

    public static short binaryStringDecode(String binary) {
        // Decodes a binary string and returns its value
        short result = 0;
        // Used to ignore the possibility of 0b appearing prior to the input of a binary number
        if (binary.startsWith("0b")) {
            binary = binary.replaceAll("0b", "");
        }
        int binaryToDivide = Integer.parseInt(binary);
// Analyzes the character of the binary input and assigns it a value
        for (int i = binary.length(); i >= 0; i--) {
            int digit =(int)(binaryToDivide/(Math.pow(10,i-1))) % 2;
            result = (short) (result + (digit * Math.pow(2, (i-1))));
            binaryToDivide = (int)(binaryToDivide - digit*Math.pow(10,i-1));
        }
        return (result);

        // Extra credit option: attempted, but did not succeed
    }
    public static String binaryToHex(String binary) {
        // Decodes a binary string, re-encodes it as a hexadecimal, and returns the hexadecimal string
        // binary: 0111 1110
        // 1st step: convert binary string into the decimal number using the binaryStringDecode function
        short dec = binaryStringDecode(binary);
        String result = "";
        while (dec % 16 > 0) {
            int remainder = dec % 16;
            result = remainder + result;
            // Look at sum of digits problem. write remainders in reverse order
        }
        // 2nd step: convert the decimal number into hexadecimal, then done! :)
        return result;
    }
// Prints the menu of the program
    public static void printMenu() {
        System.out.println("Decoding Menu");
        System.out.println("-------------");
        System.out.println("1. Decode hexadecimal");
        System.out.println("2. Decode binary");
        System.out.println("3. Convert binary to hexadecimal");
        System.out.println("4. Quit");
        System.out.print("\nPlease enter an option: ");
        }


    public static void main(String[] args) {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int option;
        option = scanner.nextInt();
        System.out.print("Please enter the numeric string to convert: ");
        String numericString;
        numericString = scanner.next();
        // Switch statement used for user options, tells the program which process to perform
        while ((option >= 1 && option <= 4))
        switch (option) {
            case 1:
                // Case 1 decodes a hex
                System.out.println("Result: " + hexStringDecode(numericString) + "\n");
                printMenu();
                option = scanner.nextInt();
                if (option == 4) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                System.out.print("Please enter the numeric string to convert: ");
                numericString = scanner.nextLine();
                break;
            case 2:
                // Case 2 decodes a binary
                System.out.println("Result: " + binaryStringDecode(numericString) + "\n");
                printMenu();
                option = scanner.nextInt();
                if (option == 4) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                System.out.print("Please enter the numeric string to convert: ");
                numericString = scanner.nextLine();
                break;
            case 3:
                // Case 3 decodes a binary to decimal and then converts the decimal into a hex
                binaryToHex(numericString);
                printMenu();
                option = scanner.nextInt();
                if (option == 4) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                System.out.print("Please enter the numeric string to convert: ");
                numericString = scanner.nextLine();
                break;
            case 4:
                // Case 4 Exits the program
                    System.out.println("Goodbye!");
                    System.exit(0);
                break;
            default:
                // Case 5 Also exits the program
                System.exit(0);
                break;
        }
    }
}

