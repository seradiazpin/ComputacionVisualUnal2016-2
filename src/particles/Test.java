package particles;

import processing.core.*;

/**
 * Created by sergiodiazpinilla on 23/08/16.
 */


public class Test extends PApplet{

    ParticleSystem particleSystem;
    PImage img;

    @Override
    public void settings() {
        size(700,400,P2D);
    }

    @Override
    public void draw() {
        super.draw();
        background(0);
        blendMode(ADD);
        text(frameCount,10,10);
        text(frameRate,10,30);

        for(int i =0; i<=5;i++){
            particleSystem.addParticle();
        }

        float vx = map(mouseX,0,width,-0.02f,0.02f);
        PVector wind = new PVector(vx,0);
        PVector gra = new PVector(0,-0.01f);
        particleSystem.applyForce(wind);
        particleSystem.applyForce(gra);
        particleSystem.run(getGraphics());
    }

    @Override
    public void setup() {
        super.setup();
        img = loadImage("Img/fire.png");
        particleSystem = new ParticleSystem(new PVector(width/2,height-50),img);
    }

    public static void main(String[] args) {
        String name = Test.class.getName();
        PApplet.main(name);

    }
}
