package AlgoritmoPintor;

import java.util.Comparator;

import processing.core.PGraphics;
import processing.core.PShape;
import processing.core.PVector;
import remixlab.dandelion.core.Camera;

public class TestComparator implements Comparator<PShape> {

	private Camera camera;
    private PGraphics pg;
    float zmin1;
    float zmax1;

    float zmin2;
    float zmax2;


	public TestComparator(Camera camera , PGraphics pg) {
		super();
		this.camera = camera;
        this.pg = pg;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public int compare(PShape o1, PShape o2) {
		// TODO
        test0(o1, o2);
		return 0;
	}

    public void test0(PShape o1, PShape o2){
        PVector v1 = o1.getVertex(0);
        PVector v2 = o1.getVertex(1);
        PVector v3 = o1.getVertex(2);
        PVector cameraPos = new PVector(camera.position().x(),camera.position().y(),camera.position().z());



        pg.ellipse(v1.x,v2.y,10,10);
        /*
        System.out.println(ch);
        for(int i = 0; i<= ch.length;i++){
            System.out.println(ch[i].depth);
        }
        System.out.println("---------------------");*/


    }
    public boolean test1(PShape o1, PShape o2){
        return false;
    }

    public boolean test2(PShape o1, PShape o2){
        return false;
    }

    public float min(float a, float b, float c){
        return Math.min(Math.min(a,b),c);
    }

    public float max(float a, float b, float c){
        return Math.max(Math.max(a,b),c);
    }
}