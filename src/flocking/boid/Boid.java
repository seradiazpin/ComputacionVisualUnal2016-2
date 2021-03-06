package flocking.boid;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

import static processing.core.PApplet.*;

class Boid {

    PVector location;
    PVector velocity;
    PVector acceleration;
    float r;
    float maxforce;    // Maximum steering force
    float maxspeed;    // Maximum speed
    float h = 50f;
    PGraphics pg;
    PApplet app;

    public Boid(PVector pos,PGraphics pg, PApplet app) {
        acceleration = new PVector(0, 0);
        this.pg = pg;
        this.app = app;
        // This is a new PVector method not yet implemented in JS
        // velocity = PVector.random2D();

        // Leaving the code temporarily this way so that this example runs in JS
        float angle = app.random(TWO_PI);
        velocity = new PVector(cos(angle), sin(angle));

        location = pos;
        r = 2.0f;
        maxspeed = 2;
        maxforce = 0.03f;
    }

    void run(ArrayList<Boid> boids) {
        flock(boids);
        update();
        borders();
        render();
    }

    void applyForce(PVector force) {
        // We could add mass here if we want A = F / M
        acceleration.add(force);
    }

    // We accumulate a new acceleration each time based on three rules
    void flock(ArrayList<Boid> boids) {
        PVector sep = separate(boids);   // Separation
        PVector ali = align(boids);      // Alignment
        PVector coh = cohesion(boids);   // Cohesion
        // Arbitrarily weight these forces
        sep.mult(1.5f);
        ali.mult(1.0f);
        coh.mult(1.0f);
        // Add the force vectors to acceleration
        applyForce(sep);
        applyForce(ali);
        applyForce(coh);
    }

    // Method to update location
    void update() {
        // Update velocity
        velocity.add(acceleration);
        // Limit speed
        velocity.limit(maxspeed);
        location.add(velocity);
        // Reset accelertion to 0 each cycle
        acceleration.mult(0);
    }

    // A method that calculates and applies a steering force towards a target
    // STEER = DESIRED MINUS VELOCITY
    PVector seek(PVector target) {
        PVector desired = PVector.sub(target, location);  // A vector pointing from the location to the target
        // Scale to maximum speed
        desired.normalize();
        desired.mult(maxspeed);

        // Above two lines of code below could be condensed with new PVector setMag() method
        // Not using this method until Processing.js catches up
        // desired.setMag(maxspeed);

        // Steering = Desired minus Velocity
        PVector steer = PVector.sub(desired, velocity);
        steer.limit(maxforce);  // Limit to maximum steering force
        return steer;
    }

    void render() {
        // Draw a triangle rotated in the direction of velocity
        Random rn = new Random();

        pg.pushMatrix();

        pg.translate(location.x,location.y,location.z);
        pg.rotateY(atan2(-velocity.z,velocity.x));
        pg.rotateZ(asin(velocity.y/velocity.mag()));
        pg.stroke(h);
        pg.noFill();
        pg.noStroke();
        pg.fill(rn.nextInt(255),rn.nextInt(255),rn.nextInt(255));
        //draw bird
        pg.beginShape(TRIANGLES);
        pg.vertex(3*r,0,0);
        pg.vertex(-3*r,2*r,0);
        pg.vertex(-3*r,-2*r,0);

        pg.vertex(3*r,0,0);
        pg.vertex(-3*r,2*r,0);
        pg.vertex(-3*r,0,2*r);

        pg.vertex(3*r,0,0);
        pg.vertex(-3*r,0,2*r);
        pg.vertex(-3*r,-2*r,0);


    /* wings
    pg.vertex(2*r,0,0);
    pg.vertex(-1*r,0,0);
    pg.vertex(-1*r,-8*r,15);

    pg.vertex(2*r,0,0);
    pg.vertex(-1*r,0,0);
    pg.vertex(-1*r,8*r,15);
    //*/

        pg.vertex(-3*r,0,2*r);
        pg.vertex(-3*r,2*r,0);
        pg.vertex(-3*r,-2*r,0);
        pg.endShape();
        //box(10);
        pg.popMatrix();


    }

    // Wraparound
    void borders() {
        if (location.x < -r) location.x = pg.width+r;
        if (location.y < -r) location.y = pg.height+r;

        if (location.x > pg.width+r) location.x = -r;
        if (location.y > pg.height+r) location.y = -r;

        if(location.z>500) location.z=0;
        if(location.z<0) location.z=500;


    }

    // Separation
    // Method checks for nearby boids and steers away
    PVector separate (ArrayList<Boid> boids) {
        float desiredseparation = 25.0f;
        PVector steer = new PVector(0, 0, 0);
        int count = 0;
        // For every boid in the system, check if it's too close
        for (Boid other : boids) {
            float d = PVector.dist(location, other.location);
            // If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)
            if ((d > 0) && (d < desiredseparation)) {
                // Calculate vector pointing away from neighbor
                PVector diff = PVector.sub(location, other.location);
                diff.normalize();
                diff.div(d);        // Weight by distance
                steer.add(diff);
                count++;            // Keep track of how many
            }
        }
        // Average -- divide by how many
        if (count > 0) {
            steer.div((float)count);
        }

        // As long as the vector is greater than 0
        if (steer.mag() > 0) {
            // First two lines of code below could be condensed with new PVector setMag() method
            // Not using this method until Processing.js catches up
            // steer.setMag(maxspeed);

            // Implement Reynolds: Steering = Desired - Velocity
            steer.normalize();
            steer.mult(maxspeed);
            steer.sub(velocity);
            steer.limit(maxforce);
        }
        return steer;
    }

    // Alignment
    // For every nearby boid in the system, calculate the average velocity
    PVector align (ArrayList<Boid> boids) {
        float neighbordist = 50;
        PVector sum = new PVector(0, 0);
        int count = 0;
        for (Boid other : boids) {
            float d = PVector.dist(location, other.location);
            if ((d > 0) && (d < neighbordist)) {
                sum.add(other.velocity);
                count++;
            }
        }
        if (count > 0) {
            sum.div((float)count);
            // First two lines of code below could be condensed with new PVector setMag() method
            // Not using this method until Processing.js catches up
            // sum.setMag(maxspeed);

            // Implement Reynolds: Steering = Desired - Velocity
            sum.normalize();
            sum.mult(maxspeed);
            PVector steer = PVector.sub(sum, velocity);
            steer.limit(maxforce);
            return steer;
        }
        else {
            return new PVector(0, 0);
        }
    }

    // Cohesion
    // For the average location (i.e. center) of all nearby boids, calculate steering vector towards that location
    PVector cohesion (ArrayList<Boid> boids) {
        float neighbordist = 50;
        PVector sum = new PVector(0, 0);   // Start with empty vector to accumulate all locations
        int count = 0;
        for (Boid other : boids) {
            float d = PVector.dist(location, other.location);
            if ((d > 0) && (d < neighbordist)) {
                sum.add(other.location); // Add location
                count++;
            }
        }
        if (count > 0) {
            sum.div(count);
            return seek(sum);  // Steer towards the location
        }
        else {
            return new PVector(0, 0);
        }
    }
}
