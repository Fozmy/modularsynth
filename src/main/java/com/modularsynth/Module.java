package com.modularsynth;

import net.beadsproject.beads.core.UGen;

public abstract class Module{
    public String name;

    public Module(String name){
        this.name = name;
    }

    public abstract UGen getOutput();

}
