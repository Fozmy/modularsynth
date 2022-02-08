package com.modularsynth;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.UGen;

public class audioOut extends Module{
    AudioContext ac;

    public audioOut() {
        ac = new AudioContext();
    }

    public UGen getOutput(){
        return null;
    }

    public boolean addInput(Module m){
        try{
            ac.out.addInput(m.getOutput());
            return true;
        }
        catch(Exception e){
            System.err.println(e);
            System.err.println("Unable to add "+m+" as an input");
            return false;
        }
    }
}
