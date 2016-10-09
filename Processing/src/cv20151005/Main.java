package cv20151005;

import processing.core.PApplet;
import processing.core.PShape;
import remixlab.proscene.Scene;

public class Main extends PApplet{

    private Scene scene;
    private float angle[];
    private float vel[];
    private float rad[];
    private int num = 10;
    private PShape shape;
    @Override
    public void settings() {
        size(800, 400,P3D);
    }

    @Override
    public void setup() {
        super.setup();
        scene = new Scene(this);
        scene.setRadius(1000);
        angle = new float[10000];
        vel = new float[angle.length];
        rad = new float[angle.length];
        for(int i = 0; i < vel.length; i++){
            vel[i] = random(0,0.1f);//0.1f;//random(0,0.1f);
            rad[i] =  random(1f,50f);
            /*if(i+1!= vel.length){
                angle[i+1] = angle[i] + 0.5f;
            }*/
        }
        frameRate(1000);
        shape = createShape(SPHERE, 10);
    }




    @Override
    public void draw() {
        super.draw();
        background(255);
        noStroke();
        sphere(10);
        String str ="num:"+ num +" - "+ "Frame Rate"+ frameRate;
        getSurface().setTitle(str);


        for (int i = 0; i < num; i++){
            pushMatrix();
            rotate(angle[i]+= vel[i]);
            translate(20 * i + 20,0);
            scale(rad[i]);
            //sphere(10);
            shape(shape);
            popMatrix();
        }
        if(keyPressed){
            switch (key){
                case '+':
                    num++;
                    break;
                case '-':
                    num--;
                    break;
                default:
                    break;
            }
        }

    }

    public static void main(String[] args) {

        String name = Main.class.getName();
        PApplet.main(name);

    }

}
