package flocking.boid;

import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;

class Flock {
    ArrayList<Boid> boids; // An ArrayList for all the boids
    Wall [] walls = new  Wall[6];
    PGraphics pg;
    Flock(PGraphics pg) {
        this.pg = pg;
        walls[0] = new Wall(new PVector(0,0,0),1,1);
        walls[1] = new Wall(new PVector(pg.width-20,0,0),-1,1);

        walls[2] = new Wall(new PVector(0,0,0),1,2);
        walls[3] = new Wall(new PVector(0,pg.height-20,0),-1,2);

        walls[4] = new Wall(new PVector(0,0,0),1,3);
        walls[5] = new Wall(new PVector(0,0,500),-1,3);

        boids = new ArrayList<Boid>(); // Initialize the ArrayList

    }

    void run() {


        for (Boid b : boids) {
            walls[0].applyRepelForce(b,pg);
            walls[1].applyRepelForce(b,pg);
            walls[2].applyRepelForce(b,pg);
            walls[3].applyRepelForce(b,pg);
            walls[4].applyRepelForce(b,pg);
            walls[5].applyRepelForce(b,pg);
            b.run(boids);  // Passing the entire list of boids to each boid individually

        }
    }

    void addBoid(Boid b) {
        boids.add(b);
    }

}
