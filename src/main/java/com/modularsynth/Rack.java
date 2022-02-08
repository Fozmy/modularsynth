package com.modularsynth;

import java.util.Scanner;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.WavePlayer;

/**
 * Main program housing the modules
 */
public class Rack 
{
    private static void processCommand(String cmd){
        if(cmd.equals("ls") || cmd.equals("list")){
            listModules();
        }
    }

    private static void listModules(){

    }

    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!(input.equals("exit"))){
            input = scan.next();
            processCommand(input);
        }
        scan.close();
    }
}
