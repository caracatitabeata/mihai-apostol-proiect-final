package fun3;

// A Simple Java program to demonstrate working of
// String matching in Java
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){

            String line = in.nextLine();

            String patternString1 = "(<([A-Za-z](.)*?)>)+?\\b(((?<!<).)*?)\\b(</\\2>)+";

            Pattern pattern = Pattern.compile(patternString1);
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                matcher.reset();
                while (matcher.find()) {
                    System.out.println(matcher.group(4));
                }
            }
            else System.out.println("None");


            testCases--;
        }
    }
}