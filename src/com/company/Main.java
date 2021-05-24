package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Hooka_Javesa("testowa",0.5,0.5,0.001,new Punkt(2,2));
        Hooka_Javesa("Rosenbrocka",0.5,0.5,0.00001,new Punkt(2,2));
        Hooka_Javesa("Rastringa",0.5,0.5,0.00001,new Punkt(2,2));
    }
    private static void Hooka_Javesa(String f,double alpha,double s,double epsilon, Punkt punkt_startowy) throws FileNotFoundException{
        PrintWriter zapisx1=new PrintWriter(f+".txt");
        Punkt x=new Punkt(punkt_startowy);
        System.out.println("Funkcja "+f);
        do{
            System.out.println("x:"+x+" f(x):"+funkcja(f,x));
            zapisx1.println("x:"+x+" funkcja:"+funkcja(f,x));
            Punkt xB=new Punkt(x);
            x=probuj(f,xB,s);
            if((funkcja(f,x)<funkcja(f,xB))){
                do{
                    Punkt xBp = new Punkt(xB);
                    xB = new Punkt(x);
                    x = new Punkt(2 * xB.x1 - xBp.x1, 2 * xB.x2 - xBp.x2);
                    x=probuj(f,x,s);
                }while(!(funkcja(f,x)>=funkcja(f,xB)));
                x=new Punkt(xB);

            }else
                s*=alpha;
        }while(s>=epsilon);
        zapisx1.println("x:"+x+" funkcja:"+funkcja(f,x));
        System.out.println("x:"+x+" funkcja:"+funkcja(f,x));
        zapisx1.close();
    }
    private static Punkt probuj(String f,Punkt x,double s){
        Punkt tmp=new Punkt(x);
        for(int i=-1;i<=1;i++){
            int g=0;
            for(int j=-1;j<=1;j++){
                if((i==0 || j==0) && !(i==0 && j==0)){
                    Punkt tmp1=new Punkt(x.x1+i*s,x.x2+j*s);
                    //System.out.println("Proba nr "+(++g)+" "+new Punkt(x.x1+i*s,x.x2+j*s)+" "+f_celu(new Punkt(x.x1+i*s,x.x2+j*s)));
                if(funkcja(f,tmp1)<funkcja(f,tmp)){
                    tmp=new Punkt(tmp1);
                }}
            }
        }
        return tmp;
    }
    private static double funkcja(String f,Punkt x){
        return switch (f) {
            case "testowa" -> 2.5 * Math.pow(Math.pow(x.x1, 2) - x.x2, 2) + Math.pow(1 - x.x1, 2);
            case "Rosenbrocka" -> 100 * Math.pow((x.x2 - Math.pow(x.x1, 2)), 2) + Math.pow(1 - x.x1, 2);
            case "Rastringa" -> 20 + Math.pow(x.x1, 2) + Math.pow(x.x2, 2) - 10 * (Math.cos(2 * Math.PI * x.x1) + Math.cos(2 * Math.PI * x.x2));
            default -> 0;
        };
    }
}
