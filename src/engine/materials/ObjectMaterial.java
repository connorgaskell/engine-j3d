package engine.materials;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class ObjectMaterial extends Appearance {
    
    public ObjectMaterial(Color3f ambientColor, Color3f emissionColor, Color3f diffuseColor, Color3f specularColor, float lightIntensity, String texture) {
        Material material = new Material(ambientColor, emissionColor, diffuseColor, specularColor, lightIntensity);
        material.setLightingEnable(true);
        setMaterial(material);
        
        try {
            TextureLoader objectTexture = new TextureLoader(texture, null);
            setTexture(objectTexture.getTexture());
        } catch(NullPointerException e) { }
    }
    
    public ObjectMaterial(Color3f ambientColor, Color3f emissionColor, Color3f diffuseColor, Color3f specularColor, float lightIntensity) {
        Material material = new Material(ambientColor, emissionColor, diffuseColor, specularColor, lightIntensity);
        material.setLightingEnable(true);
        setMaterial(material);
    }
    
}
