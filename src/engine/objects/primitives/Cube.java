package engine.objects.primitives;

import com.sun.j3d.utils.geometry.*;
import engine.materials.ObjectMaterial;
import engine.objects.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Cube extends GameObject {
    
    public Shape3D cubeObject;
    
    public Cube(Vector3f pos, Quat4d rot, Vector3f scale, ObjectMaterial material, BranchGroup branchGroup, String name) {
        cubeObject = (new Box(scale.x, scale.y, scale.z, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, material).getShape(Box.TOP));
        
        bounds.addChild(cubeObject.getParent());
        setPosition(pos);
        setRotation(rot);
        //setName(name);
        cubeObject.setName(name);
        
        //BranchGroup gameObjectGroup = new BranchGroup();
        addChild(gameObject);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(this);
        branchGroup.addChild(gameObjectGroup);
    }
    
}
