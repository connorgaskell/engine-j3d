package engine.objects.primitives;

import com.sun.j3d.utils.geometry.Cylinder;
import engine.materials.ObjectMaterial;
import engine.objects.GameObject;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3f;

public class CylinderObject extends GameObject {
    
    public Shape3D cylinderObject;
    
    public CylinderObject(Vector3f pos, Quat4d rot, Vector3f scale, ObjectMaterial material, BranchGroup branchGroup, String name) {
        cylinderObject = new Cylinder(scale.x, scale.y, Cylinder.GENERATE_NORMALS | Cylinder.GENERATE_TEXTURE_COORDS, material).getShape(Cylinder.TOP);
        cylinderObject.setAppearance(material);
        
        bounds.addChild(cylinderObject.getParent());

        setPosition(pos);
        setRotation(rot);

        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
}
