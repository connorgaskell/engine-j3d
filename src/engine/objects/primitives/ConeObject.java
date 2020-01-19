package engine.objects.primitives;

import com.sun.j3d.utils.geometry.Cone;
import engine.materials.ObjectMaterial;
import engine.objects.GameObject;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3f;

public class ConeObject extends GameObject {
    
    public Shape3D coneObject;
    
    public ConeObject(Vector3f pos, Quat4d rot, Vector3f scale, ObjectMaterial material, BranchGroup branchGroup, String name) {
        coneObject = new Cone(scale.x, scale.y, material).getShape(Cone.BODY);//new ConeObject(scale, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS, 50).getShape();
        coneObject.setAppearance(material);
        
        bounds.addChild(coneObject.getParent());

        setPosition(pos);

        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
}
