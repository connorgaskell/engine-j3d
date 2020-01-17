package engine.objects.light;

import engine.Settings;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Point extends PointLight {
    
    public Point(Point3f pos, Color3f col) {
        setColor(col);
        setPosition(pos);
        setAttenuation(1f, 3f, 0f);
        setEnable(true);
        setInfluencingBounds(Settings.INFINITE_BOUNDS);
    }
    
    public void changeColor(Color3f color) {
        setColor(color);
    }
    
}
