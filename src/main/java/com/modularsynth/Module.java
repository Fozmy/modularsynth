package com.modularsynth;

import java.util.ArrayList;

import net.beadsproject.beads.core.UGen;

public abstract class Module{
    public String name;
    ArrayList<Module> inputs;

    public Module(String name){
        this.name = name;
        inputs = new ArrayList<>();
    }

    @Override
    public String toString(){
        String s = ""+name;
        for(Module m : inputs){
            s += " <-- "+m+" ";
        }
        return s;
    }

    public abstract UGen getOutput();

    public abstract boolean addInput(Module m);
}
