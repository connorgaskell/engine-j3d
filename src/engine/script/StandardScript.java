package engine.script;

import engine.*;
import engine.objects.Instantiation;
import engine.objects.Prefab;
import engine.objects.PrimitiveType;
import java.awt.event.KeyListener;
import java.util.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public abstract class StandardScript extends Behavior implements KeyListener {
    private final Instantiation instantiation;
    private WakeupCriterion[] wakeupCriterion;
    private WakeupOr wakeupOr;
    
    public void instantiate(String prefabFile, Vector3f pos, Quat4d rot, Vector3f scale, BranchGroup branchGroup) {
        Prefab prefab = new Prefab(prefabFile);
        instantiation.add(prefab, pos, rot, scale, branchGroup);
    }
    
    public void instantiate(PrimitiveType primitive, Vector3d pos, Quat4d rot, Vector3f scale, Color3f col) {
        instantiation.add(primitive, pos, rot, scale, col);
    }
    
    public StandardScript() {
        setSchedulingBounds(Settings.INFINITE_BOUNDS);
        instantiation = new Instantiation();
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
