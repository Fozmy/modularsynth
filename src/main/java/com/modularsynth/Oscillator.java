package com.modularsynth;

import net.beadsproject.beads.core.UGen;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.Static;
import net.beadsproject.beads.ugens.WavePlayer;

public class Oscillator extends Module{

    WavePlayer wp;
    float freqIndex = 200.0f;

    public Oscillator(audioOut ac, int index) {
        super("Oscillator", index);
        wp = new WavePlayer(ac.getAudioContext(), new Static(ac.getAudioContext(), 440.0f), Buffer.SINE);
    }

    @Override
    public UGen getOutput() {
        return wp;
    }

    @Override
    public boolean addInput(Module m) {
        try{
            if(m instanceof Oscillator){
                WavePlayer modulator = (WavePlayer)m.getOutput();
                Function frequencyModulation = new Function(modulator){
                    @Override
                    public float calculate()
                    {
                      return (x[0] * freqIndex) + wp.getFrequency();
                    }
                };
                wp.setFrequency(frequencyModulation);
                inputs.add(m);
            }
        }
        catch(Exception e){
            System.err.println(e);
            return false;
        }
        return true;
    }
    
    public void setFrequency(float freq){
        wp.setFrequency(freq);
    }
}
