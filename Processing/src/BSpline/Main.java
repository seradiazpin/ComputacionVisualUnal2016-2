package BSpline;


import processing.core.PApplet;
import processing.core.PVector;
import processing.event.Event;
import remixlab.bias.event.MotionEvent;
import remixlab.proscene.InteractiveFrame;
import remixlab.proscene.Scene;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by sergiodiazpinilla on 28/09/16.
 */
public class Main extends PApplet {

    BSpline bs;
    Scene scene;
    ArrayList<InteractiveFrame> frames = new ArrayList<>();


    InteractiveFrame iframe ;
    @Override
    public void settings() {
        size(1000, 700,P3D);
    }

    @Override
    public void setup() {
        super.setup();
        scene = new Scene(this);
        //iframe = new InteractiveFrame(scene);
        scene.setVisualHints(scene.PICKING);
        for(int i = 0; i<10;i++){
            frames.add(new InteractiveFrame(scene));
            frames.get(i).setPosition((int)random(-width/2,width/2),(int)random(-height/2,height/2),random(-10f,10f));
            frames.get(i).setMotionBinding(LEFT, "metodo");
        }
        //iframe.setPosition(0,0,0);


    }

    @Override
    public void draw() {
        super.draw();
        background(0);
        stroke(255);

        for(int i = 0; i<10;i++) {
            if (i != 9) {
                line(frames.get(i).position().x(), frames.get(i).position().y(), frames.get(i).position().z(), frames.get(i + 1).position().x(), frames.get(i + 1).position().y(), frames.get(i + 1).position().z());
            }else{
                line(frames.get(i).position().x(), frames.get(i).position().y(), frames.get(i).position().z(), frames.get(0).position().x(), frames.get(0).position().y(), frames.get(0).position().z());

            }

        }
    }

    public static void main(String[] args) {
        String name = Main.class.getName();
        PApplet.main(name);
    }

    public void metodo(InteractiveFrame iframe, MotionEvent event){
        iframe.translate(event);

    }
}
