package engine.scene;

import engine.core.Engine;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Point3d;

public class Sky {
    
    public void setSky(String texture) {
        BranchGroup skyGroup = new BranchGroup();
        Background skyBg = new Background();
        skyBg.setApplicationBounds(new BoundingSphere(new Point3d(0, 0, 0), 1e100));
        
        Appearance starAppearance = new Appearance();
        
        starAppearance.setTexture(new TextureLoader(texture, null).getTexture());
        
        Sphere skySphere = new Sphere(1.0f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_NORMALS_INWARD | Sphere.GENERATE_TEXTURE_COORDS, 40, starAppearance);
        
        skyGroup.addChild(skySphere);
        skyBg.setGeometry(skyGroup);
        
        BranchGroup skyBgGroup = new BranchGroup();
        skyBgGroup.addChild(skyBg);

        Engine.worldGroup.addChild(skyBgGroup);
    }
    
}
