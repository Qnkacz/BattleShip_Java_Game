package com.company;

import java.awt.datatransfer.DataFlavor;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.*;

public class Game {
    boolean isBoardGood=false;
    String[][] myBoard = new String[12][12];
    String[][] enemyBoard = new String[12][12];
    String enemyName;
    Scanner input = new Scanner(System.in);
    int oneCount=4;
    int twoCount=3;
    int threeCount=2;
    int fourcount=1;
    boolean doIstart=false;
    int whostartsnumber;
    int turnNumber=0;
    boolean isEnded=false;
    boolean isSaved=false;
    boolean isFin=false;
    int myScore=0;
    int enemyScore=0;
    String saveStringPath=null;
    String lastSavePath=null;
    String gameState=null;
    String savePath=null;
    void InitArr()
    {
        for(int x=0;x<myBoard.length;x++)
            for(int y=0;y<myBoard[x].length;y++)
                myBoard[x][y] = String.valueOf((char)95)+" ";

        for(int x=0;x<enemyBoard.length;x++)
            for(int y=0;y<enemyBoard[x].length;y++)
                enemyBoard[x][y] = String.valueOf((char)95)+" ";
    }
    public void GameStart(String usr) throws IOException {
       LocalDate today = LocalDate.now();
        out.println("Who will be your opponent?");
        enemyName=input.nextLine();
        //File directory = new File("BWAKS\\" + usr + "\\" + enemyName + "\\" + today.toString() );
        Files.createDirectories(Paths.get("BWAKS", usr, enemyName, today.toString()));
        saveStringPath = "BWAKS\\" + usr + "\\" + enemyName + "\\" + today.toString();
        lastSavePath = "BWAKS\\" + usr + "\\" + "lastSave.txt";

/*
        if(directory.mkdir())
            {
                out.println("directory created");
            }
*/      int oneCount=4;
        int twoCount=3;
        int threeCount=2;
        int fourcount=1;
        InitArr();
        myBoardIit();

            do {

                Main.clearConsole();
                out.println("Who starts?");
                out.println("1 and Enter- me");
                out.println("2 and Enter- enemy");
                whostartsnumber = input.nextInt();


            }while(whostartsnumber !=1 && whostartsnumber !=2);
            if(whostartsnumber==1) doIstart=true;
            else doIstart=false;
            gameState = "n";
            game();
    }

    public static boolean isInteger(String s)
    {
        try
        {
            Integer.parseInt(s);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    void myBoardIit()
    {
        boolean isInPlace=false;
        char col;
        String colIput;
        int row;
        int chipChoice;

        do{
            do {
                out.println("Choose a ship type ");
                if(oneCount!=0) out.println("1 - one flag "+oneCount+" avaible");
                if(twoCount!=0)  out.println("2 - two flag "+twoCount+" avaible");
                if(threeCount!=0)  out.println("3 - three flag "+threeCount+" avaible");
                if(fourcount!=0)  out.println("4 - four flag "+fourcount+" avaible");
                String playerChoice = input.nextLine();
                if(isInteger(playerChoice))
                {
                    chipChoice = Integer.parseInt(playerChoice);
                }
                else
                {
                    out.println(playerChoice);
                    chipChoice=0;
                    out.println("ERROR: wrong ship input, try again");
                }
            }while((chipChoice!=1 && chipChoice!=2 && chipChoice!=3 && chipChoice!=4) && !checkIfValidShip((chipChoice)));

            printBoard(myBoard);
            out.println("Write position (eg. a - enter, 1 - enter)");
            out.println("Now the char");
            colIput=input.nextLine();
            col=colIput.toLowerCase().charAt(0);
            out.println("Now the number");
            String rowChoice = input.nextLine();
            if(isInteger(rowChoice))
            {
                row = Integer.parseInt(rowChoice);
                chipInput(row,ConvrtColToNumb(col),chipChoice);
            }
            else
            {
                out.println(rowChoice + "ERROR: wrong row input, try again");
            }
        }
        while(oneCount+twoCount+threeCount+fourcount!=0);

    }
    void printBoard(String[][] s)
    {
        out.println((char)160 + " A B C D E F G H I J");
        for(int i=1; i<10; i++)
        {

            out.println(" " + i + s[i][1] + s[i][2] + s[i][3] + s[i][4] + s[i][5] + s[i][6] + s[i][7] + s[i][8] + s[i][9] + s[i][10]);
        }
        out.println("10"+ s[10][1] + s[10][2] + s[10][3] + s[10][4] + s[10][5] + s[10][6] + s[10][7] + s[10][8] + s[10][9] + s[10][10]);
    }
    boolean check(int i, int j)
    {
        if(i>=0 && i<=10 && j>=0 && j<=10)
        {


                for(int c=i-1;c<=i+1;c++)
                {
                    for(int k=j-1;k<=j+1;k++)
                    {
                        if(c>myBoard.length || c<0 || k>myBoard.length || k<0)
                        {
                            continue;
                        }
                       if(myBoard[c][k]==String.valueOf((char)95))
                        {
                            return  false;
                        }
                    }
                }
                return true;

        }
return false;
    }
    int ConvrtColToNumb(char s)
    {
        switch(s)
        {
            case 'a':
            return 1;
            case 'b':
                return 2;
            case 'c':
                return  3;
            case 'd':
                return 4;
            case 'e':
                    return 5;
            case'f':
                return 6;
            case'g':
                return 7;
            case 'h':
                return 8;
            case'i':
                return 9;
            case 'j':
                return 10;
            default:
                return -1;
        }
    }
    void chipInput(int i, int j,int choice)
    {
        if((i==0 || i==11) || (j==0 || j==11))
        {
            //krawedz
        }
        else
        {
            if(check(i,j)==true)
            {
                checkSide(i,j,choice);
            }
            else
            {
                out.println("ERROR: wrong pos, write one more time");
            }
        }
    }
    void checkSide(int i, int j, int size)
    {
        boolean up = true,right=true,down=true,left=true;
                for(int w=0;w<size;w++)
                {
                            if(!check(i-w,j))
                            {
                              up=false;
                            }
                            if(!check(i+w,j))
                            {
                                down=false;
                            }
                            if(!check(i,j-w))
                            {
                                left=false;
                            }
                            if(!check(i,j+w))
                            {
                                right=false;
                            }
                }

                int chipChoice;
        do {
            out.println("You can choose: ");
            if(up) out.println("up - 1");
            if(right) out.println("right-2");
            if(down) out.println("down -3");
            if(left) out.println("left -4");
            chipChoice=input.nextInt();
        }while(chipChoice!=1 && chipChoice!=2 && chipChoice!=3 && chipChoice!=4);
        placeShip(i,j,size,chipChoice);
        switch(size)
        {
            case 1:
                oneCount--;
                break;
            case 2:
                twoCount--;
                break;
            case 3:
                threeCount--;
                break;
            case 4:
                fourcount--;
                break;
        }
    }

    void placeShip(int i, int j,int size,int orintation)
    {
        switch(orintation)
        {
            case 1:
                for(int w=0;w<size;w++)
                {
                    myBoard[i-w][j]= String.valueOf((char)164)+" ";
                }
                break;
            case 2:
                for(int w=0;w<size;w++)
                {
                    myBoard[i][j+w]= String.valueOf((char)164)+" ";
                }
                break;
            case 3:
                for(int w=0;w<size;w++)
                {
                    myBoard[i+w][j]= String.valueOf((char)164)+" ";
                }
                break;
            case 4:
                for(int w=0;w<size;w++)
                {
                    myBoard[i][j-w]= String.valueOf((char)164)+" ";
                }
                break;
        }
    }
    boolean checkIfValidShip(int choice)
    {
        switch(choice)
        {
            case 1:
                if(oneCount>0){
                    return true;
                }
                else return false;
            case 2:
                if(twoCount>0)
                {
                    return true;
                }else return false;
            case 3:
                if(threeCount>0)
                {
                    return true;
                }else return false;
            case 4:
                if(fourcount>0)
                {
                    return true;
                }else return false;
            default: return false;

        }
    }
    void game() throws IOException {
        String turnChoice = null;
        do{
            out.println("Press ENTER to start TURN or write end and press enter to finish the game early");
            turnChoice = input.nextLine();
            if("".equals(turnChoice))
            {
                turnNumber++;
                out.println("Turn: " + turnNumber);
                if(doIstart)
                {
                    out.println("Your turn");
                    PutValue(enemyBoard);
                    doIstart=false;
                }
                else
                {
                    out.println("Enemy turn");
                    PutValue(myBoard);
                    doIstart=true;
                }
                turnChoice = null;
                CheckIfGameEnded();
                if(isEnded)
                {
                    break;
                }
                else
                {
                    continue;
                }
            }
            else if("end".equals(turnChoice))
            {
                String saveChoice = null;
                do {
                    out.println("Do you wish to save the game?");
                    out.println("y - yes (previous save override)");
                    out.println("n - no");
                    saveChoice = input.nextLine();
                    if("y".equals(saveChoice))
                    {
                        isSaved = true;
                        gameState = "n";
                        isFin = true;
                        saveChoice = null;
                    }
                    else if("n".equals(saveChoice))
                    {
                        //just exit, no save
                        isSaved = false;
                        isFin = true;
                        saveChoice = null;
                    }
                    else
                    {
                        out.println("wrong input for save choice");
                        saveChoice = null;
                    }
                }while(!isFin);
                turnChoice = null;
                isFin = true;
                break;
            }
            else
            {
                out.println("wrong input for turn choice");
                turnChoice = null;
            }

        }while(!isEnded || !isFin);

        if(isEnded || isSaved)
        {
            arrayToFile(myBoard, enemyBoard, enemyName, doIstart, turnNumber, gameState);
            if(isSaved)
            {
                File lastSave = new File(lastSavePath);
                if(lastSave.createNewFile())
                {
                    out.println("Created a new file with a path to the last unsolved game!");
                }
                else
                {
                    out.println("Updated the file with a path to the last unsolved game!");
                }
                try(BufferedWriter bw1 = new BufferedWriter(new FileWriter(lastSavePath)))
                {
                    int fileSum = new File(saveStringPath).listFiles().length;
                    String tmp;
                    tmp = saveStringPath + "\\" + fileSum + ".txt";
                    bw1.write(tmp);
                    bw1.flush();
                }
                catch(IOException ex)
                {

                }
            }
        }
    }
    void CheckIfGameEnded()
    {
        if(myScore==20)
        {
            isEnded=true;
            gameState = "w";
        }
        else if(enemyScore==20)
        {
            isEnded=true;
            gameState = "p";
        }
        else isEnded=false;
    }
    void PutValue(String[][] s)
    {
        int row;
        char col;
        String colinput;
        String didithit=null;
        out.println("Write position (eg. a - enter, 1 - enter)");
        //input.nextLine();
        int colnumb;
        do {
            out.println("input column letter:");
            colinput=input.nextLine();
            col=colinput.toLowerCase().charAt(0);
            colnumb = ConvrtColToNumb((char) col);
        }while(colnumb<1 || colnumb>10);
        String rowS = null;
        do{
            out.println("input row number:");
            rowS=input.nextLine();
        }while(!isInteger(rowS));
        row = Integer.parseInt(rowS);
        do {
            out.println("Did it hit?");
            out.println("Yes - 1");
            out.println("No - 2");
            didithit = input.nextLine();
            if("1".equals(didithit))
            {
                s[row][colnumb]=String.valueOf('X')+" ";
                didithit=null;
                break;
            }
            else if("2".equals(didithit))
            {
                s[row][colnumb]=String.valueOf('O')+" ";
                didithit=null;
                break;
            }
            else
            {
                out.println("not an option");
                didithit=null;
            }
        }while(!"1".equals(didithit) && !"2".equals(didithit));


        printBoard(myBoard);out.println(" ");
        printBoard(enemyBoard);
    }

    void arrayToFile(String[][] s, String[][] e, String ename, boolean b, int it, String gs) throws IOException //zapisujemy tablice stringow ze stanem mapy w formie ciagow znakow, po jednej linii dla kazdej tablicy, na koniec enter
    {
        int fileSum = new File(saveStringPath).listFiles().length;
        int id = fileSum + 1;
        for(int i=0; i<s.length; i++)
        {
            for(int j=0; j<s.length; j++)
            {
                try(BufferedWriter bw1 = new BufferedWriter(new FileWriter(saveStringPath + "\\" + id + ".txt", true )))
                {
                    String tmp;
                    tmp = s[i][j];
                    bw1.write(tmp);
                    bw1.flush();
                    if(i==s.length-1 && j==s.length-1)
                    {
                        bw1.newLine();
                    }
                }
                catch(IOException ex)
                {

                }
            }
        }

        for(int x=0; x<e.length; x++)
        {
            for(int y=0; y<s.length; y++)
            {
                try(BufferedWriter bw2 = new BufferedWriter(new FileWriter(saveStringPath + "\\" + id + ".txt", true )))
                {
                    String tmp;
                    tmp = e[x][y];
                    bw2.write(tmp);
                    bw2.flush();
                    if(x==e.length-1 && y==e.length-1)
                    {
                        bw2.newLine();
                    }
                }
                catch(IOException ex)
                {

                }
            }
        }
        BufferedWriter bw3 = new BufferedWriter(new FileWriter(saveStringPath + "\\" + id + ".txt", true ));
        String btmp;
        if(b)
        {
            btmp = "0";
            bw3.write(btmp);
            bw3.newLine();
            bw3.flush();
        }
        else
        {
            btmp = "1";
            bw3.write(btmp);
            bw3.newLine();
            bw3.flush();
        }
        bw3.write(ename);
        bw3.newLine();
        bw3.flush();
        bw3.write(Integer.toString(it));
        bw3.newLine();
        bw3.flush();
        bw3.write(gs);
        bw3.newLine();
        bw3.flush();
    }

    public void loadGame(String user) throws IOException {
        LocalDate today = LocalDate.now();
        String me=null;
        String enemy=null;
        String whoseTurn = null;
        String ename = null;
        String turn = null;
        String gs = null;
        lastSavePath = "BWAKS\\" + user + "\\" + "lastSave.txt";
        InitArr();
        File tmpload = new File(lastSavePath);
        Scanner tmpsc = new Scanner(tmpload);
        String tmp = null;
        while(tmpsc.hasNextLine())
        {
            tmp = tmpsc.nextLine();
        }
        File load = new File(tmp);
        Scanner sLoad = new Scanner(load);
        if(sLoad.hasNextLine())
        {
            me = sLoad.nextLine();
            //me = me.substring(0, me.length()-2);
        }
        if(sLoad.hasNextLine())
        {
            enemy = sLoad.nextLine();
            //enemy = enemy.substring(0, enemy.length()-2);
        }
        if(sLoad.hasNextLine())
        {
            whoseTurn = sLoad.nextLine();
        }
        if(sLoad.hasNextLine())
        {
            ename = sLoad.nextLine();
        }
        if(sLoad.hasNextLine())
        {
            turn = sLoad.nextLine();
        }
        if(sLoad.hasNextLine())
        {
            gs = sLoad.nextLine();
        }
        String [] meb = me.split(" ");
        String [] enemyb = enemy.split(" ");
        for(int i=0; i<myBoard.length; i++)
        {
            for(int j=0; j<myBoard.length; j++)
            {
                myBoard[i][j] = meb[i*myBoard.length + j] + " ";
                enemyBoard[i][j] = enemyb[i*enemyBoard.length + j] + " ";
            }
        }
        if("0".equals(whoseTurn))
        {
            doIstart = true;
        }
        else
        {
            doIstart = false;
        }
        enemyName = ename;
        if(isInteger(turn))
        {
            turnNumber = Integer.parseInt(turn);
        }
        else
        {
            turnNumber=0;
        }
        gameState = gs;
        saveStringPath = "BWAKS\\" + user + "\\" + enemyName + "\\" + today.toString();
        Main.clearConsole();
        game();
    }

    public void loadSave(String user, String sp) throws IOException {
        String me=null;
        String enemy=null;
        String whoseTurn = null;
        String ename = null;
        String turn = null;
        String gs = null;
        savePath = sp;
        InitArr();
        File load = new File(savePath);
        Scanner sLoad = new Scanner(load);
        if(sLoad.hasNextLine())
        {
            me = sLoad.nextLine();
            //me = me.substring(0, me.length()-2);
        }
        if(sLoad.hasNextLine())
        {
            enemy = sLoad.nextLine();
            //enemy = enemy.substring(0, enemy.length()-2);
        }
        if(sLoad.hasNextLine())
        {
            whoseTurn = sLoad.nextLine();
        }
        if(sLoad.hasNextLine())
        {
            ename = sLoad.nextLine();
        }
        if(sLoad.hasNextLine())
        {
            turn = sLoad.nextLine();
        }
        if(sLoad.hasNextLine())
        {
            gs = sLoad.nextLine();
        }
        String [] meb = me.split(" ");
        String [] enemyb = enemy.split(" ");
        for(int i=0; i<myBoard.length; i++)
        {
            for(int j=0; j<myBoard.length; j++)
            {
                myBoard[i][j] = meb[i*myBoard.length + j] + " ";
                enemyBoard[i][j] = enemyb[i*enemyBoard.length + j] + " ";
            }
        }
        if("0".equals(whoseTurn))
        {
            doIstart = true;
        }
        else
        {
            doIstart = false;
        }
        enemyName = ename;
        if(isInteger(turn))
        {
            turnNumber = Integer.parseInt(turn);
        }
        else
        {
            turnNumber=0;
        }
        gameState = gs;
        Main.clearConsole();
        out.println("Enemy: " + enemyName);
        out.println("Number of turns: " + turnNumber);
        if("w".equals(gameState))
        {
            out.println("Winner: You");
        }
        else if("p".equals(gameState))
        {
            out.println("Winner: Enemy");
        }
        else if("n".equals(gameState))
        {
            out.println("Game not finished");
        }
        else
        {
            out.println("Unknown game state");
        }
        out.println("MY BOARD:");
        printBoard(myBoard);
        out.println("ENEMY'S BOARD:");
        printBoard(enemyBoard);
    }

}
