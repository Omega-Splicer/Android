package com.epitech.billy.omega_splicer.presentation.models;

/**
 * Created by bichon_b on 3/11/16.
 * <p/>
 * This class represents a plane in the presentation layer.
 * <p/>
 */
public class Plane {

    private String name;

    public Plane(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
