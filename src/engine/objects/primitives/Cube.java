package engine.objects.primitives;

import com.sun.j3d.utils.geometry.*;
import engine.objects.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Cube extends GameObject {
    
    public Shape3D cubeObject;
    
    public Cube(Vector3f pos, Quat4d rot, Vector3f scale, Color3f color, BranchGroup branchGroup, String name) {
        Appearance shapeAppearance = new Appearance();
        shapeAppearance.getRenderingAttributes();
        
        PolygonAttributes polygonAttributes = new PolygonAttributes();
        polygonAttributes.setCapability(PolygonAttributes.ALLOW_CULL_FACE_WRITE);
        shapeAppearance.setPolygonAttributes(polygonAttributes);
        
        ColoringAttributes cubeCA = new ColoringAttributes(color, 1);
        shapeAppearance.setColoringAttributes(cubeCA);
        
        cubeObject = (new Box(scale.x, scale.y, scale.z, shapeAppearance).getShape(Box.TOP));
        
        bounds.addChild(cubeObject.getParent());
        setPosition(pos);
        setRotation(rot);
        this.name = name;
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
}
