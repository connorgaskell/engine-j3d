package engine.objects.primitives;

import engine.materials.ObjectMaterial;
import engine.objects.GameObject;
import java.awt.Font;
import java.awt.geom.Line2D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.*;

public class Text3D extends GameObject {
    
    javax.media.j3d.Text3D t;
    
    public Text3D(String text, Vector3f pos, ObjectMaterial material, BranchGroup branchGroup) {
        Line2D.Double line = new Line2D.Double(0, 0, 2.0f, 0);
        FontExtrusion extrudePath = new FontExtrusion(line);
        Font3D font3D = new Font3D(new Font("Helvetica", Font.BOLD, 20), extrudePath);
        
        t = new javax.media.j3d.Text3D(font3D, text, new Point3f(0, 0, 0));
        t.setCapability(javax.media.j3d.Text3D.ALLOW_STRING_WRITE);

        Shape3D textShape = new Shape3D((Geometry)t);
        textShape.setAppearance(material);
        bounds.addChild(textShape);
        setPosition(pos);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
    public void setText(String text) {
        t.setString(text);
    }
    
}
