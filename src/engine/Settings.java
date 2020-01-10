package engine;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;

public class Settings {
    
    public static final String APP_NAME = "Test Game";
    public static final String APP_VERSION = "0.1.1";
    public static final boolean DISPLAY_APP_VERSION = true;
    public static final float RENDER_DISTANCE = 800.0f;
    
    public static final BoundingSphere INFINITE_BOUNDS = new BoundingSphere(new Point3d(0, 0, 0), 1e100);

}
