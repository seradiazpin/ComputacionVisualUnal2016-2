package flocking.boid;

import particles.Test;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by sergiodiazpinilla on 28/08/16.
 */
public class Main extends PApplet{

    Flock flock;
    float zoom=800;
    int zAxis = 500;

    @Override
    public void settings() {
        size(1000, 700,P3D);
    }

    @Override
    public void setup() {
        super.setup();
        //scene = new Scene(this);




        flock = new Flock(getGraphics());

        // Add an initial set of boids into the system
        for (int i = 0; i < 350; i++) {
            //PVector pos = new PVector(width/2,height/2,50);
            PVector pos = new PVector(width/3,height/2,this.random(zAxis));
            flock.addBoid(new Boid(pos,getGraphics(),this));
        }

        for (int i = 0; i < 350; i++) {
            //PVector pos = new PVector(width/2,height/2,50);
            PVector pos = new PVector(width/2,height/2,this.random(zAxis));
            flock.addBoid(new Boid(pos,getGraphics(),this));
        }



    }

    @Override
    public void draw() {
        super.draw();

        //camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0);
        background(205);

        camera(mouseX*3, height/2, 1300, width/2, height/2, 0, 0, 1, 0);
        //translate(width/2, height/2, -100);
        directionalLight(255,255,255, 0, 1, -100);

        stroke(0);
        noFill();

        line(0,0,0,  0,height,0);
        line(0,0,0,  0,0,zAxis);
        line(0,0,0,  width,0,0);

        line(0,height,0,  0,height,zAxis);
        line(0,height,0,  width,height,0);

        line(width,0,0,  width,height,0);
        line(width,0,0,  width,0,zAxis);

        line(width,height,0,  width,height,zAxis);

        line(0,0,zAxis, 0,height,zAxis);
        line(0,0,zAxis, width,0,zAxis);
        line(width,height,zAxis,  width,0,zAxis);
        line(width,height,zAxis,  0,height,zAxis);

        //camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0);
        flock.run();

        text(frameCount,10,10,zAxis);
        text(frameRate,10,30,zAxis);


    }

    @Override
    public void mousePressed() {
        super.mousePressed();
        // Add a new boid into the System
        PVector pos = new PVector(mouseX,mouseY,this.random(zAxis));
        flock.addBoid(new Boid(pos,getGraphics(),this));
    }

    public static void main(String[] args) {

        String name = Main.class.getName();
        PApplet.main(name);

    }
    @Override
    public void keyPressed()
    {
        switch (keyCode)
        {
            case UP: zoom-=10; break;
            case DOWN: zoom+=10; break;
        }
    }

}
