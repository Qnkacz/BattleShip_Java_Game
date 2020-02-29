package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class registry {
    static Login login=new Login();
    public static void saveChech(String PATH) throws IOException {
        File saveFile = new File(PATH);
        saveFile.setExecutable(true);saveFile.setWritable(true);saveFile.setReadable(true);
        if(saveFile.exists())
        {
            out.println("true");
            login.question();
        }
        else
        {
            out.println("It seems that it's your first time booting this game, please register to play!");
            Main.pressAnyKeyToContinue();
            registering(PATH);
        }
    }
    public static void registering(String PATH) throws IOException {
        File save = new File(PATH);
        save.setExecutable(true);save.setWritable(true);save.setReadable(true);
        File directory = new File(PATH.substring(0, 5));
        directory.setExecutable(true);directory.setWritable(true);directory.setReadable(true);
        File score = new File(PATH.substring(0, 5)+"\\score.txt");
        score.setExecutable(true);score.setWritable(true);score.setReadable(true);




        if(directory.mkdir())
        {
            out.println("directory created");
        }
        if (save.createNewFile())
        {
            out.println("File is created!");
        }
        if(score.createNewFile())
        {
            out.println("file is created");
        }
        ArrayList<String>userList = getUsernamesList(save);
        out.println("write your username!");
        Scanner input = new Scanner(System.in);
        String username;
        do{
            username = input.nextLine();
        }
        while(userList.contains(username));
        out.println("write your password (min. 8 chars, and 1 number)");
        String password;
        do {
            password=input.nextLine();
        }
        while(password.length()<=7 || stringContainsNumber(password)==false);

        FileWriter writer = new FileWriter(save,true);
        writer.write(username+" "+password+"\n");
        writer.close();
        File userDir = new File(PATH.substring(0, 5)+"\\"+username);
        if(userDir.mkdir())
        {
            out.println("usrdirdirectory created");
        }
        login.question();
    }


    public static boolean stringContainsNumber(String s)
    {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }

    public static ArrayList<String> getUsernamesList(File save) throws FileNotFoundException {
        ArrayList<String> aList=new ArrayList<String>();
        Scanner fileScanner = new Scanner(save);
        while(fileScanner.hasNextLine())
        {
            String z = fileScanner.nextLine();
            String [] zip = z.split(" ");
            aList.add((zip[0]));
        }
        return aList;
    }
    public static ArrayList<String> getPassword(File save) throws FileNotFoundException {
        ArrayList<String> aList=new ArrayList<String>();
        Scanner fileScanner = new Scanner(save);
        while(fileScanner.hasNextLine())
        {
            String z = fileScanner.nextLine();
            String [] zip = z.split(" ");
            aList.add((zip[1]));
        }
        return aList;
    }



}
