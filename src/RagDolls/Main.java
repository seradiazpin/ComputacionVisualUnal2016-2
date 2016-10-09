package RagDolls;


import fisica.*;
import processing.core.PApplet;

/**
 * Created by sergiodiazpinilla on 28/08/16.
 */
public class Main extends PApplet{

    FWorld world;


    @Override
    public void settings() {
        size(1000, 700,P2D);
    }

    @Override
    public void setup() {
        super.setup();
        float cx = width/2;
        float cy = height/2;
        Fisica.init(this);
        frameRate(60);
        world = new FWorld();
        world.setEdges();

        new Doll(width/3,cy,world,true);

        new Doll(cx+width/3,cy,world,true);

        map();

    }

    @Override
    public void draw() {
        super.draw();
        background(128);


        world.step();
        world.draw();
        text(frameCount,10,10);
        text(frameRate,10,30);

    }

    public void map(){
        FBox box = new FBox(300,50);
        box.setPosition(0,height/2+100);
        box.setStatic(true);

        world.add(box);

        box = new FBox(300,50);
        box.setPosition(width,height/2+100);
        box.setStatic(true);

        world.add(box);

        box = new FBox(500,50);
        box.setPosition(0,height/2+150);
        box.setStatic(true);

        world.add(box);

        box = new FBox(500,50);
        box.setPosition(width,height/2+150);
        box.setStatic(true);

        world.add(box);

        box = new FBox(700,50);
        box.setPosition(0,height/2+200);
        box.setStatic(true);

        world.add(box);

        box = new FBox(700,50);
        box.setPosition(width,height/2+200);
        box.setStatic(true);

        world.add(box);

        box = new FBox(850,50);
        box.setPosition(0,height/2+250);
        box.setStatic(true);

        world.add(box);

        box = new FBox(850,50);
        box.setPosition(width,height/2+250);
        box.setStatic(true);

        world.add(box);

        box = new FBox(width,50);
        box.setPosition(width/2,height/2+300);
        box.setStatic(true);

        world.add(box);


        box = new FBox(50,50);
        box.setPosition(width/2,height/2+250);
        box.setStatic(true);

        world.add(box);



    }



    public static void main(String[] args) {

        String name = Main.class.getName();
        PApplet.main(name);

    }

}
