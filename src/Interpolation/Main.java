package Interpolation;



import processing.core.PApplet;
import processing.core.PGraphics;
import remixlab.proscene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergiodiazpinilla on 28/08/16.
 */
public class Main extends PApplet{

    List<Float> pointsX = new ArrayList<>();
    List<Float> pointsY = new ArrayList<>();
    Scene scene;
    boolean debu = false;

    float dAnim;
    int DIR = 1;
    Spline spl;
    float dy = height/2;
    float upperBound = 10f+height/2f, loweBound = 28f+height/2f;

    @Override
    public void settings() {
        size(1000, 700,P3D);
    }

    @Override
    public void setup() {
        float dx = width/3;
        //scene = new Scene(this);
        //scene.setRadius(500);

        super.setup();
        stroke(0);
        dAnim = 0.09f;

        pointsX.add(10f+dx);
        pointsX.add(70f+dx);
        pointsX.add(140f+dx);
        pointsX.add(210f+dx);
        pointsX.add(280f+dx);
        pointsX.add(350f+dx);
        pointsX.add(420f+dx);

        pointsY.add(15f +dy);
        pointsY.add(20f +dy);
        pointsY.add(12f +dy);
        pointsY.add(23f +dy);
        pointsY.add(10f +dy);
        pointsY.add(15f +dy);
        pointsY.add(20f +dy);

        spl = Spline.createMonotoneCubicSpline(pointsX,pointsY);

    }

    @Override
    public void draw() {
        super.draw();
        dAnim = 0.1f * DIR;
        background(50);

        drawFlag();
    }


    public static void main(String[] args) {

        String name = Main.class.getName();
        PApplet.main(name);

    }

    public void drawFlag(){
        drawlineFlag(0, color(255,255,0));
        drawlineFlag(50, color(255,255,0));
        drawlineFlag(100, color(0,0,255));
        drawlineFlag(150, color(255,0,0));
        //drawlineFlag(150);
    }

    public void drawlineFlag (int ddy, int colorS){
        int delta = 1;
        for (float i = pointsX.get(0); i < pointsX.get(pointsX.size()-1); i+=0.3) {
            float y1 = spl.interpolate(i);
            stroke(255);
            ellipse(i, y1+ddy, 1, 1);
            if((int)i%delta == 0){
                    stroke(colorS);
                    fill(colorS);
                float y2 = spl.interpolate(i+delta);
                quad(i, y1+ddy, i+delta, y2+ddy, i, y1+ddy+50, i+delta, y2+ddy+50);
            }

        }

        stroke(255,0,0);
        for (int i = 0; i < pointsX.size(); i++) {
            ellipse(pointsX.get(i), pointsY.get(i)+ddy, 3, 3);
        }


        if (pointsY.get(2).intValue() >= loweBound) {
            DIR = -1;
        }
        if(pointsY.get(2).intValue() <= upperBound){
            DIR = 1;
        }

        for (int i = 0; i < pointsY.size(); i+=2) {
            pointsY.set(i, pointsY.get(i) + dAnim);
        }
        stroke(255);
    }

}
