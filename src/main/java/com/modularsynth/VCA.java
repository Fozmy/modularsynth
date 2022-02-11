package com.modularsynth;

import net.beadsproject.beads.core.UGen;
import net.beadsproject.beads.ugens.Gain;

public class VCA extends Module{

    Gain vca;

    public VCA(audioOut ac, int index) {
        super("VCA", index);
        vca = new Gain(ac.getAudioContext(), 4);
        System.out.println(getIndex());
    }

    public void setLevel(float gain){
        vca.setGain(gain);
    }
    public void setLevel(UGen gain){
        vca.setGain(gain);
    }

    @Override
    public UGen getOutput() {
        return vca;
    }

    @Override
    public boolean addInput(Module m) {
        try{
            vca.addInput(m.getOutput());
            inputs.add(m);
        }
        catch(Exception e){
            System.err.println(e);
            return false;
        }
        return true;
    }
    
}
