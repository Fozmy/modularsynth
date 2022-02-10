package com.modularsynth;

import java.util.ArrayList;
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
                modules.add(new Oscillator((audioOut) modules.get(0), modules.size()+1));
            }
        } 
        else if (cmd.equals("input")) {
            int send = scan.nextInt();
            int recieve = scan.nextInt();
            modules.get(recieve).addInput(modules.get(send));
        }
        else if (cmd.equalsIgnoreCase("setfreq")){
            int recieve = scan.nextInt();
            float freq = scan.nextFloat();
            ((Oscillator)modules.get(recieve)).setFrequency(freq);
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