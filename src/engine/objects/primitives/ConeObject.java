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
        coneObject = new Cone(scale.x, scale.y, Cone.GENERATE_NORMALS | Cone.GENERATE_TEXTURE_COORDS, material).getShape(Cone.BODY);
        coneObject.setAppearance(material);
        
        bounds.addChild(coneObject.getParent());

        setPosition(pos);

        addChild(gameObject);
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(this);
        branchGroup.addChild(gameObjectGroup);
    }
    
}
