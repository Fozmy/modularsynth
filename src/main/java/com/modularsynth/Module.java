package com.modularsynth;

import java.util.ArrayList;

import net.beadsproject.beads.core.UGen;

public abstract class Module{
    public String name;
    ArrayList<Module> inputs;
    int index;

    public Module(String name, int index){
        this.name = name;
        this.index = index;
        inputs = new ArrayList<>();
    }

    public int getIndex(){
        return index;
    }

    @Override
    public String toString(){
        String s = ""+name;
        for(Module m : inputs){
            s += " <-- "+"["+index+"] "+m+" ";
        }
        return s;
    }

    public abstract UGen getOutput();

    public abstract boolean addInput(Module m);
}
