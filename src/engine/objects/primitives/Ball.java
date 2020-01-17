package engine.objects.primitives;

import com.sun.j3d.utils.geometry.*;
import engine.materials.ObjectMaterial;
import engine.objects.GameObject;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Ball extends GameObject {
    
    public Shape3D ballObject;
    
    public Ball(Vector3f pos, Quat4d rot, float scale, ObjectMaterial material, BranchGroup branchGroup, String name) {
        ballObject = new Sphere(scale, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS, 50).getShape();
        /*Appearance shapeAppearance = new Appearance();
        shapeAppearance.getRenderingAttributes();
        
        PolygonAttributes polygonAttributes = new PolygonAttributes();
        polygonAttributes.setCapability(PolygonAttributes.ALLOW_CULL_FACE_WRITE);
        shapeAppearance.setPolygonAttributes(polygonAttributes);
        
        ColoringAttributes sphereCA = new ColoringAttributes(color, 1);
        shapeAppearance.setColoringAttributes(sphereCA);*/
        ballObject.setAppearance(material);
        
        bounds.addChild(ballObject.getParent());

        setPosition(pos);

        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
}
