package engine.core;

import com.sun.j3d.utils.universe.*;
import engine.*;
import engine.script.*;
import java.awt.*;
import java.util.*;
import java.util.logging.*;
import javax.media.j3d.*;
import javax.swing.*;

public class Engine extends JPanel {
    public static SimpleUniverse scene;
    public static BranchGroup rootGroup, worldGroup, fogGroup;
    public static ArrayList<Node> nodeList = new ArrayList<>();
    public static Canvas3D canvas;
    
    private void createBranches() {
        rootGroup = new BranchGroup();
        rootGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        rootGroup.setName("rootGroup");
        nodeList.add(rootGroup);
        
        worldGroup = new BranchGroup();
        worldGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        
        fogGroup = new BranchGroup();

        rootGroup.addChild(worldGroup);
        rootGroup.addChild(fogGroup);
        
        loadScripts();
        rootGroup.compile();
    }
    
    private void loadScripts() {
        try {
            new ScriptLoader();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Engine() {
        setLayout(new BorderLayout());
        GraphicsConfigTemplate3D configTemplate3D = new GraphicsConfigTemplate3D();
        configTemplate3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
        GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getBestConfiguration(configTemplate3D);

        canvas = new Canvas3D(config) {
            Graphics2D g = this.getGraphics2D();

            @Override
            public void postRender() {
                Dimension dimensions = this.getSize();
                double screenWidth = dimensions.getWidth();
                double screenHeight = dimensions.getHeight();

                g.setColor(Color.WHITE);

                g.drawString(Settings.APP_VERSION,(int)(screenWidth / 2) - 12, (int)(screenHeight) - 20);

                this.getGraphics2D().flush(false);
                revalidate();
            }
        };
        
        canvas.setFocusable(true);
        canvas.requestFocus();
        
        scene = new SimpleUniverse(canvas);

        createBranches();
        
        add("Center", canvas);

        scene.addBranchGraph(rootGroup);
    }
    
}
