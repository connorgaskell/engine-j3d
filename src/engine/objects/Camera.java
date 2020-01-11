package engine.objects;

import com.sun.j3d.utils.universe.Viewer;
import engine.Settings;
import engine.core.Engine;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3f;

public class Camera {
    
    private Viewer viewer;
    private View view;
    
    public Camera() {
        viewer = Engine.scene.getViewer();
        view = viewer.getView();
        view.setBackClipDistance(Settings.RENDER_DISTANCE);
        view.setSceneAntialiasingEnable(true);
        view.setDepthBufferFreezeTransparent(true);
        view.setTransparencySortingPolicy(View.PERSPECTIVE_PROJECTION);
        view.setScreenScalePolicy(View.SCALE_EXPLICIT);
        view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
        view.setWindowEyepointPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
        view.setFieldOfView(1.5f);

        Engine.scene.getViewingPlatform().setNominalViewingTransform();
        Transform3D viewPosTransform = new Transform3D();
        viewPosTransform.set(new Vector3f(0.0f, 0.0f, 125.0f));
        Transform3D viewRotTransform = new Transform3D();
        viewRotTransform.setRotation(new Quat4d(25 * (Math.PI / 180), 0.0f, 0.0f, -1.0f));
        viewRotTransform.mul(viewPosTransform);
        Engine.scene.getViewingPlatform().getViewPlatformTransform().setTransform(viewRotTransform);
    }
    
    public void setPosition(Vector3f pos) {
        Transform3D transform3D = getTransform(Engine.scene.getViewingPlatform().getViewPlatformTransform());
        transform3D.set(pos);
        Engine.scene.getViewingPlatform().getViewPlatformTransform().setTransform(transform3D);
    }
    
    public void setRotation(Quat4d rot) {
        Transform3D transform3D = getTransform(Engine.scene.getViewingPlatform().getViewPlatformTransform());
        Transform3D transform3D1 = new Transform3D();

        transform3D1.setRotation(rot);
        transform3D.mul(transform3D1);

        Engine.scene.getViewingPlatform().getViewPlatformTransform().setTransform(transform3D);
    }
    
    public Transform3D getTransform(TransformGroup transformGroup) {
        Transform3D transform3D = new Transform3D();
        transformGroup.getTransform(transform3D);
        return transform3D;
    }
    
    public Vector3f getPosition() {
        Transform3D transform3D = getTransform(Engine.scene.getViewingPlatform().getViewPlatformTransform());
        Vector3f pos = new Vector3f();
        transform3D.get(pos);
        return pos;
    }
    
    public Vector3f getRotation() {
        Transform3D transform3D = getTransform(Engine.scene.getViewingPlatform().getViewPlatformTransform());
        Vector3f rot = new Vector3f();
        transform3D.get(rot);
        return rot;
    }
    
}
