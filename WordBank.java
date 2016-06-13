/*   
 * Name: Maninder Sran
 * Date: June 08, 2016
 * Desc: This Class defintion is responsible for reading in words from a text file 
 *       and then putting those words in the appropriate category word bank.
*/
package hangman;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordBank {

 public WordBank(){}
 
 public static void readData(String file,ArrayList placeData)
 {
        BufferedReader reader;
        int counter = 0;
        String line;
        ArrayList<String> data = placeData;

        try
        {
            reader = new BufferedReader (new FileReader (file));

            while ((line = reader.readLine ()) != null)
            {
                for (int i = 0 ; i < line.length () ; i++)
                {
                    data.add(counter, reader.readLine()); 
                    counter++;
                }
            }
            reader.close();
            //System.out.println ("The number of entries for " + file.substring(65) + " is: " + counter);
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("File: " + file + " not found.");
            e.printStackTrace ();
        }
        catch (IOException e)
        {
            System.out.println ("Error in reading file: " + file);
            e.printStackTrace ();
        }
        catch (RuntimeException e)
        {
            System.out.println ("Error Array index out of bounds");
            e.printStackTrace ();
        }
    }
 public static void readLeaders(String file,String []names,String[] scores)
 {
        BufferedReader reader;
        int counter = 0;
        String line;
        try
        {
            reader = new BufferedReader (new FileReader (file));

            while ((line = reader.readLine ()) != null)
            {
                for (int i = 0 ; i < line.length () ; i++)
                {
                        if(i%2!=0)
                        {
                           names[i] = reader.readLine();
                        }
                        else
                        {
                           scores[i] = reader.readLine();
                        }
                        counter++;
                }
            }
            reader.close();
            //System.out.println ("The number of entries for " + file.substring(65) + " is: " + counter);
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("File: " + file + " not found.");
            e.printStackTrace ();
        }
        catch (IOException e)
        {
            System.out.println ("Error in reading file: " + file);
            e.printStackTrace ();
        }
        catch (RuntimeException e)
        {
            System.out.println ("Error Array index out of bounds");
            e.printStackTrace ();
        }
    }
 public static void setLeaders(String name,int score,String[] names,String[]scores)
 {
     String tempName = null,tempScore = null;

     //readData("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\Leaderboard.txt",leaders);
     readLeaders("E:/Hangman/src/hangman/Leaderboard.txt",names,scores);
     for(int i=0;i<scores.length;i++)
     {
        if(score>Integer.valueOf(scores[i]))
        {
           names[names.length-1]   = name;
           scores[scores.length-1] = String.valueOf(score);
           for (int j = 0 ; j < scores.length ; j++)
           {
                for (int k = 0 ; k < scores.length - 1 ; k++)
                {
                    if (Integer.valueOf(scores [k + 1]) > Integer.valueOf(scores [k]))
                    {
                        tempScore = scores [k];
                        scores [k] = scores [k + 1];
                        scores [k + 1] = tempScore;
                        
                        tempName = names [k];
                        names [k] = names [k + 1];
                        names [k + 1] = tempName;
                    }
                    if(scores[k]==null)
                    {
                        scores[i] = "0";
                    }
                    if(names[k]==null)
                    {
                        names[i] = "empty";
                    }
                }
            }
           //writeLeaders("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\Leaderboard.txt",leaders,score,name);
           writeLeaders("E:/Hangman/src/hangman/Leaderboard.txt",scores,names,score,name);
        }
     }
 }
 public static void writeLeaders(String file,String []scores,String[]names,int score, String name)
 {
        BufferedWriter fileWriter;
        File newFile = new File (file);

            if (checkFile (newFile))
            {
                try
                {
                    newFile.createNewFile ();
                }
                catch (IOException e)
                {
                    e.printStackTrace ();
                }

                try
                {
                    fileWriter = new BufferedWriter (new FileWriter (newFile));
                    
                    for(int i =0; i<= 15; i++)
                    {
                            if(i%2!=0)
                            {
                                fileWriter.write (names[i]);
                            }
                            else
                            {
                                fileWriter.write (scores[i]);
                            }
                    }
                    fileWriter.close ();
                    System.out.println ("File written successfully");
                }
                catch (IOException e)
                {
                    System.out.println ("An error occured writing/reading to the file.");
                    e.printStackTrace ();
                }
            }
 }
 
 public static boolean checkFile (File newFile)
    {
        Scanner input = new Scanner(System.in);
        String response;

        if (newFile.exists ())
        {
            System.out.println ("This file already exists");
            System.out.println ("Do you want to over write this file enter y or n: ");
            response = input.next();

            if(response.equalsIgnoreCase("Y"))
            {
                    return true;
            }
            else if(response.equalsIgnoreCase("N"))
            {
                return false;
            }
            else
            {
                System.out.println ("Enter y or n");
                checkFile (newFile);
            }
        }
        return true;
    }
 public static void showLeaderBoard(String file,String[] names,String[] scores)
 {  
     int counter = 0;
        readLeaders(file,names,scores);
      
        System.out.println("\t\t\t\tLeaderBoards");
        System.out.println("\t\t\t\t------------");
        System.out.println("Name\t\t\t\t\t\t\tScore");
        for(int i =0;i<names.length;i++)
        {    ++counter;
            System.out.println(counter+". "+names[i]+"\t\t\t\t\t\t\t"+scores[i]);
        }
 }
}
