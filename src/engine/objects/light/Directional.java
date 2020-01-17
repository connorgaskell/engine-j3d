package engine.objects.light;

import engine.Settings;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Directional extends DirectionalLight {

    public Directional(Vector3f pos, Color3f col, BranchGroup branchGroup) {
        setColor(col);
        setDirection(pos);
        setEnable(true);
        setInfluencingBounds(Settings.INFINITE_BOUNDS);

        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(this);
        branchGroup.addChild(gameObjectGroup);
    }
    
    public void changeColor(Color3f color) {
        setColor(color);
    }
    
}
