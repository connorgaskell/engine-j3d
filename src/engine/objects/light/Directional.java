package engine.objects.light;

import engine.Settings;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Directional extends DirectionalLight {

    public Directional(Point3f pos, Color3f col) {
        setColor(col);
        setDirection(new Vector3f(pos.x, pos.y, pos.z));
        setEnable(true);
        setInfluencingBounds(Settings.INFINITE_BOUNDS);
    }
    
    public void changeColor(Color3f color) {
        setColor(color);
    }
    
}
