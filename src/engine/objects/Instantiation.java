package engine.objects;

import engine.core.Engine;
import engine.materials.ObjectMaterial;
import engine.objects.light.Ambient;
import engine.objects.light.Directional;
import engine.objects.light.LightType;
import engine.objects.light.Point;
import engine.objects.primitives.Ball;
import engine.objects.primitives.ConeObject;
import engine.objects.primitives.Cube;
import engine.objects.primitives.CylinderObject;
import engine.objects.primitives.Plane;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.media.j3d.Shape3D;
import javax.vecmath.*;

/*
 * The Instantiation class takes a Prefab object and creates a GameObject.
 * The initial position and rotation are also defined here.
 */

public class Instantiation {
    
    public void add(Prefab prefab, Vector3f pos, Quat4d rot, Vector3f scale, BranchGroup branchGroup) {
        /*
         * Perform a check to see if the prefab contains a Mesh or is a primitive type.
         * If it's a primitive, then call the other add() method.
         */
        
        prefab.setPosition(pos);
        prefab.setRotation(rot);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(prefab.gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
    public GameObject add(PrimitiveType primitive, Vector3f pos, Quat4d rot, Vector3f scale, ObjectMaterial material) {
        switch(primitive) {
            case PLANE:
                return new Plane(pos, rot, scale.x, scale.z, material, Engine.worldGroup, "");
            case CUBE:
                return new Cube(pos, rot, scale, material, Engine.worldGroup, "");
            case SPHERE:
                return new Ball(pos, rot, scale.x, material, Engine.worldGroup, "");
            case CONE:
                return new ConeObject(pos, rot, scale, material, Engine.worldGroup, "");
            case CYLINDER:
                return new CylinderObject(pos, rot, scale, material, Engine.worldGroup, "");
        }
        
        return null;
    }
    
    public Light add(LightType light, Point3f pos, Color3f col) {
        switch(light) {
            case AMBIENT:
                return new Ambient(col, Engine.worldGroup);
            case DIRECTIONAL:
                return new Directional(new Vector3f(pos.x, pos.y, pos.z), col, Engine.worldGroup);
            case POINT:
                return new Point(pos, col, Engine.worldGroup);
        }
        
        return null;
    }
    
}
