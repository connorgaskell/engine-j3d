package engine.game;

import com.sun.j3d.utils.universe.*;
import engine.*;
import engine.script.ScriptLoader;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

public class Game extends JPanel {
    public static BranchGroup rootGroup, worldGroup, fogGroup;
    public static ArrayList<Node> nodeList = new ArrayList<>();
    
    private void createWorld() {
        rootGroup = new BranchGroup();
        rootGroup.setName("rootGroup");
        nodeList.add(rootGroup);
        
        worldGroup = new BranchGroup();
        
        try {
            new ScriptLoader();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        rootGroup.addChild(worldGroup);
        rootGroup.addChild(fogGroup);
        rootGroup.compile();
    }
    
    public Game() {
        setLayout(new BorderLayout());
        GraphicsConfigTemplate3D configTemplate3D = new GraphicsConfigTemplate3D();
        configTemplate3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
        GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getBestConfiguration(configTemplate3D);
        
        Canvas3D canvas = new Canvas3D(config) {
            Graphics2D g = this.getGraphics2D();

            @Override
            public void postRender() {
                Dimension dimensions = this.getSize();
                double screenWidth = dimensions.getWidth();
                double screenHeight = dimensions.getHeight();

                g.setColor(Color.WHITE);

                g.drawString("Test",(int)(screenWidth / 2) - 45, (int)(screenHeight) - 20);

                this.getGraphics2D().flush(false);
                revalidate();
            }
        };
        
        canvas.setFocusable(true);
        canvas.requestFocus();
        
        SimpleUniverse universe = new SimpleUniverse(canvas);

        fogGroup = new BranchGroup();
        
        createWorld();
        
        add("Center", canvas);

        Viewer viewer = universe.getViewer();
        View view = viewer.getView();
        view.setBackClipDistance(Settings.RENDER_DISTANCE);
        view.setSceneAntialiasingEnable(true);
        view.setDepthBufferFreezeTransparent(true);
        view.setTransparencySortingPolicy(View.PERSPECTIVE_PROJECTION);
        view.setScreenScalePolicy(View.SCALE_EXPLICIT);
        view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
        view.setWindowEyepointPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
        view.setFieldOfView(1.5f);
        
        //OrbitCamera camera = new OrbitCamera(universe, canvas, 12.0f, Settings.camRotationSpeed);
        
        //camera.setOrbit(new Point3d(0f, 0f, 0f));
        
        universe.getViewingPlatform().setNominalViewingTransform();
        Transform3D viewPosTransform = new Transform3D();
        viewPosTransform.set(new Vector3f(0.0f, 0.0f, 125.0f));
        Transform3D viewRotTransform = new Transform3D();
        viewRotTransform.setRotation(new Quat4d(25 * (Math.PI / 180), 0.0f, 0.0f, -1.0f));
        viewRotTransform.mul(viewPosTransform);
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(viewRotTransform);
        universe.addBranchGraph(rootGroup);
    }
    
}
