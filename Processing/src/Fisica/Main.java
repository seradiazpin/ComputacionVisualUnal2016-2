package Fisica;


import processing.core.PApplet;
import processing.core.PImage;

import fisica.*;

/**
 * Created by sergiodiazpinilla on 28/08/16.
 */
public class Main extends PApplet{

    FWorld world;
    FPoly poly;

    FCompound compound;

    @Override
    public void settings() {
        size(1000, 700,P2D);
    }

    @Override
    public void setup() {
        super.setup();
        Fisica.init(this);
        frameRate(60);

        world = new FWorld();
        world.setEdges();





        FBox box = new FBox(100,10);
        box.setPosition(width/4,height/2);
        box.setStatic(true);
        world.add(box);

        box = new FBox(100,10);
        box.setPosition(width*3f/4,height/2);
        box.setStatic(true);
        world.add(box);

        compound = new FCompound();
        compound.setPosition(width/2,height/2);


        FCircle circle = new FCircle(40);
        circle.setPosition(0,10);
        circle.setRestitution(0.8f);
        compound.addBody(circle);




        circle = new FCircle(30);
        circle.setPosition(0,0);
        circle.setRestitution(0.8f);
        compound.addBody(circle);
        circle = new FCircle(15);
        circle.setPosition(5,-12);
        circle.setRestitution(0.8f);
        compound.addBody(circle);


        circle = new FCircle(15);
        circle.setPosition(-5,-12);
        circle.setRestitution(0.8f);
        compound.addBody(circle);


        world.add(compound);
    }

    @Override
    public void draw() {
        super.draw();
        background(128);



        if(frameCount%10 == 0){
            FCircle circle2 = new FCircle(20);
            circle2.setPosition(this.random(20,width-20),20);
            circle2.setRestitution(0.8f);
            world.add(circle2);

            FBlob blob = new FBlob();
            int r = (int)this.random(10,50);
            float x = random(1,5);
            blob.setAsCircle(map(x,0,6,0,width),50,r,20);
            blob.setFill(random(255),random(255),random(255));
            blob.setFriction(0);

            world.add(blob);

        }

        world.step();
        world.draw();
        text(frameCount,10,10);
        text(frameRate,10,30);

        if(key == CODED){
            if(keyCode == UP){
                compound.addForce(0,-10000);
            }
            if(keyCode == LEFT){
                compound.addForce(-10000,0);
            }
            if(keyCode == RIGHT){
                compound.addForce(10000,0);
            }
        }
    }

    @Override
    public void mouseDragged() {
        super.mouseDragged();
        if(poly == null){
            poly = new FPoly();
            poly.setStatic(true);
            world.add(poly);
        }
        poly.vertex(mouseX,mouseY);
        poly.recreateInWorld();
    }

    @Override
    public void mouseReleased() {
        super.mouseReleased();
        if(poly != null){
            poly.setStatic(false);
            poly = null;
        }
    }



    public static void main(String[] args) {

        String name = Main.class.getName();
        PApplet.main(name);

    }

}
