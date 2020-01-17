package engine.objects.light;

import engine.Settings;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

public class Point extends PointLight {
    
    public Point(Point3f pos, Color3f col, BranchGroup branchGroup) {
        setColor(col);
        setPosition(pos);
        setAttenuation(5f, 3f, 0f);
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
