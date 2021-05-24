package com.company;

public class Punkt {
    double x1,x2;

    @Override
    public String toString() {
        return x1+","+x2;
    }

    public Punkt(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }
    public Punkt(Punkt x) {
        this.x1 = x.x1;
        this.x2 = x.x2;
    }
}
