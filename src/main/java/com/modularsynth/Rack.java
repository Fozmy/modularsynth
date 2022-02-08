package com.modularsynth;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main program housing the modules
 */
public class Rack 
{
    private static ArrayList<Module> modules = new ArrayList<>();

    private static void processCommand(String cmd, Scanner scan){
        if(cmd.equals("ls") || cmd.equals("list")){
            listModules();
        }
        else if(cmd.equals("create")){
            String module = scan.next();
            if(module.equals("Osc") || module.equals("Oscillator")){
                modules.add(new Oscillator((audioOut)modules.get(0)));
            }
        }
        else if(cmd.equals("input")){
            int send = scan.nextInt();
            int recieve = scan.nextInt();
            modules.get(recieve).addInput(modules.get(send));
        }
    }

    private static void listModules(){
        for(int i = 0; i < modules.size(); i++){
            System.out.println("["+i+"] "+modules.get(i));
        }
    }

    private static void setup(){
        modules.add(new audioOut());
    }

    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String input = "";
        setup();
        while(!(input.equals("exit"))){
            input = scan.next();
            processCommand(input, scan);
        }
        scan.close();
    }
}