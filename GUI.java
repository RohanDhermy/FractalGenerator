/**
 * The GUI class sets up the widgets that the user will use to select the depth, 
 * ratio, and colors before they click the draw button to draw the fractal. 
 * @author Rohan Dhermy
 * @version (06/05/17)
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JComboBox; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JColorChooser;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JLabel;  


public class GUI extends JFrame implements Observer{
    private Toolkit toolkit;
    private int depth; 
    private int ratio; 
    private Color cactusColor; 
    private Color pearColor; 
    private FractalGenerator fractalGenerator; 
    private ArrayList<FractalPart> fractalParts; 
    private GPanel graphicsPanel; 
    private ArrayList<GPanel> gPanelList; 
    private JColorChooser cactusChoice;
    private JColorChooser pearChoice;
    
    /**
     * Constructor of GUI class that creates the JFrame, JPanel, and the widgets 
     * need for data selection. It communicates with the FractalGenerator class
     * to set the data needed for us to create and paint the Fratal 
     * @param fractalGen 
     */
    public GUI(FractalGenerator fractalGen){
    this.fractalParts = new ArrayList<>();     
    fractalGenerator = fractalGen;  
    fractalGenerator.registerObserver(this);
    cactusColor = new Color(30, 150, 50); 
    pearColor = new Color(200, 0, 200);
    cactusChoice = new JColorChooser(cactusColor);
    pearChoice = new JColorChooser(pearColor);
    depth = 2; 
    ratio = 40; 
    
    setTitle("Prickly Pear Cactus");
        setSize(800, 600);
        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width - getWidth())/2, (size.height - getHeight())/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        //JPanel to put widets on 
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);
        //JPanel to draw fractal on 
        this.graphicsPanel = new GPanel(); 
        this.graphicsPanel.setLocation(0, 50);
        this.graphicsPanel.setSize(800, 550);
        this.graphicsPanel.setVisible(true);
        panel.add(graphicsPanel);
        //Label for depth 
        JLabel depthLabel = new JLabel("Depth:");
        
        Integer[] depthRange = new Integer[9];
        int depthValue = 2; 
        for(int idx = 0; depthValue <= 10; idx++){
            depthRange[idx] = depthValue; 
            depthValue++; 
        }
        //ComboBox button to select depth
        JComboBox<Integer> depthButton = new JComboBox<>(depthRange);
        Dimension depthBSize = depthButton.getPreferredSize(); 
        depthButton.setMinimumSize(depthBSize);
        depthButton.setMaximumSize(depthBSize);
        depthButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    depth = (int)depthButton.getSelectedItem(); 
                }
        });
        JLabel ratioLabel = new JLabel("Ratio:");
        Integer[] ratioRange = new Integer[7];
        int ratioValue = 40; 
        for(int idx = 0; ratioValue <= 70; idx++){
            ratioRange[idx] = ratioValue; 
            ratioValue+=5; 
        }
        //ComboBox button to select the ratio 
        JComboBox<Integer> ratioButton = new JComboBox<>(ratioRange);
        Dimension ratioBSize = ratioButton.getPreferredSize(); 
        ratioButton.setMinimumSize(ratioBSize);
        ratioButton.setMaximumSize(ratioBSize);
        ratioButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    ratio = (int)ratioButton.getSelectedItem();
                }
        });
        
        JLabel cactusColorLabel = new JLabel("    ");
        cactusColorLabel.setOpaque(true);
        cactusColorLabel.setBackground(cactusColor);
        //Button to select the cactus color 
        JButton cactusColorButton = new JButton("Cactus Color");
        Dimension cactusBSize = cactusColorButton.getPreferredSize(); 
        cactusColorButton.setMinimumSize(cactusBSize);
        cactusColorButton.setMaximumSize(cactusBSize);
        cactusColorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    cactusColor = cactusChoice.showDialog(panel, "Choose Color",
                            cactusColor);
                    cactusColorLabel.setBackground(cactusColor);
                }
        });
        
        JLabel pearColorLabel = new JLabel("    ");
        pearColorLabel.setOpaque(true);
        pearColorLabel.setBackground(pearColor);
        //Button to select the pear color 
        JButton pearColorButton = new JButton("Pear Color");
        Dimension pearBSize = pearColorButton.getPreferredSize(); 
        pearColorButton.setMinimumSize(pearBSize);
        pearColorButton.setMaximumSize(pearBSize);
        pearColorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    pearColor = pearChoice.showDialog(panel, "Choose Color",
                            pearColor);
                    pearColorLabel.setBackground(pearColor);
                }
        });
        //Draw button the calls setData for the FractalGenerator create the 
        //fractal pieces 
        JButton drawButton = new JButton("Draw");
        Dimension drawBSize = drawButton.getPreferredSize(); 
        drawButton.setMinimumSize(drawBSize);
        drawButton.setMaximumSize(drawBSize);
        drawButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent event) {
                    fractalGenerator.setData(depth, ratio, cactusColor, pearColor);
                }

        });
        
        //Widgets are put neatly in a toobar at the top 
        JToolBar toolbar = new JToolBar();
        panel.add(toolbar, BorderLayout.NORTH);
        toolbar.setSize(800,25);
        toolbar.setVisible(true);
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        //Widgets 
        toolbar.add(depthLabel);
        toolbar.add(depthButton);
        toolbar.addSeparator();
        toolbar.add(ratioLabel);
        toolbar.add(ratioButton);
        toolbar.addSeparator();
        toolbar.add(cactusColorButton);
        toolbar.add(cactusColorLabel);
        toolbar.addSeparator();
        toolbar.add(pearColorButton); 
        toolbar.add(pearColorLabel);
        toolbar.addSeparator();
        toolbar.add(drawButton);
       
    }
    /**
     * Update method is called by the Observer (this implements the Observer 
     * interface). FractalGenerator calls this and repaints the JPanel using the 
     * paintComponent method bellow 
     */
    @Override
    public void update() {
        this.fractalParts = fractalGenerator.getData(); 
            this.graphicsPanel.repaint();
    }
    
    /**
     * GPanel lets us put another JPanel above our existing JPanel for drawing
     */
    private class GPanel extends JPanel{
    @Override
        /**
         * Is called by java whenever update() is called to repaint the JPanel 
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);  
            for(int idx = 0; idx < fractalParts.size(); idx++){
                fractalParts.get(idx).draw(g);
            }
        }
        
    }
}




