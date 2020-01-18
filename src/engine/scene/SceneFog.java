package engine.scene;

import engine.Settings;
import engine.core.Engine;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ExponentialFog;
import javax.media.j3d.Fog;
import javax.vecmath.Color3f;

public class SceneFog {
    
    public Fog addExponentialFog(Color3f col, float density) {
        ExponentialFog fog = new ExponentialFog();
        fog.setColor(col);
        fog.setDensity(density);
        fog.setCapability(Fog.ALLOW_COLOR_WRITE);
        fog.setCapability(ExponentialFog.ALLOW_DENSITY_WRITE);
        fog.setInfluencingBounds(Settings.INFINITE_BOUNDS);
        
        BranchGroup fogGroup = new BranchGroup();
        fogGroup.addChild(fog);
        Engine.worldGroup.addChild(fogGroup);
        return fog;
    }
    
}
