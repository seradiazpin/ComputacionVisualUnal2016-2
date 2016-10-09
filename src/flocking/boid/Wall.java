package flocking.boid;

/**
 * Created by sergiodiazpinilla on 1/09/16.
 */

import processing.core.PGraphics;
import processing.core.PVector;

import static processing.core.PConstants.PI;
import static processing.core.PVector.sub;

/**
 * Created by sergiodiazpinilla on 24/08/16.
 */
public class Wall {

    PVector pos;
    PVector dpos;
    int dir;
    int orie;

    private static final int X = 1;
    private static final int Y = 2;
    private static final int Z = 3;

    public  Wall(PVector pos, int dir, int orie){
        this.pos = pos;
        this.dpos = pos;
        this.dir = dir;
        this.orie = orie;
    }



    public void applyRepelForce(Boid particle, PGraphics pg){
        /*
        PVector repelForce = new PVector(particle.location.x,0);
        pg.line(repelForce.x,repelForce.y,repelForce.z,particle.location.x,particle.location.y,particle.location.z);
        float dist = particle.location.dist(repelForce);

        particle.applyForce(repelForce.mult(dir/(dist*dist)));
        /*
        System.out.println("Repel Force:"+repelForce.toString());
        System.out.println("Dist:"+dist);
        System.out.println("---------------------------------------------");
        */
        PVector repelForce = new PVector(1f, 0,0);
        if(orie == X) {
            pos = new PVector(pos.x,particle.location.y,particle.location.z);
        }
        if(orie == Y){
            repelForce = new PVector(0, 1f,0);
            pos = new PVector(particle.location.x,pos.y,particle.location.z);
        }
        if(orie == Z){
            repelForce = new PVector(0, 0,1f);
            pos = new PVector(particle.location.x,particle.location.y,pos.z);
        }

        float dist = particle.location.dist(pos);
        //System.out.println("Normal:"+repelForce.toString());
        particle.applyForce(repelForce.mult(dir*20/(dist*dist)));
        //System.out.println("Apply:"+repelForce.toString());
        //pg.stroke(255,0,0);
        //pg.line(pos.x,particle.location.y,pos.z, repelForce.x,particle.location.y,repelForce.z);
        //pg.ellipse(repelForce.x,particle.location.y,10,20);


        /*
        PVector repelForce = sub(particle.location,pos);
        repelForce.mult(100);
        float dist = PVector.dist(particle.location,pos);

        particle.applyForce(repelForce.mult(dir/(dist*dist)));

        pg.stroke(255,0,0);
        pg.line(pos.x,pos.y,pos.z,repelForce.x,repelForce.y,repelForce.z);
        pg.ellipse(repelForce.x,repelForce.y,10,20);
        */


    }
}