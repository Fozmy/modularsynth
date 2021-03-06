package com.modularsynth;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main program housing the modules
 */
public class Rack {
    private static ArrayList<Module> modules = new ArrayList<>();

    private static void processCommand(String cmd, Scanner scan) {
        if (cmd.equalsIgnoreCase("ls") || cmd.equalsIgnoreCase("list")) {
            listModules();
        } 
        else if (cmd.equalsIgnoreCase("create")) {
            String module = scan.next();
            if (module.equalsIgnoreCase("Osc") || module.equalsIgnoreCase("Oscillator")) {
                modules.add(new Oscillator((audioOut) modules.get(0), modules.size()));
            }
            else if(module.equalsIgnoreCase("vca")){
                modules.add(new VCA((audioOut) modules.get(0), modules.size()));
            }
            else {
            	System.err.println("Error: unknown input");
            }
        } 
        else if(cmd.equalsIgnoreCase("rm") || cmd.equalsIgnoreCase("remove")) {
        	int index;
        	try {
        		index = scan.nextInt();
        		if(index == 0) {
        			throw new InputMismatchException();
        		}
        		else if(index < 1 || index > modules.size()-1) {
        			throw new ArrayIndexOutOfBoundsException();
        		}
        		modules.remove(index);
        		for(int i = index; i < modules.size(); i++) {
        			modules.get(i).decrementIndex();
        		}
        	}
        	catch(InputMismatchException e) {
        		System.err.println("Error: wrong parameters, rm|remove [index (not 0)]");
        	}
        	catch(ArrayIndexOutOfBoundsException e){
        		System.err.println("Error: unknown module selected");
        	}
        }
        else if (cmd.equals("input")) {
            int send;
            int recieve;
            try{
                send = scan.nextInt();
                recieve = scan.nextInt();
                if(send < 0 || send > modules.size()-1 || recieve < 0 || recieve > modules.size()-1){
                    throw new ArrayIndexOutOfBoundsException();
                }
                modules.get(recieve).addInput(modules.get(send));
            }
            catch(InputMismatchException e){
                System.err.println("Error: wrong parameters, input [# index of sender] [# index of receiver]");
                scan.nextLine();
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.err.println("Error: unknown module selected");
                scan.nextLine();
            }
            
        }
        else if (cmd.equalsIgnoreCase("setfreq") || cmd.equalsIgnoreCase("sf")){
            int recieve;
            float freq;
            try{
                recieve = scan.nextInt();
                freq = scan.nextFloat();
                if(recieve < 0 || recieve > modules.size()-1){
                    throw new ArrayIndexOutOfBoundsException();
                }
                if(!(modules.get(recieve) instanceof Oscillator)){
                    throw new InputMismatchException();
                }
                ((Oscillator)modules.get(recieve)).setFrequency(freq);
            }
            catch(InputMismatchException e){
                System.err.println("Error: wrong parameters, setfreq [# index of osc] [frequency]");
                scan.nextLine();
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.err.println("Error: unknown module selected");
                scan.nextLine();
            }
        }
        else if(cmd.equalsIgnoreCase("setlevel") || cmd.equalsIgnoreCase("sl")){
            int recieve;
            float level;
            try{
                recieve = scan.nextInt();
                level = scan.nextFloat();
                if(recieve < 0 || recieve > modules.size()-1){
                    throw new ArrayIndexOutOfBoundsException();
                }
                if(!(modules.get(recieve) instanceof VCA)){
                    throw new InputMismatchException();
                }
                ((VCA)modules.get(recieve)).setLevel(level);
            }
            catch(InputMismatchException e){
                System.err.println("Error: wrong parameters, setlevel [# index of osc] [level]");
                scan.nextLine();
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.err.println("Error: unknown module selected");
                scan.nextLine();
            }
        }
        else if (cmd.equalsIgnoreCase("exit")){
            System.out.println("Exiting program");
        }
        else{
            System.out.println(cmd + ": Unknown command");
            scan.nextLine();
        }
    }

    private static void listModules() {
        for (int i = 0; i < modules.size(); i++) {
            System.out.println("[" + i + "] " + modules.get(i));
        }
    }

    private static void setup() {
        modules.add(new audioOut());
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        setup();
        while (!(input.equals("exit"))) {
            input = scan.next();
            processCommand(input, scan);
        }
        scan.close();
        ((audioOut) modules.get(0)).getAudioContext().stop();
    }
}