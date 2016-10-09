package RagDolls;

import fisica.*;
import org.jbox2d.dynamics.World;

/**
 * Created by sergiodiazpinilla on 10/09/16.
 */
public class Doll {
    final boolean DIST = true;

    public Doll(float cx,float cy, FWorld world, boolean type){
        if(type == DIST){
            distanceJoint(cx,cy,world);
        }else{
            rotationceJoint(cx,cy,world);
        }

    }

    public void distanceJoint(float cx,float cy,FWorld world){

        //Head
        FCircle head = new FCircle(25);
        head.setPosition(0+cx,10+cy);

        //Body
        FBox chestUp = new FBox(30, 15);
        chestUp.setPosition(0+cx,-10+cy);

        FBox chestCenter = new FBox(30, 15);
        chestCenter.setPosition(0+cx,-25+cy);

        FBox chestDow = new FBox(30, 15);
        chestDow.setPosition(0+cx,-40+cy);

        world.add(head);
        world.add(chestUp);
        world.add(chestCenter);
        world.add(chestDow);

        FDistanceJoint cuello = new FDistanceJoint(head, chestUp);
        cuello.calculateLength();
        //cuello.setCollideConnected(false);
        cuello.setDamping(0);
        cuello.setFrequency(0);



        FDistanceJoint chestUM = new FDistanceJoint(chestUp, chestCenter);
        chestUM.calculateLength();
        //chestUM.setCollideConnected(false);
        chestUM.setDamping(0);
        chestUM.setFrequency(0);



        FDistanceJoint chestMD = new FDistanceJoint(chestCenter, chestDow);
        chestMD.calculateLength();
        //chestMD.setCollideConnected(false);
        chestMD.setDamping(0);
        chestMD.setFrequency(0);

        world.add(cuello);
        world.add(chestUM);
        world.add(chestMD);
        // Rigth Leg

        FBox rightLegUp = new FBox(15, 30);
        rightLegUp.setFill(255,0,0,100);
        rightLegUp.setPosition(-7+cx,-63+cy);

        FBox rightLegDown = new FBox(15, 30);
        rightLegDown.setPosition(-7+cx,-93+cy);

        world.add(rightLegUp);
        world.add(rightLegDown);


        FDistanceJoint rightKnee = new FDistanceJoint(rightLegUp, rightLegDown);
        rightKnee.setDamping(0);
        rightKnee.setCollideConnected(false);
        rightKnee.setFrequency(0);
        rightKnee.setAnchor1(0,-rightLegUp.getHeight()/3);
        rightKnee.setAnchor2(0, rightLegDown.getHeight()/3);
        rightKnee.calculateLength();

        world.add(rightKnee);

        //Left Leg

        FBox leftLegUp = new FBox(15,30);
        leftLegUp.setPosition(7+cx,-63+cy);


        FBox leftLegDown = new FBox(15, 30);
        leftLegDown.setPosition(7+cx,-93+cy);

        world.add(leftLegUp);
        world.add(leftLegDown);

        FDistanceJoint leftKnee = new FDistanceJoint(leftLegUp, leftLegDown);
        leftKnee.setDamping(0);
        leftKnee.setFrequency(0);
        leftKnee.setCollideConnected(false);
        leftKnee.setAnchor1(0,-leftLegUp.getHeight()/3);
        leftKnee.setAnchor2(0,leftLegDown.getHeight()/3);
        leftKnee.calculateLength();

        FDistanceJoint hipLf = new FDistanceJoint(leftLegUp, chestDow);
        hipLf.setDamping(0);
        hipLf.setFrequency(0);
        hipLf.setCollideConnected(false);
        hipLf.setAnchor1(0,leftLegUp.getHeight()/3);
        hipLf.setAnchor2(chestDow.getWidth()/3, -chestDow.getHeight()/3);
        hipLf.calculateLength();


        world.add(hipLf);
        world.add(leftKnee);

        FDistanceJoint hipRg = new FDistanceJoint(rightLegUp, chestDow);
        hipRg.setDamping(0);
        hipRg.setCollideConnected(false);
        hipRg.setFrequency(0);
        hipRg.setAnchor1(0,leftLegUp.getHeight()/3);
        hipRg.setAnchor2(-chestDow.getWidth()/3, -chestDow.getHeight()/3);
        hipRg.calculateLength();

        world.add(hipRg);


        // Right Arm
        FBox rightArmUp = new FBox(15, 30);
        rightArmUp.setPosition(-20+cx,-15+cy);

        FBox rightArmDown = new FBox(15, 30);
        rightArmDown.setPosition(-20+cx,-45+cy);


        world.add(rightArmDown);
        world.add(rightArmUp);


        FDistanceJoint elbowRg = new FDistanceJoint(rightArmUp, rightArmDown);
        elbowRg.setDamping(0);
        elbowRg.setFrequency(0);
        elbowRg.setCollideConnected(false);
        elbowRg.setAnchor1(0,-rightArmUp.getHeight()/3);
        elbowRg.setAnchor2(0, rightArmDown.getHeight()/3);
        elbowRg.calculateLength();


        FDistanceJoint shoulderRg = new FDistanceJoint(rightArmUp, chestUp);
        shoulderRg.setDamping(0);
        shoulderRg.setFrequency(0);
        shoulderRg.setCollideConnected(false);
        shoulderRg.setAnchor1(rightArmUp.getWidth()/3, rightArmUp.getHeight()/3);
        shoulderRg.setAnchor2(-chestUp.getWidth()/3,0);
        shoulderRg.calculateLength();

        world.add(shoulderRg);
        world.add(elbowRg);

        //Left Arm

        FBox leftArmUp = new FBox(15, 30);
        leftArmUp.setPosition(20+cx,-15+cy);

        FBox leftArmDown = new FBox(15, 30);
        leftArmDown.setPosition(20+cx,-45+cy);


        world.add(leftArmDown);
        world.add(leftArmUp);


        FDistanceJoint elbowLf = new FDistanceJoint(leftArmUp, leftArmDown);

        elbowLf.setDamping(0);
        elbowLf.setFrequency(0);
        elbowLf.setCollideConnected(false);
        elbowLf.setAnchor1(0,-rightArmUp.getHeight()/3);
        elbowLf.setAnchor2(0, rightArmDown.getHeight()/3);
        elbowLf.calculateLength();

        FDistanceJoint shoulderLf = new FDistanceJoint(leftArmUp, chestUp);
        shoulderLf.setDamping(0);
        shoulderLf.setFrequency(0);
        shoulderLf.setCollideConnected(false);
        shoulderLf.setAnchor1(-leftArmUp.getWidth()/3, leftArmUp.getHeight()/3);
        shoulderLf.setAnchor2(chestUp.getWidth()/3,0);
        shoulderLf.calculateLength();

        world.add(shoulderLf);
        world.add(elbowLf);

    }

    public void  rotationceJoint(float cx,float cy,FWorld world){
        //Head
        FCircle head = new FCircle(25);
        head.setPosition(0+cx,10+cy);

        //Body
        FBox chestUp = new FBox(30, 15);
        chestUp.setPosition(0+cx,-10+cy);

        FBox chestCenter = new FBox(30, 15);
        chestCenter.setPosition(0+cx,-25+cy);

        FBox chestDow = new FBox(30, 15);
        chestDow.setPosition(0+cx,-40+cy);

        world.add(head);
        world.add(chestUp);
        world.add(chestCenter);
        world.add(chestDow);

        FRevoluteJoint cuello = new FRevoluteJoint(head, chestUp);




        FRevoluteJoint chestUM = new FRevoluteJoint(chestUp, chestCenter);


        FRevoluteJoint chestMD = new FRevoluteJoint(chestCenter, chestDow);


        world.add(cuello);
        world.add(chestUM);
        world.add(chestMD);
        // Rigth Leg

        FBox rightLegUp = new FBox(15, 30);
        rightLegUp.setFill(255,0,0);
        rightLegUp.setPosition(-7+cx,-63+cy);

        FBox rightLegDown = new FBox(15, 30);
        rightLegDown.setPosition(-7+cx,-93+cy);

        world.add(rightLegUp);
        world.add(rightLegDown);


        FRevoluteJoint rightKnee = new FRevoluteJoint(rightLegUp, rightLegDown);


        world.add(rightKnee);

        //Left Leg

        FBox leftLegUp = new FBox(15,30);
        leftLegUp.setPosition(7+cx,-63+cy);

        FBox leftLegDown = new FBox(15, 30);
        leftLegDown.setPosition(7+cx,-93+cy);

        world.add(leftLegUp);
        world.add(leftLegDown);

        FRevoluteJoint leftKnee = new FRevoluteJoint(leftLegUp, leftLegDown);


        FRevoluteJoint hipLf = new FRevoluteJoint(leftLegUp, chestDow);



        world.add(hipLf);
        world.add(leftKnee);

        FRevoluteJoint hipRg = new FRevoluteJoint(rightLegUp, chestDow);


        world.add(hipRg);


        // Right Arm
        FBox rightArmUp = new FBox(15, 30);
        rightArmUp.setPosition(-20+cx,-15+cy);

        FBox rightArmDown = new FBox(15, 30);
        rightArmDown.setPosition(-20+cx,-45+cy);


        world.add(rightArmDown);
        world.add(rightArmUp);


        FRevoluteJoint elbowRg = new FRevoluteJoint(rightArmUp, rightArmDown);



        FRevoluteJoint shoulderRg = new FRevoluteJoint(rightArmUp, chestUp);


        world.add(shoulderRg);
        world.add(elbowRg);

        //Left Arm

        FBox leftArmUp = new FBox(15, 30);
        leftArmUp.setPosition(20+cx,-15+cy);

        FBox leftArmDown = new FBox(15, 30);
        leftArmDown.setPosition(20+cx,-45+cy);


        world.add(leftArmDown);
        world.add(leftArmUp);


        FRevoluteJoint elbowLf = new FRevoluteJoint(leftArmUp, leftArmDown);



        FRevoluteJoint shoulderLf = new FRevoluteJoint(leftArmUp, chestUp);


        world.add(shoulderLf);
        world.add(elbowLf);


    }

    
    
}
