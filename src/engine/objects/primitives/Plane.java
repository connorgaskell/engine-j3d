package engine.objects.primitives;

import engine.materials.ObjectMaterial;
import engine.objects.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Plane extends GameObject {

    public Plane(Vector3f pos, Quat4d rot, double scaleX, double scaleZ, ObjectMaterial material, BranchGroup branchGroup, String planeName) {
        Shape3D plane = createPlane(pos.x * 2, pos.y * 2, pos.z * 2, scaleX * 1, scaleZ * 1, material);
 
        bounds.addChild(plane);
  
        addChild(gameObject);
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(this);
        branchGroup.addChild(gameObjectGroup);
    }

    private Shape3D createPlane(double posX, double posY, double posZ, double scaleX, double scaleZ, ObjectMaterial material) {
        QuadArray quadArray = new QuadArray(4, QuadArray.COORDINATES | QuadArray.NORMALS | QuadArray.TEXTURE_COORDINATE_2);

        quadArray.setTextureCoordinate (0, new Point2f(0.0f,0.0f));
	quadArray.setTextureCoordinate (1, new Point2f(1.0f,0.0f)); 
    	quadArray.setTextureCoordinate (2, new Point2f(1.0f,1.0f));
    	quadArray.setTextureCoordinate (3, new Point2f(0.0f,1.0f));
        
        quadArray.setCoordinate(0, new Point3d(-scaleX + posX, posY, scaleZ + posZ));
        quadArray.setCoordinate(1, new Point3d(scaleX + posX, posY, scaleZ + posZ));
        quadArray.setCoordinate(2, new Point3d(scaleX + posX, posY, -scaleZ + posZ));
        quadArray.setCoordinate(3, new Point3d(-scaleX + posX, posY, -scaleZ + posZ));
        
        Vector3f quadArrayNormal = new Vector3f(0f, 0f, 1f);
        quadArray.setNormal(0, quadArrayNormal);
        quadArray.setNormal(1, quadArrayNormal);
        quadArray.setNormal(2, quadArrayNormal);
        quadArray.setNormal(3, quadArrayNormal);
        
        return new Shape3D(quadArray, material);
    }
}
