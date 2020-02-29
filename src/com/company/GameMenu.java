package com.company;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static com.company.registry.login;
import static java.lang.System.out;

public class GameMenu
{
    public static Game gameobj;

    public static void menu(String str) throws IOException {
        gameobj=new Game();
        Scanner input = new Scanner(System.in);
        String choice=null;

        do
        {
            out.print("MENU GRY" + "\n"  + "-----------" +  "\n" +  "1 - nowa gra" + "\n" + "2 - wroc do rozgrywki" + "\n" + "3 - objrzyj poprzednie gry" + "\n" + "4 - Profil uzytkownika" + "\n" + "end - wyjscie" + "\n");
            choice=input.nextLine();
            if("1".equals(choice))
            {
                gameobj.GameStart(str);
                choice = null;
            }
            else if("2".equals(choice))
            {
                gameobj.loadGame(str);
                choice = null;
            }
            else if("3".equals(choice))
            {
                //obejrzyj historie gier

                File[] directories = new File("BWAKS\\"+str).listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isDirectory();
                    }
                });
                String enemyChoice=null;
                int c=directories.length;
                String tmpPath=null;
                do{

                    for(int i=0;i<directories.length;i++)
                    {
                        out.println((i+1)+" - "+(directories[i].getName()));
                    }
                    out.println("end - wyjscie do menu glownego");
                    enemyChoice=input.nextLine();
                    if(Integer.parseInt(enemyChoice)>=1 && Integer.parseInt(enemyChoice)<=c)
                    {
                        tmpPath="BWAKS\\"+str+"\\"+directories[Integer.parseInt(enemyChoice)-1].getName();
                        String dateChoice=null;
                        File[] dateDirectories = new File(tmpPath).listFiles(new FileFilter() {
                            @Override
                            public boolean accept(File file) {
                                return file.isDirectory();
                            }
                        });
                        int d=dateDirectories.length;
                        String tmp2Path=null;
                        do {
                            for(int j=0; j<dateDirectories.length; j++)
                            {
                                out.println((j+1)+" - "+(dateDirectories[j].getName()));
                            }
                            out.println("end - wyjscie do menu przeciwnikow");
                            dateChoice=input.nextLine();
                            if(Integer.parseInt(dateChoice)>=1 && Integer.parseInt(dateChoice)<=d)
                            {
                                tmp2Path=tmpPath+"\\"+dateDirectories[Integer.parseInt(dateChoice)-1].getName();
                                String idChoice = null;
                                File[] idf = new File(tmp2Path).listFiles();
                                int f=idf.length;
                                do {
                                    for(int k=0; k<idf.length; k++)
                                    {
                                        out.println((k+1) + " - " + idf[k].getName());
                                    }
                                    out.println("end - wyjscie do menu dat");
                                    idChoice = input.nextLine();
                                    String idPath = null;
                                    if(Integer.parseInt(idChoice)>=1 && Integer.parseInt(idChoice)<=f)
                                    {
                                        idPath = tmp2Path + "\\" + idf[Integer.parseInt(idChoice)-1].getName();
                                        gameobj.loadSave(str, idPath);
                                    }
                                    else if ("end".equals(idChoice))
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        out.println("not in the id menu");
                                    }
                                }while(!"end".equals(idChoice));
                            }
                            else if("end".equals(dateChoice))
                            {
                                break;
                            }
                            else
                            {
                                out.println("not in the date menu");
                            }
                        }while(!"end".equals(dateChoice));
                    }
                    else if ("end".equals(enemyChoice))
                    {
                        break;
                    }
                    else
                    {
                        out.println("nonexistent choice O.o");
                    }
                }while(!"end".equals(enemyChoice));



                choice = null;
            }
            else if("4".equals(choice))
            {
                String profileChoice;
                do {
                    out.println(str + " - user profile:");
                    out.println("1 - powrot do menu");
                    out.println("2 - zmiana hasla");
                    out.println("3 - pokaz statystyki");
                    //out.println(profileChoice);
                    profileChoice = input.nextLine();
                    if("1".equals(profileChoice))
                    {
                        profileChoice = null;
                        break;
                    }
                    else if("2".equals(profileChoice))
                    {
                        login.ChangePasswd();
                        profileChoice = null;
                    }
                    else if("3".equals(profileChoice))
                    {
                        login.ShowScore();
                        profileChoice = null;
                    }
                    else
                    {
                        out.println("sth went wrong O.o");
                        profileChoice = null;
                    }
                }while(!"1".equals(profileChoice));
                choice = null;
            }
            else if("end".equals(choice))
            {
                out.println("bye bye!");
                choice = null;
                break;
            }
            else
            {
                out.println("wrong menu choice");
                choice = null;
            }
        }
        while(!"end".equals(choice));
        /* stare menu poza petla
        switch(choice)
        {
            case 1:
            gameobj.GameStart(str);
            break;
            case 2:
                //wroc do najnowszj nieskonczonej (w pliku tekstowym przechowujemy sciezke do ostatniej zapisanej nieukonczonej gry)
                break;
            case 3:
                //objrzyj historie gier (tu wczytujemy pliki dla wszysktich gier z przeciwnikami, na podstawie plikow w katalogach "USER/OPPONENT/DATE/match_id.txt")
                break;
            case 4:

                int profileChoice;
                do {
                    out.println(str + " - user profile:");
                    out.println("1 - powrot do menu");
                    out.println("2 - zmiana hasla");
                    //out.println(profileChoice);
                    profileChoice = Integer.parseInt(input.nextLine());
                }while(profileChoice!=1 && profileChoice!=2);

                if(profileChoice==1)
                {
                    //out.println(profileChoice);
                    menu(str);
                }
                else if(profileChoice==2)
                {
                    //out.println(profileChoice);
                    login.ChangePasswd();
                }
                else
                {
                    //out.println(profileChoice);
                    out.println("sth went wrong O.o");
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
        */
    }


}
