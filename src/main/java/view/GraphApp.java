package view;

import controller.Main;
import controller.mouse.Clicker;
import controller.mouse.MouseMover;
import model.graphs.representation.WeightGraph;
import model.settings.CurrentAction;
import model.settings.Settings;
import model.settings.Type;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.*;


public class GraphApp {
    private Settings settings=new Settings();
    private JFrame frame;
    private GraphPanel graphPanel;
    private JTextArea info;
    private Clicker clicker;

    public JFrame getFrame() {
        return frame;
    }
    public Settings getSettings() {
        return settings;
    }
    public JTextArea getInfo() {
        return info;
    }

    public GraphApp() {
        initialize();
    }
    public GraphApp(WeightGraph G) {
        initialize();
        G.setPanel(this.graphPanel);
        this.graphPanel.setG(G);
        if(!G.getVertexes().isEmpty()){
            this.settings=G.getVertexes().get(0).getSettings();
            this.graphPanel.setSettings(G.getVertexes().get(0).getSettings());
            this.graphPanel.setCounter(G.getVertexes().size());
            for(int i=0;i<graphPanel.getMouseListeners().length;i++){
                graphPanel.removeMouseListener(graphPanel.getMouseListeners()[i]);
            }
            clicker=new Clicker(graphPanel);
            graphPanel.addMouseListener(clicker);
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 657, 520);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.setTitle("Map manager");

        graphPanel = new GraphPanel(this);
        graphPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        graphPanel.setBackground(UIManager.getColor("info"));
        frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        clicker=new Clicker(graphPanel);
        graphPanel.addMouseListener(clicker);
        graphPanel.addMouseMotionListener(new MouseMover(graphPanel));
        graphPanel.setBackground(Color.GRAY);

        info = new JTextArea();
        info.setMaximumSize(new Dimension(2147483647, 400));
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setMargin(new Insets(10, 10, 10, 10));
        info.setCaretColor(Color.GREEN);
        info.setLocation(new Point(2, 2));
        info.setFont(new Font("Monospaced", Font.BOLD, 15));
        info.setBounds(new Rectangle(2, 2, 2, 2));
        info.setTabSize(11);
        info.setText("Info:");
        info.setDisabledTextColor(new Color(109, 109, 109));
        info.setForeground(Color.GREEN);
        info.setBackground(new Color(0, 0, 0));
        info.setPreferredSize(new Dimension(150, 400));
        info.setMinimumSize(new Dimension(150, 400));
        frame.getContentPane().add(info, BorderLayout.WEST);



        JMenuBar menuBar = new JMenuBar();
        menuBar.setAutoscrolls(true);
        menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.setAlignmentX(0.0f);
        frame.setJMenuBar(menuBar);
        menuBar.setBackground(Color.DARK_GRAY);



        JMenu mnFile = new JMenu("File");
        mnFile.setForeground(new Color(0, 200, 0));
        mnFile.setVerticalAlignment(SwingConstants.TOP);
        mnFile.setBorderPainted(true);
        mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mnFile.setBackground(Color.pink);
        menuBar.add(mnFile);


        JButton btnSave = new JButton("Save");
        btnSave.setForeground(new Color(0, 200, 0));
        btnSave.setPreferredSize(new Dimension(140, 23));
        btnSave.setMinimumSize(new Dimension(140, 23));
        btnSave.setMaximumSize(new Dimension(140, 23));
        btnSave.setBackground(Color.DARK_GRAY);
        btnSave.addActionListener(arg0 -> Main.getFC().getFW().saveAs(graphPanel.getG(),this.frame));
        mnFile.add(btnSave);

        JButton btnSaveAs = new JButton("Save As");
        btnSaveAs.setForeground(new Color(0, 200, 0));
        btnSaveAs.setPreferredSize(new Dimension(140, 23));
        btnSaveAs.setMinimumSize(new Dimension(140, 23));
        btnSaveAs.setMaximumSize(new Dimension(140, 23));
        btnSaveAs.setBackground(Color.DARK_GRAY);
        btnSaveAs.addActionListener(arg0 -> Main.getFC().getFW().saveAs(graphPanel.getG(),this.frame));
        mnFile.add(btnSaveAs);

        JButton btnOpen = new JButton("Open");
        btnOpen.setForeground(new Color(0, 200, 0));
        btnOpen.setPreferredSize(new Dimension(140, 23));
        btnOpen.setMinimumSize(new Dimension(140, 23));
        btnOpen.setMaximumSize(new Dimension(140, 23));
        btnOpen.setBackground(Color.DARK_GRAY);
        btnOpen.addActionListener(actionEvent ->{
            WeightGraph G=Main.getFC().getFR().getGraph(this.getFrame());
            if(G!=null){
                Main.loadManager(G);
            }
        });
        mnFile.add(btnOpen);



        JMenu mnNewMenu = new JMenu("New");
        mnNewMenu.setForeground(new Color(0, 200, 0));
        mnNewMenu.setVerticalAlignment(SwingConstants.TOP);
        mnNewMenu.setBorderPainted(true);
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mnNewMenu.setBackground(Color.pink);
        menuBar.add(mnNewMenu);


        JButton btnVertex = new JButton("Vertex");
        btnVertex.setForeground(new Color(0, 200, 0));
        btnVertex.setPreferredSize(new Dimension(140, 23));
        btnVertex.setMinimumSize(new Dimension(140, 23));
        btnVertex.setMaximumSize(new Dimension(140, 23));
        btnVertex.setBackground(Color.DARK_GRAY);
        btnVertex.addActionListener(arg0 -> settings.currently = CurrentAction.VERTEX);
        mnNewMenu.add(btnVertex);

        JButton btnEdge = new JButton("Edge");
        btnEdge.setForeground(new Color(0, 200, 0));
        btnEdge.setPreferredSize(new Dimension(140, 23));
        btnEdge.setMinimumSize(new Dimension(140, 23));
        btnEdge.setMaximumSize(new Dimension(140, 23));
        btnEdge.setBackground(Color.DARK_GRAY);
        btnEdge.addActionListener(e -> {
            if(settings.GraphType == Type.UNDEFINED|| settings.GraphType==Type.SIMPLE) {
                settings.currently = CurrentAction.EDGE;
            }
            else {
                JOptionPane.showMessageDialog(graphPanel,"This is not a simple graph");
            }
        });
        mnNewMenu.add(btnEdge);

        JButton btnWeightedEdge = new JButton("Weight edge");
        btnWeightedEdge.setForeground(new Color(0, 200, 0));
        btnWeightedEdge.setPreferredSize(new Dimension(140, 23));
        btnWeightedEdge.setMinimumSize(new Dimension(140, 23));
        btnWeightedEdge.setMaximumSize(new Dimension(140, 23));
        btnWeightedEdge.setBackground(Color.DARK_GRAY);
        btnWeightedEdge.addActionListener(arg0 -> {
            if(settings.GraphType ==Type.UNDEFINED|| settings.GraphType==Type.SIMPLEWEIGHT) {
                settings.currently = CurrentAction.SIMPLEWEIGHT;
            }
            else {
                JOptionPane.showMessageDialog(graphPanel,"This is not simple weight graph");
            }
        });
        mnNewMenu.add(btnWeightedEdge);



        JMenu mnAlgorithms = new JMenu("Algorithms");
        mnAlgorithms.setForeground(new Color(0, 200, 0));
        mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnAlgorithms);

        JButton btnDfs = new JButton("DFS");
        btnDfs.setForeground(new Color(0, 200, 0));
        btnDfs.setPreferredSize(new Dimension(140, 23));
        btnDfs.setMinimumSize(new Dimension(140, 23));
        btnDfs.setMaximumSize(new Dimension(140, 23));
        btnDfs.setBackground(Color.DARK_GRAY);
        btnDfs.addActionListener(arg0 -> settings.currently = CurrentAction.DFS);
        mnAlgorithms.add(btnDfs);

        JButton btnDeepSearch = new JButton("Deep search");
        btnDeepSearch.setForeground(new Color(0, 200, 0));
        btnDeepSearch.setPreferredSize(new Dimension(140, 23));
        btnDeepSearch.setMinimumSize(new Dimension(140, 23));
        btnDeepSearch.setMaximumSize(new Dimension(140, 23));
        btnDeepSearch.setBackground(Color.DARK_GRAY);
        btnDeepSearch.addActionListener(arg0 -> settings.currently = CurrentAction.DEEPSEARCH);
        mnAlgorithms.add(btnDeepSearch);

        JButton btnBfs = new JButton("BFS");
        btnBfs.setForeground(new Color(0, 200, 0));
        btnBfs.setPreferredSize(new Dimension(140, 23));
        btnBfs.setMinimumSize(new Dimension(140, 23));
        btnBfs.setMaximumSize(new Dimension(140, 23));
        btnBfs.setBackground(Color.DARK_GRAY);
        btnBfs.addActionListener(arg0 -> settings.currently = CurrentAction.BFS);
        mnAlgorithms.add(btnBfs);

        JButton btnBreadthSearch = new JButton("Breadth search");
        btnBreadthSearch.setForeground(new Color(0, 200, 0));
        btnBreadthSearch.setPreferredSize(new Dimension(140, 23));
        btnBreadthSearch.setMinimumSize(new Dimension(140, 23));
        btnBreadthSearch.setMaximumSize(new Dimension(140, 23));
        btnBreadthSearch.setBackground(Color.DARK_GRAY);
        btnBreadthSearch.addActionListener(e -> settings.currently = CurrentAction.BREADTHSEARCH);
        mnAlgorithms.add(btnBreadthSearch);

        JButton btnKraskal = new JButton("Kruskal");
        btnKraskal.setForeground(new Color(0, 200, 0));
        btnKraskal.setPreferredSize(new Dimension(140, 23));
        btnKraskal.setMinimumSize(new Dimension(140, 23));
        btnKraskal.setMaximumSize(new Dimension(140, 23));
        btnKraskal.setBackground(Color.DARK_GRAY);
        btnKraskal.addActionListener(arg0 -> settings.currently = CurrentAction.KRASKAL);
        mnAlgorithms.add(btnKraskal);

        JButton btnPrim = new JButton("Prim");
        btnPrim.addActionListener(arg0 -> settings.currently=CurrentAction.PRIM);
        btnPrim.setForeground(new Color(0, 200, 0));
        btnPrim.setMinimumSize(new Dimension(140, 23));
        btnPrim.setMaximumSize(new Dimension(140, 23));
        btnPrim.setPreferredSize(new Dimension(140, 23));
        btnPrim.setBackground(Color.DARK_GRAY);
        btnKraskal.addActionListener(arg0 -> settings.currently = CurrentAction.PRIM);
        mnAlgorithms.add(btnPrim);

        JButton btnDijkstra = new JButton("Dijkstra");
        btnDijkstra.addActionListener(arg0 -> settings.currently = CurrentAction.DIJKSTRA);
        mnAlgorithms.add(btnDijkstra);
        btnDijkstra.setForeground(new Color(0, 200, 0));
        btnDijkstra.setMinimumSize(new Dimension(140, 23));
        btnDijkstra.setMaximumSize(new Dimension(140, 23));
        btnDijkstra.setPreferredSize(new Dimension(140, 23));
        btnDijkstra.setBackground(Color.DARK_GRAY);



        JMenu mnRemove = new JMenu("Change");
        mnRemove.setForeground(new Color(0, 200, 0));
        mnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnRemove);


        JButton btnVertex_1 = new JButton("Remove vertex");
        btnVertex_1.setForeground(new Color(0, 200, 0));
        btnVertex_1.setPreferredSize(new Dimension(140, 23));
        btnVertex_1.setMinimumSize(new Dimension(140, 23));
        btnVertex_1.setMaximumSize(new Dimension(140, 23));
        btnVertex_1.setBackground(Color.DARK_GRAY);
        btnVertex_1.addActionListener(arg0 -> settings.currently = CurrentAction.VERTEXREMOVE);

        JButton btnRefresh = new JButton("Delete all");
        btnRefresh.setForeground(new Color(0, 200, 0));
        btnRefresh.setMinimumSize(new Dimension(140, 23));
        btnRefresh.setMaximumSize(new Dimension(140, 23));
        btnRefresh.setPreferredSize(new Dimension(140, 23));
        btnRefresh.setBackground(Color.DARK_GRAY);
        mnRemove.add(btnRefresh);
        btnRefresh.addActionListener(arg0 -> {
            graphPanel.refresh();
            info.setText("");
        });

        JButton btnClear = new JButton("Clear");
        btnClear.setForeground(new Color(0, 200, 0));
        btnClear.setPreferredSize(new Dimension(140, 23));
        btnClear.setMinimumSize(new Dimension(140, 23));
        btnClear.setMaximumSize(new Dimension(140, 23));
        btnClear.setBackground(Color.DARK_GRAY);
        btnClear.addActionListener(arg0 -> {
            graphPanel.clear();
            info.setText("");
        });
        mnRemove.add(btnClear);
        mnRemove.add(btnVertex_1);

        JButton btnEdge_1 = new JButton("Remove \n edge");
        btnEdge_1.setForeground(new Color(0, 200, 0));
        btnEdge_1.setMaximumSize(new Dimension(140, 23));
        btnEdge_1.setMinimumSize(new Dimension(140, 23));
        btnEdge_1.setPreferredSize(new Dimension(140, 23));
        btnEdge_1.setBackground(Color.DARK_GRAY);
        btnEdge_1.addActionListener(e -> settings.currently = CurrentAction.EDGEREMOVE);
        mnRemove.add(btnEdge_1);

        JButton btnLabel = new JButton("Change \n Label");
        btnLabel.setForeground(new Color(0, 200, 0));
        btnLabel.setMaximumSize(new Dimension(140, 23));
        btnLabel.setMinimumSize(new Dimension(140, 23));
        btnLabel.setPreferredSize(new Dimension(140, 23));
        btnLabel.setBackground(Color.DARK_GRAY);
        btnLabel.addActionListener(e -> settings.currently = CurrentAction.CHANGELABEL);
        mnRemove.add(btnLabel);



        JMenu myMenu = new JMenu("Elements");
        myMenu.setForeground(new Color(0, 200, 0));
        myMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(myMenu);


        JButton btnCenter = new JButton("Center");
        btnCenter.setForeground(new Color(0, 200, 0));
        btnCenter.setMinimumSize(new Dimension(140, 23));
        btnCenter.setMaximumSize(new Dimension(140, 23));
        btnCenter.setPreferredSize(new Dimension(140, 23));
        btnCenter.setBackground(Color.DARK_GRAY);
        btnCenter.addActionListener(arg0 -> clicker.getEC().visualizeCenter());
        myMenu.add(btnCenter);

        JButton btnPeryfery = new JButton("Peryfery");
        btnPeryfery.setForeground(new Color(0, 200, 0));
        btnPeryfery.setPreferredSize(new Dimension(140, 23));
        btnPeryfery.setMinimumSize(new Dimension(140, 23));
        btnPeryfery.setMaximumSize(new Dimension(140, 23));
        btnPeryfery.setBackground(Color.DARK_GRAY);
        btnPeryfery.addActionListener(arg0 -> clicker.getEC().visualizePeryfery());
        myMenu.add(btnPeryfery);

        JButton btnShortestPatch = new JButton("Shortest path");
        btnShortestPatch.setForeground(new Color(0, 200, 0));
        btnShortestPatch.setMinimumSize(new Dimension(140, 23));
        btnShortestPatch.setMaximumSize(new Dimension(140, 23));
        btnShortestPatch.setPreferredSize(new Dimension(140, 23));
        btnShortestPatch.setBackground(Color.DARK_GRAY);
        btnShortestPatch.addActionListener(e -> settings.currently = CurrentAction.PATH);
        myMenu.add(btnShortestPatch);

        JButton btnMinimalST = new JButton("Minimal Spanning tree");
        btnMinimalST.setForeground(new Color(0, 200, 0));
        btnMinimalST.setMinimumSize(new Dimension(140, 23));
        btnMinimalST.setMaximumSize(new Dimension(140, 23));
        btnMinimalST.setPreferredSize(new Dimension(140, 23));
        btnMinimalST.setBackground(Color.DARK_GRAY);
        btnMinimalST.addActionListener(e -> clicker.getEC().visualizeMinimalST());
        myMenu.add(btnMinimalST);

        JButton btnBestPath = new JButton("Best path");
        btnBestPath.setForeground(new Color(0, 200, 0));
        btnBestPath.setMinimumSize(new Dimension(140, 23));
        btnBestPath.setMaximumSize(new Dimension(140, 23));
        btnBestPath.setPreferredSize(new Dimension(140, 23));
        btnBestPath.setBackground(Color.DARK_GRAY);
        btnBestPath.addActionListener(e -> settings.currently = CurrentAction.BESTPATH);
        myMenu.add(btnBestPath);



        JMenu mnInfo = new JMenu("Informations");
        mnInfo.setForeground(new Color(0, 200, 0));
        mnInfo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnInfo);

        JButton btnBasicInfo = new JButton("Basic info");
        btnBasicInfo.setForeground(new Color(0, 200, 0));
        btnBasicInfo.setMinimumSize(new Dimension(140, 23));
        btnBasicInfo.setMaximumSize(new Dimension(140, 23));
        btnBasicInfo.setPreferredSize(new Dimension(140, 23));
        btnBasicInfo.setBackground(Color.DARK_GRAY);
        btnBasicInfo.addActionListener(arg0 -> info.setText("Info: \n"+graphPanel.basicInfo()));
        mnInfo.add(btnBasicInfo);

        JButton btnVertexInfo = new JButton("Vertex info");
        btnVertexInfo.setForeground(new Color(0, 200, 0));
        btnVertexInfo.addActionListener(arg0 -> settings.currently=CurrentAction.VINFO);
        btnVertexInfo.setPreferredSize(new Dimension(140, 23));
        btnVertexInfo.setMinimumSize(new Dimension(140, 23));
        btnVertexInfo.setMaximumSize(new Dimension(140, 23));
        btnVertexInfo.setBackground(Color.DARK_GRAY);
        mnInfo.add(btnVertexInfo);


        JButton btnConnectedVertexes = new JButton("Connected");
        btnConnectedVertexes.setForeground(new Color(0, 200, 0));
        btnConnectedVertexes.addActionListener(arg0 -> settings.currently=CurrentAction.CONNECTED);
        btnConnectedVertexes.setPreferredSize(new Dimension(140, 23));
        btnConnectedVertexes.setMinimumSize(new Dimension(140, 23));
        btnConnectedVertexes.setMaximumSize(new Dimension(140, 23));
        btnConnectedVertexes.setBackground(Color.DARK_GRAY);
        mnInfo.add(btnConnectedVertexes);


        JButton btnAll = new JButton("All info");
        btnAll.addActionListener(arg0 -> info.setText("Info: \n"+graphPanel.allInfo()));
        btnAll.setBackground(new Color(0, 0, 0));
        btnAll.setForeground(new Color(50, 205, 50));
        btnAll.setMaximumSize(new Dimension(140, 23));
        btnAll.setMinimumSize(new Dimension(140, 23));
        btnAll.setPreferredSize(new Dimension(140, 23));
        mnInfo.add(btnAll);



        JMenu mnSettings = new JMenu("Settings");
        mnSettings.setDoubleBuffered(true);
        mnSettings.setFocusCycleRoot(true);
        mnSettings.setFocusPainted(true);
        mnSettings.setFocusTraversalPolicyProvider(true);
        mnSettings.setForeground(new Color(0, 200, 0));
        mnSettings.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnSettings);
        mnSettings.setBackground(Color.DARK_GRAY);


        JLabel lblNewLabel = new JLabel("Animations wait time");
        lblNewLabel.setForeground(new Color(0, 200, 0));
        mnSettings.add(lblNewLabel);

        JSpinner spinner = new JSpinner();
        spinner.setForeground(new Color(0, 200, 0));
        spinner.setBackground(new Color(105, 105, 105));
        spinner.addChangeListener(arg0 -> {

        });
        spinner.setModel(new SpinnerNumberModel(500, 0, 1000000, 10));
        mnSettings.add(spinner);

        JRadioButton rdbtnVisibleLabels = new JRadioButton("Labels visible");

        JButton btnApplay = new JButton("Apply");
        btnApplay.addActionListener(e -> {
            settings.t = (Integer) spinner.getValue();
            if(rdbtnVisibleLabels.isSelected()) {
                graphPanel.showLabels();
            }
            else {
                graphPanel.hideLabels();
            }
        });
        mnSettings.add(rdbtnVisibleLabels);
        mnSettings.add(btnApplay);


    }

}
