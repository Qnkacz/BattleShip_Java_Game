package com.company;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.out;

public class Login {
    registry regObj = new registry();
    Game gameobj;
   public String currUser;
   public String currUserPswd;


    public void question() throws IOException {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            Main.clearConsole();
            out.println("It sems, that you have an Account, you can log in or register!");
            out.println("1 and Enter- to login");
            out.println("2 and Enter- to register");
            choice = input.nextInt();
            //out.println(choice);

        }while(choice !=1 && choice !=2);

        switch (choice)
        {
            case 1:
                out.println("LOG IN");
                login();
                break;
            case 2:
                out.println("SIGN UP");
                registry.registering(Main.savePath);
        }
    }

    public void login() throws IOException {
        Scanner input = new Scanner(System.in);
        out.println("username: ");
        String username=input.nextLine();
        out.println("password: ");
        String password=input.nextLine();
        currUserPswd=password;
        String userpasswd=username+" "+password;
        getUserPasswd(userpasswd,username);
    }

    private void getUserPasswd(String str,String usr) throws IOException {
        File save = new File(Main.savePath);
        ArrayList<String> aList=new ArrayList<String>();
        Scanner fileScanner = new Scanner(save);
        while(fileScanner.hasNextLine())
        {
            String z = fileScanner.nextLine();
            aList.add((z));
        }
        if(aList.contains(str))
        {
            out.println("zalogowano");
            currUser=usr;

            GameMenu.menu(currUser);

        }
        else
        {
            out.println("We are sorry, but the username or password doesn't match, please try again");
            login();
            currUser=null;
            currUserPswd=null;
        }



    }
    public void ChangePasswd() throws IOException {
        out.println(currUser);
        out.println(currUserPswd);
        File save = new File(Main.savePath);
        ArrayList<String> aList=new ArrayList<String>();
        Scanner fileScanner = new Scanner(save);
        StringBuffer buffer = new StringBuffer();
        Scanner userInput = new Scanner(System.in);
        while(fileScanner.hasNextLine())
        {
            buffer.append(fileScanner.nextLine()+System.lineSeparator());
        }
        String filecontents=buffer.toString();
        fileScanner.close();
        String oldLine = currUser+" "+currUserPswd;
        String newPasswd;
        out.println("Write new password: ");
        do {
            newPasswd=userInput.nextLine();
        }
        while(newPasswd.length()<=7 || registry.stringContainsNumber(newPasswd)==false);
        String newLine = currUser+" "+newPasswd;
        filecontents = filecontents.replaceAll(oldLine,newLine);
        FileWriter writer = new FileWriter(Main.savePath);
        writer.append(filecontents);
        writer.flush();
        GameMenu.menu(currUser);
    }

    public void ShowScore() throws FileNotFoundException {
        File save = new File(Main.scorePath);
        Scanner fileScanner = new Scanner(save);
        String[] profileInfo = new String[4];
        while (fileScanner.hasNextLine()) {
            final String lineFromFile = fileScanner.nextLine();
            if(lineFromFile.contains(currUser))
            {
                for(int i=0;i<lineFromFile.length();i++ )
                {

                    profileInfo = lineFromFile.split(" ");
                }
                out.println("Nazwa: "+profileInfo[0]);
                out.println("Ilosc wygranych: "+profileInfo[1]);
                out.println("ilosc przegranych: "+profileInfo[2]);
                float procent = Float.parseFloat(profileInfo[1])*100/(Float.parseFloat(profileInfo[1])+Float.parseFloat(profileInfo[2]));
                out.println("Winrate: "+procent+"%");
                break;
            }
            else out.println("Nothing here");

        }
    }
}
