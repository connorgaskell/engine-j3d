package engine.script;

import engine.*;
import java.awt.event.KeyListener;
import java.util.*;
import javax.media.j3d.*;

public abstract class StandardScript extends Behavior implements KeyListener {
    private WakeupCriterion[] wakeupCriterion;
    private WakeupOr wakeupOr;
    
    public StandardScript() {
        setSchedulingBounds(Settings.INFINITE_BOUNDS);
    }
    
    @Override
    public void initialize() {
        wakeupCriterion = new WakeupCriterion[2];
        wakeupCriterion[0] = new WakeupOnElapsedFrames(0);
        wakeupCriterion[1] = new WakeupOnElapsedTime(1);
        
        wakeupOr = new WakeupOr(wakeupCriterion);
        wakeupOn(wakeupOr);
        start();
    }

    @Override
    public void processStimulus(Enumeration criteria) {
        WakeupCriterion criterion = (WakeupCriterion)criteria.nextElement();

        if(criterion instanceof WakeupOnElapsedFrames) {
            frameUpdate();
        } else if(criterion instanceof WakeupOnElapsedTime) {
            update();
        }
        
        wakeupOn(wakeupOr);
    }
    
    public abstract void start();
    public abstract void update();
    public abstract void frameUpdate();
    public abstract void lateUpdate();
    
}
