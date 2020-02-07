package engine.objects;

import engine.core.Engine;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4d;
import javax.vecmath.SingularMatrixException;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class GameObject extends TransformGroup {
    public TransformGroup gameObject, position, rotation, scale, bounds;
    private String name, tag, layer;
    public Vector3f origin;
    
    public GameObject() {
        bounds = new TransformGroup();
        setCapabilities(bounds, "bounds");
        
        scale = new TransformGroup();
        setCapabilities(scale, "scale");
        scale.addChild(bounds);
        
        rotation = new TransformGroup();
        setCapabilities(rotation, "rotation");
        rotation.addChild(scale);

        position = new TransformGroup();
        setCapabilities(position, "position");
        position.addChild(rotation);

        gameObject = new TransformGroup();
        setCapabilities(gameObject, "gameObject");
        gameObject.addChild(position);
    }
    
    public void translate(Vector3f translation) {
        Transform3D transform3D = getTransform(position);
        Vector3f pos = getPosition();
        pos.add(translation);
        transform3D.setTranslation(pos);
        setTransform(position, transform3D);
    }
    
    private void setTransform(TransformGroup transformGroup, Transform3D transform3D) {
        transformGroup.setTransform(transform3D);
    }
    
    public void setPosition(Vector3f pos) {
        Transform3D transform3D = getTransform(position);
        transform3D.set(pos);
        position.setTransform(transform3D);
    }
    
    public void setRotation(Quat4d rot) {
        Transform3D transform3D = getTransform(rotation);
        Transform3D transform3D1 = new Transform3D();

        transform3D1.setRotation(rot);
        transform3D.mul(transform3D1);

        rotation.setTransform(transform3D);
    }
    
    public void setRotation(Transform3D newT3D) {
        Transform3D transform3D = getTransform(rotation);
        Transform3D transform3D1 = newT3D;

        transform3D.mul(transform3D1);

        rotation.setTransform(transform3D);
    }
    
    public void lookAt(Vector3f center) {
        Transform3D transform3D = getTransform(rotation);
        Transform3D transform3D1 = new Transform3D(transform3D);

        Vector3f translation = new Vector3f();
        transform3D1.get(translation);
  
        transform3D1.lookAt(new Point3d(translation), new Point3d(center.x, 0, center.z), new Vector3d(0, 1, 0));
        
        try {
            transform3D1.invert();
            rotation.setTransform(transform3D1);
        } catch(SingularMatrixException e) { }
    }
    
    public Transform3D getTransform(TransformGroup transformGroup) {
        Transform3D transform3D = new Transform3D();
        transformGroup.getTransform(transform3D);
        return transform3D;
    }
    
    public Vector3f getPosition() {
        Transform3D transform3D = getTransform(position);
        Vector3f pos = new Vector3f();
        transform3D.get(pos);
        return pos;
    }

    public Vector3f getRotation() {
        Transform3D transform3D = getTransform(rotation);
        Vector3f rot = new Vector3f();
        transform3D.get(rot);
        return rot;
    }

    /*@Override
    public void setName(String name) {
        this.name = name;
    }*/
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public void setLayer(String layer) {
        this.layer = layer;
    }
    
    /*@Override
    public String getName() {
        return name;
    }*/
    
    public String getTag() {
        return tag;
    }
    
    public String getLayer() {
        return layer;
    }
    
    private void setCapabilities(TransformGroup transformGroup, String groupName) {
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(Node.ALLOW_BOUNDS_READ);
        
        transformGroup.setUserData(groupName);
    }
    
}