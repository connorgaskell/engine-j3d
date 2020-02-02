package engine.behaviors;

import com.sun.j3d.utils.picking.PickCanvas;

import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Point3d;


public abstract class PickMouseBehavior extends Behavior {
    protected PickCanvas pickCanvas;

    protected WakeupCriterion[] conditions;
    protected WakeupOr wakeupCondition;
    protected boolean buttonPress = false;

    protected TransformGroup currGrp;
    protected static final boolean debug = false;
    protected MouseEvent mevent;

    public PickMouseBehavior(Canvas3D canvas, BranchGroup root, Bounds bounds){
        super();
        currGrp = new TransformGroup();
        currGrp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        currGrp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        root.addChild(currGrp);
        pickCanvas = new PickCanvas(canvas, root);
    }

    @Override
    public void initialize() {
        conditions = new WakeupCriterion[2];
        conditions[0] = new WakeupOnAWTEvent(Event.MOUSE_MOVE);
        conditions[1] = new WakeupOnAWTEvent(Event.MOUSE_DOWN);
        wakeupCondition = new WakeupOr(conditions);

        wakeupOn(wakeupCondition);
    }

    @Override
    public void processStimulus (Enumeration criteria) {
        WakeupCriterion wakeup;
        AWTEvent[] evt = null;
        int xPos = 0, yPos = 0;

        while(criteria.hasMoreElements()) {
            wakeup = (WakeupCriterion)criteria.nextElement();
            if (wakeup instanceof WakeupOnAWTEvent) {
                evt = ((WakeupOnAWTEvent)wakeup).getAWTEvent();
            }
        }

        if (evt[0] instanceof MouseEvent){
            mevent = (MouseEvent) evt[0];

            xPos = mevent.getPoint().x;
            yPos = mevent.getPoint().y;
        }

        if (buttonPress){
            //updateScene(xPos, yPos);
        }
        
        wakeupOn (wakeupCondition);
    }

    public abstract void updateScene(int xPos, int yPos);
}
