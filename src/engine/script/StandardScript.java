package engine.script;

import engine.*;
import engine.materials.ObjectMaterial;
import engine.objects.*;
import engine.objects.light.LightType;
import engine.scene.SceneFog;
import engine.scene.Sky;
import engine.util.RandomRange;
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
    
    public GameObject instantiate(PrimitiveType primitive, Vector3f pos, Quat4d rot, Vector3f scale, ObjectMaterial material) {
        return instantiation.add(primitive, pos, rot, scale, material);
    }
    
    public Light instantiate(LightType light, Point3f pos, Color3f col) {
        return instantiation.add(light, pos, col);
    }
    
    public Fog fog(Color3f col, float density) {
        SceneFog sceneFog = new SceneFog();
        return sceneFog.addExponentialFog(col, density);
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
