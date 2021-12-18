package com.company;

import java.util.Comparator;

public abstract class Figure implements Comparable<Figure>{
    public final double pi = 3.14;
    Figure()
    {

    }
    abstract double volume();
    @Override
    public int compareTo(Figure obj)
    {
        return (int)(this.volume()-obj.volume());
    }

}
class Cube extends Figure{
    private int side;
    Cube(int side)
    {
        this.side = side;
    }
    @Override
    double volume() {
        return side*side*side;
    }
}
class Sphere extends Figure{
    private int radius;
    Sphere(int radius)
    {
        this.radius = radius;
    }
    @Override
    double volume() {
        return ((double)(4/3))*pi*Math.pow(radius,3);
    }
}

class Cilinder extends Figure{
    private int radius, height;
    Cilinder(int radius,int height)
    {
        this.height = height;
        this.radius = radius;
    }
    @Override
    double volume() {
        return pi*Math.pow(radius,2)*height;
    }
}
