package engine.objects.light;

import engine.Settings;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Ambient extends AmbientLight {
    
    public Ambient(Color3f col, BranchGroup branchGroup) {
        setColor(col);
        setInfluencingBounds(Settings.INFINITE_BOUNDS);
        setCapability(Light.ALLOW_COLOR_WRITE);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(this);
        branchGroup.addChild(gameObjectGroup);
    }
    
    public void changeColor(Color3f color) {
        setColor(color);
    }
    
}