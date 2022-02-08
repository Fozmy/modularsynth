package com.modularsynth;

import net.beadsproject.beads.core.UGen;

public abstract class Module{

    private String name;

    public abstract UGen getOutput();

    @Override
    public String toString(){
        return name;
    }
}
