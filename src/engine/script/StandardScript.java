package engine.script;

import engine.*;
import engine.behaviors.Pick;
import engine.core.Engine;
import engine.materials.ObjectMaterial;
import engine.objects.*;
import engine.objects.light.LightType;
import engine.scene.SceneFog;
import engine.scene.Sky;
import engine.util.RandomRange;
import java.awt.event.KeyListener;
import engine.objects.primitives.Text3D;
import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public abstract class StandardScript extends Behavior implements KeyListener {
    private final Instantiation instantiation;
    private WakeupCriterion[] wakeupCriterion;
    private WakeupOr wakeupOr;
    
    public Vector3f mousePosition(Pick mouse) {
        if(mouse.get3DIntercept() == null) {
            return new Vector3f(0, 0, 0);
        } else {
            return new Vector3f(mouse.get3DIntercept());
        }
    }
    
    public Pick enableMouse(ArrayList<String> layers) {
        Pick mousePick = new Pick(Engine.canvas, Engine.rootGroup, Settings.INFINITE_BOUNDS, layers);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(mousePick);
        Engine.rootGroup.addChild(gameObjectGroup);
        return mousePick;
    }

    public void instantiate(String prefabFile, Vector3f pos, Quat4d rot, Vector3f scale, BranchGroup branchGroup) {
        Prefab prefab = new Prefab(prefabFile);
        instantiation.add(prefab, pos, rot, scale, branchGroup);
    }
    
    public GameObject instantiate(String path, Vector3f pos, Quat4d rot, Vector3f scale, ObjectMaterial material) {
        return instantiation.add(path, pos, rot, scale, material);
    }
    
    public GameObject instantiate(PrimitiveType primitive, Vector3f pos, Quat4d rot, Vector3f scale, ObjectMaterial material) {
        return instantiation.add(primitive, pos, rot, scale, material);
    }
    
    public Light light(LightType light, Point3f pos, Color3f col) {
        return instantiation.add(light, pos, col);
    }
    
    public Text3D text3d(String text, Vector3f pos, ObjectMaterial material) {
        return new engine.objects.primitives.Text3D(text, pos, material, Engine.worldGroup);
    }
    
    public Fog fog(Color3f col, float density) {
        SceneFog sceneFog = new SceneFog();
        return sceneFog.addExponentialFog(col, density);
    }
    
    public Fog fog(Color3f col, float frontDistance, float backDistance) {
        SceneFog sceneFog = new SceneFog();
        return sceneFog.addLinearFog(col, frontDistance, backDistance);
    }
    
    public void sky(String texture) {
        Sky sky = new Sky();
        sky.setSky(texture);
    }
    
    public float randomFloat(float min, float max) {
        RandomRange rand = new RandomRange();
        return rand.randomFloat(min, max);
    }
    
    public int randomInt(int min, int max) {
        RandomRange rand = new RandomRange();
        return rand.randomInteger(min, max);
    }
    
    public void processMouseEvent(MouseEvent evt) {
        if (evt.getID() == MouseEvent.MOUSE_PRESSED) {
            onMousePress(evt);
        } else if (evt.getID() == MouseEvent.MOUSE_RELEASED) {
            onMouseRelease(evt);
        } else if (evt.getID() == MouseEvent.MOUSE_MOVED) {
            onMouseMove(evt);
        }
    }
    
    public StandardScript() {
        setSchedulingBounds(Settings.INFINITE_BOUNDS);
        instantiation = new Instantiation();
    }
    
    @Override
    public void initialize() {
        wakeupCriterion = new WakeupCriterion[5];
        wakeupCriterion[0] = new WakeupOnElapsedFrames(0);
        wakeupCriterion[1] = new WakeupOnElapsedTime(1);
        wakeupCriterion[2] = new WakeupOnAWTEvent(Event.MOUSE_MOVE);
        wakeupCriterion[3] = new WakeupOnAWTEvent(Event.MOUSE_DOWN);
        wakeupCriterion[4] = new WakeupOnAWTEvent(Event.MOUSE_UP);
        
        wakeupOr = new WakeupOr(wakeupCriterion);
        wakeupOn(wakeupOr);
        start();
    }

    @Override
    public void processStimulus(Enumeration criteria) {
        AWTEvent[] evt = null;
        WakeupCriterion criterion;

        while(criteria.hasMoreElements()) {
            criterion = (WakeupCriterion)criteria.nextElement();
            if(criterion instanceof WakeupOnElapsedFrames) {
                frameUpdate();
            } else if(criterion instanceof WakeupOnElapsedTime) {
                update();
            } else if (criterion instanceof WakeupOnAWTEvent) {
                evt = ((WakeupOnAWTEvent)criterion).getAWTEvent();
                if (evt[0] instanceof MouseEvent){
                    processMouseEvent((MouseEvent)evt[0]);
                }
            }
        }
        
        wakeupOn(wakeupOr);
    }
    
    public abstract void start();
    public abstract void update();
    public abstract void frameUpdate();
    public abstract void lateUpdate();
    public abstract void onMousePress(MouseEvent evt);
    public abstract void onMouseRelease(MouseEvent evt);
    public abstract void onMouseMove(MouseEvent evt);
}
