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
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        AudioContext ac = new AudioContext();
        WavePlayer wp = new WavePlayer(ac, 440.0f, Buffer.SINE);
        ac.out.addInput(wp);
        ac.start();
        int input = 1;
        while(input != 0){
            input = scan.nextInt();
            if(input != 0){
                wp.setFrequency((float)input);
            }
        }
        ac.stop();
        scan.close();
    }
}
