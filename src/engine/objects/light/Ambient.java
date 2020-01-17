package engine.objects.light;

import engine.Settings;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Ambient extends AmbientLight {
    
    public Ambient(Color3f col) {
        setColor(col);
        setInfluencingBounds(Settings.INFINITE_BOUNDS);
    }
    
    public void changeColor(Color3f color) {
        setColor(color);
    }
    
}