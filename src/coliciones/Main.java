package coliciones;


import math.geom2d.line.Line2D;
import processing.core.PApplet;
import processing.core.PImage;
import remixlab.proscene.Scene;

import java.util.ArrayList;

/**
 * Created by sergiodiazpinilla on 28/08/16.
 */
public class Main extends PApplet{
    PImage img;
    ArrayList<Circle> circles = new ArrayList();
    Line2D linej;
    @Override
    public void settings() {
        size(1000, 700,P2D);
    }

    @Override
    public void setup() {
        super.setup();
        Circle c = new Circle(width/2,height/2,800);
        circles.add(c);
        c = new Circle(width/2,height/4,325);
        circles.add(c);
        c = new Circle(width/2,3*height/4,325);
        circles.add(c);
        c = new Circle(width/2,3*height/4,125);
        circles.add(c);
        //img = loadImage("Img/kungfu.jpg");
    }

    @Override
    public void draw() {
        linej = new Line2D(0,0,mouseX,mouseY);
        super.draw();
        background(50);

        stroke(255,0,0);
        line(0,0,mouseX,mouseY);
        fill(255,0);

        for(Circle c:circles){
            c.display(getGraphics());
            c.pointInter(linej,getGraphics());
        }

        text(frameCount,10,10);

        text(frameRate,10,30);
    }

    @Override
    public void mousePressed() {
        super.mousePressed();
        // Add a new boid into the System

    }

    public static void main(String[] args) {

        String name = Main.class.getName();
        PApplet.main(name);

    }

}
