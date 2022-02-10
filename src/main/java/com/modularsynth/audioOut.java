package com.modularsynth;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.UGen;

public class audioOut extends Module{
    AudioContext ac;

    public audioOut() {
        super("Audio Out", 0);
        ac = new AudioContext();
        ac.start();
    }

    public AudioContext getAudioContext(){
        return ac;
    }

    @Override
    public boolean addInput(Module m){
        try{
            ac.out.addInput(m.getOutput());
            inputs.add(m);
            return true;
        }
        catch(Exception e){
            System.err.println(e);
            System.err.println("Unable to add "+m+" as an input");
            return false;
        }
    }

    @Override
    public UGen getOutput() {
        return null;
    }
}
