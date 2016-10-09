package particles;

import processing.core.PGraphics;
import processing.core.PVector;

import static processing.core.PVector.sub;

/**
 * Created by sergiodiazpinilla on 24/08/16.
 */
public class Repeledor {
    PVector pos;
    public float mg;

    public Repeledor(PVector pos,float mg){
        this.pos = pos;
        this.mg = mg;
    }

    public void applyRepelForce(Particle particle){
        PVector repelForce = sub(particle.getPos(),pos);
        repelForce.mult(mg);
        float dist = PVector.dist(particle.getPos(),pos);
        particle.applyForce(repelForce.mult(1/(dist*dist)));
    }

    public void draw(PGraphics pg){
        pg.ellipse(pos.x,pos.y,10,10);
    }
}
