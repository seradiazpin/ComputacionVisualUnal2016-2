package particles;

import processing.core.*;

import java.util.*;

class Particle {

    private int m;
    private float life;
    private PVector pos;
    private PVector acc;
    private PVector vel;
    private int size;
    private PImage img;

    public Particle(PVector pos, PImage img) {
        this.pos = pos.copy();
        this.img = img;
        this.life = 100;
        this.m =20;
        this.acc = new PVector();
        float vx  = (float) (new Random().nextGaussian()*0.8);
        float vy  = (float) (new Random().nextGaussian()*0.3);

        this.vel = new PVector(vx,0);
        this.size = new Random().nextInt(100)+5;
    }

    public void applyForce(PVector f) {
    /*
      F = m *a
     a = F/m
     */
        acc.add(PVector.mult(f, m));
    }

    public void run(PGraphics pg) {
    /*
      F = m *a
     */
        vel = vel.add(acc);
        pos = pos.add(vel);
        life -= 0.5;
        acc.mult(0);
        //Render
        //pg.noStroke();

        //pg.fill(255,0,0,(float)life);
        //pg.ellipse(pos.x,pos.y,size,size);
        //pg.tint(2*life,life,0,(float)life);

        pg.tint(255, (float)(2*life),0f,(float)life);
        pg.image(img,pos.x,pos.y,size,size);


    }
    public PVector getPos(){
        return pos;
    }

    public boolean isDead(){
        return life <= 0;
    }
}