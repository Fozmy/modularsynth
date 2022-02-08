package com.modularsynth;

import net.beadsproject.beads.core.UGen;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.WavePlayer;

public class Oscillator extends Module{

    WavePlayer wp;

    public Oscillator(audioOut ac) {
        super("Oscillator");
        wp = new WavePlayer(ac.getAudioContext(), 440.0f, Buffer.SINE);
    }

    @Override
    public UGen getOutput() {
        return wp;
    }

    @Override
    public boolean addInput(Module m) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
