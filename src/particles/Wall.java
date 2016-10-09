package particles;

import processing.core.PGraphics;
import processing.core.PVector;

/**
 * Created by sergiodiazpinilla on 24/08/16.
 */
public class Wall {

    PVector pos;


    public  Wall(PVector pos){
        this.pos = pos;

    }

    public void draw(PGraphics pg){
        pg.rect(10,0,20,pg.height);
    }

    public void applyRepelForce(Particle particle){
        PVector repelForce = new PVector(particle.getPos().x,0);
        float dist = particle.getPos().dist(repelForce);
        particle.applyForce(repelForce.mult(1/(dist*dist)));
        if(particle.getPos().x < pos.x){
            particle.getPos().x = pos.x+20;
        }

    }
}
