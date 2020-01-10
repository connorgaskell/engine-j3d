package engine.objects.light;

import engine.Settings;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Ambient extends AmbientLight {
    
    public Ambient() {
        setColor(new Color3f(1.5f, 1.5f, 1.5f));
        setInfluencingBounds(Settings.INFINITE_BOUNDS);
    }
    
    public void changeColor(Color3f color) {
        setColor(color);
    }
    
}