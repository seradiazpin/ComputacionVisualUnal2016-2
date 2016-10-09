package coliciones;

import math.geom2d.Point2D;
import math.geom2d.conic.Circle2D;
import math.geom2d.line.Line2D;
import math.geom2d.line.LinearShape2D;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sergiodiazpinilla on 12/09/16.
 */
public class Circle {
    Circle2D circle;
    float cx;
    float cy;
    float r;
    public Circle(float cx,float cy,float r){
        circle = new Circle2D(cx,cy,r/2);
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }
    public void pointInter(Line2D line, PGraphics pg){
        try {
            Collection<Point2D> points =  circle.intersections(line);
            for(Point2D p:points){
                pg.ellipse((float)p.x(),(float)p.y(),10,10);
            }
        }catch (Exception e){
            //System.out.println("ERROR");
        }

    }

    public void display(PGraphics pg){
        pg.ellipse(cx,cy,r,r);
    }

}
