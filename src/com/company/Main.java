package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.out;
import static java.lang.System.in;

public class Main {
    public static String savePath="BWAKS\\save.txt";
    public static String scorePath="BWAKS\\SCORE.txt";
    public static void main(String[] args) throws IOException {
        registry regObj = new registry();
        regObj.saveChech(savePath);
    }

    public static void pressAnyKeyToContinue()
    {
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    public static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }



}
