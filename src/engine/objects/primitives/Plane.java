package engine.objects.primitives;

import engine.objects.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Plane extends GameObject {

    public Plane(Vector3f pos, Quat4d rot, double scaleX, double scaleZ, Color3f color, BranchGroup branchGroup, String planeName) {
        Shape3D plane = createPlane(pos.x * 2, pos.y * 2, pos.z * 2, scaleX * 1, scaleZ * 1);
        
        Appearance planeAppearance = new Appearance();
        planeAppearance.getRenderingAttributes();
        
        PolygonAttributes polygonAttributes = new PolygonAttributes();
        polygonAttributes.setCapability(PolygonAttributes.ALLOW_CULL_FACE_WRITE);
        planeAppearance.setPolygonAttributes(polygonAttributes);
        
        ColoringAttributes planeCA = new ColoringAttributes(color, 1);
        planeAppearance.setColoringAttributes(planeCA);
        plane.setAppearance(planeAppearance);
        
        this.name = planeName;
        plane.setName(this.name);
        
        bounds.addChild(plane);
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(gameObject);
        branchGroup.addChild(gameObjectGroup);
    }

    private Shape3D createPlane(double posX, double posY, double posZ, double scaleX, double scaleZ) {
        QuadArray quadArray = new QuadArray(4, QuadArray.COORDINATES);

        quadArray.setCoordinate(0, new Point3d(-scaleX + posX, posY, scaleZ + posZ));
        quadArray.setCoordinate(1, new Point3d(scaleX + posX, posY, scaleZ + posZ));
        quadArray.setCoordinate(2, new Point3d(scaleX + posX, posY, -scaleZ + posZ));
        quadArray.setCoordinate(3, new Point3d(-scaleX + posX, posY, -scaleZ + posZ));

        return new Shape3D(quadArray);
    }
}
