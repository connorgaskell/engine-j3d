package editor;

import engine.core.Engine;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Editor extends JPanel {
    
    public Editor() {
        super(new GridLayout(1, 1));
        
        JTabbedPane mainPane = new JTabbedPane();
        JComponent gameTab = new Engine();
        
        mainPane.addTab("Scene", null, tempPanel("The scene editor is currently disabled..."), "");
        mainPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        mainPane.addTab("Game", null, gameTab, "");
        mainPane.setMnemonicAt(0, KeyEvent.VK_2);
        
        JTabbedPane leftPane = new JTabbedPane();
        leftPane.addTab("Hierarchy", null, tempPanel("Scene Objects..."), "");
        leftPane.setMnemonicAt(0, KeyEvent.VK_1);
        leftPane.setSize(200, 500);
        
        add(leftPane);
        add(mainPane);
        
        leftPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        mainPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    protected JComponent tempPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
}
