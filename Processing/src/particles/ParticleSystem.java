package particles;

import processing.core.*;

import java.util.*;

class ParticleSystem{
    private List<Particle> particles;
    private PVector pos;
    private PImage img;

    private Repeledor rep;
    private Wall wall;

    public ParticleSystem(PVector pos, PImage img){
        this.pos = pos.copy();
        this.img = img;
        this.particles = new ArrayList<Particle>();
        this.rep = new Repeledor(new PVector(pos.x,pos.y-100f),0.5f);
        this.wall = new Wall(new PVector(pos.x,pos.y-100f));
    }

    public void applyForce(PVector f) {
        for(Particle p:particles){
            p.applyForce(f);
        }
    }
    public void run(PGraphics pg){
        rep.draw(pg);
        wall.draw(pg);
        for(int i = particles.size()-1;i>=0;i--){
            Particle particle = particles.get(i);
            if(particle.isDead()){
                particles.remove(i);
            }else{
                rep.applyRepelForce(particle);
                wall.applyRepelForce(particle);
                particle.run(pg);
            }

        }
    }

    public void addParticle(){
        particles.add(new Particle(pos,img));
    }
}