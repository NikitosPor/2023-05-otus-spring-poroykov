package ru.otus.helpers;

import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

@Component
public class IOStreamHelperImpl implements IOStreamHelper {

    private final PrintStream output = System.out;
    private final Scanner input = new Scanner(System.in);

    @Override
    public void outputString(String string) {
        output.println(string);
    }

    @Override
    public String parseString() {
        return input.nextLine();
    }
}
