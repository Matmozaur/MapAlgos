package view;

import controller.mouse.Clicker;
import controller.mouse.MouseMover;
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
    private final Settings settings=new Settings();

    public Settings getSettings() {
        return settings;
    }

    private JFrame frame;
    private GraphPanel graphPanel;
    private JTextArea info;

    public JFrame getFrame() {
        return frame;
    }



    public GraphApp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 657, 468);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        graphPanel = new GraphPanel(this);
        graphPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        graphPanel.setBackground(UIManager.getColor("info"));
        frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        graphPanel.addMouseListener(new Clicker(graphPanel));
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

        JMenu mnNewMenu = new JMenu("New");
        mnNewMenu.setForeground(new Color(0, 200, 0));
        mnNewMenu.setVerticalAlignment(SwingConstants.TOP);
        mnNewMenu.setBorderPainted(true);
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mnNewMenu.setBackground(Color.pink);
        menuBar.add(mnNewMenu);

        JButton btnVertex = new JButton("Vertex");
        btnVertex.setForeground(new Color(0, 200, 0));
        btnVertex.setPreferredSize(new Dimension(144, 23));
        btnVertex.setMinimumSize(new Dimension(144, 23));
        btnVertex.setMaximumSize(new Dimension(144, 23));
        btnVertex.setBackground(Color.DARK_GRAY);
        btnVertex.addActionListener(arg0 -> settings.currently = CurrentAction.VERTEX);
        mnNewMenu.add(btnVertex);

        JButton btnEdge = new JButton("Edge");
        btnEdge.setForeground(new Color(0, 200, 0));
        btnEdge.setPreferredSize(new Dimension(144, 23));
        btnEdge.setMinimumSize(new Dimension(144, 23));
        btnEdge.setMaximumSize(new Dimension(144, 23));
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
        btnWeightedEdge.setPreferredSize(new Dimension(144, 23));
        btnWeightedEdge.setMinimumSize(new Dimension(144, 23));
        btnWeightedEdge.setMaximumSize(new Dimension(144, 23));
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
        btnDfs.setPreferredSize(new Dimension(133, 23));
        btnDfs.setMinimumSize(new Dimension(133, 23));
        btnDfs.setMaximumSize(new Dimension(133, 23));
        btnDfs.setBackground(Color.DARK_GRAY);
        btnDfs.addActionListener(arg0 -> settings.currently = CurrentAction.DFS);
        mnAlgorithms.add(btnDfs);

        JButton btnDeepSearch = new JButton("Deep search");
        btnDeepSearch.setForeground(new Color(0, 200, 0));
        btnDeepSearch.setPreferredSize(new Dimension(133, 23));
        btnDeepSearch.setMinimumSize(new Dimension(133, 23));
        btnDeepSearch.setMaximumSize(new Dimension(133, 23));
        btnDeepSearch.setBackground(Color.DARK_GRAY);
        btnDeepSearch.addActionListener(arg0 -> settings.currently = CurrentAction.DEEPSEARCH);
        mnAlgorithms.add(btnDeepSearch);

        JButton btnBfs = new JButton("BFS");
        btnBfs.setForeground(new Color(0, 200, 0));
        btnBfs.setPreferredSize(new Dimension(133, 23));
        btnBfs.setMinimumSize(new Dimension(133, 23));
        btnBfs.setMaximumSize(new Dimension(133, 23));
        btnBfs.setBackground(Color.DARK_GRAY);
        btnBfs.addActionListener(arg0 -> settings.currently = CurrentAction.BFS);
        mnAlgorithms.add(btnBfs);

        JButton btnBreadthSearch = new JButton("Breadth search");
        btnBreadthSearch.setForeground(new Color(0, 200, 0));
        btnBreadthSearch.setPreferredSize(new Dimension(133, 23));
        btnBreadthSearch.setMinimumSize(new Dimension(133, 23));
        btnBreadthSearch.setMaximumSize(new Dimension(133, 23));
        btnBreadthSearch.setBackground(Color.DARK_GRAY);
        btnBreadthSearch.addActionListener(e -> settings.currently = CurrentAction.BREADTHSEARCH);
        mnAlgorithms.add(btnBreadthSearch);

        JButton btnKraskal = new JButton("Kruskal");
        btnKraskal.setForeground(new Color(0, 200, 0));
        btnKraskal.setPreferredSize(new Dimension(133, 23));
        btnKraskal.setMinimumSize(new Dimension(133, 23));
        btnKraskal.setMaximumSize(new Dimension(133, 23));
        btnKraskal.setBackground(Color.DARK_GRAY);
        btnKraskal.addActionListener(arg0 -> settings.currently = CurrentAction.KRASKAL);
        mnAlgorithms.add(btnKraskal);

        JButton btnPrim = new JButton("Prim");
        btnPrim.addActionListener(arg0 -> settings.currently=CurrentAction.PRIM);
        btnPrim.setForeground(new Color(0, 200, 0));
        btnPrim.setMinimumSize(new Dimension(133, 23));
        btnPrim.setMaximumSize(new Dimension(133, 23));
        btnPrim.setPreferredSize(new Dimension(133, 23));
        btnPrim.setBackground(Color.DARK_GRAY);
        btnKraskal.addActionListener(arg0 -> settings.currently = CurrentAction.PRIM);
        mnAlgorithms.add(btnPrim);

        JButton btnDijkstra = new JButton("Dijkstra");
        btnDijkstra.addActionListener(arg0 -> settings.currently = CurrentAction.DIJKSTRA);
        mnAlgorithms.add(btnDijkstra);
        btnDijkstra.setForeground(new Color(0, 200, 0));
        btnDijkstra.setMinimumSize(new Dimension(133, 23));
        btnDijkstra.setMaximumSize(new Dimension(133, 23));
        btnDijkstra.setPreferredSize(new Dimension(133, 23));
        btnDijkstra.setBackground(Color.DARK_GRAY);


        JMenu mnRemove = new JMenu("Change");
        mnRemove.setForeground(new Color(0, 200, 0));
        mnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnRemove);

        JButton btnVertex_1 = new JButton("Remove vertex");
        btnVertex_1.setForeground(new Color(0, 200, 0));
        btnVertex_1.setPreferredSize(new Dimension(133, 23));
        btnVertex_1.setMinimumSize(new Dimension(133, 23));
        btnVertex_1.setMaximumSize(new Dimension(133, 23));
        btnVertex_1.setBackground(Color.DARK_GRAY);
        btnVertex_1.addActionListener(arg0 -> settings.currently = CurrentAction.VERTEXREMOVE);

        JButton btnRefresh = new JButton("Refresh (delete all)");
        btnRefresh.setForeground(new Color(0, 200, 0));
        btnRefresh.setMinimumSize(new Dimension(133, 23));
        btnRefresh.setMaximumSize(new Dimension(133, 23));
        btnRefresh.setPreferredSize(new Dimension(133, 23));
        btnRefresh.setBackground(Color.DARK_GRAY);
        mnRemove.add(btnRefresh);
        btnRefresh.addActionListener(arg0 -> graphPanel.refresh());

        JButton btnClear = new JButton("Clear");
        btnClear.setForeground(new Color(0, 200, 0));
        btnClear.setPreferredSize(new Dimension(133, 23));
        btnClear.setMinimumSize(new Dimension(133, 23));
        btnClear.setMaximumSize(new Dimension(133, 23));
        btnClear.setBackground(Color.DARK_GRAY);
        btnClear.addActionListener(arg0 -> graphPanel.clear());
        mnRemove.add(btnClear);
        mnRemove.add(btnVertex_1);

        JButton btnEdge_1 = new JButton("Remove \n edge");
        btnEdge_1.setForeground(new Color(0, 200, 0));
        btnEdge_1.setMaximumSize(new Dimension(133, 23));
        btnEdge_1.setMinimumSize(new Dimension(133, 23));
        btnEdge_1.setPreferredSize(new Dimension(133, 23));
        btnEdge_1.setBackground(Color.DARK_GRAY);
        btnEdge_1.addActionListener(e -> settings.currently = CurrentAction.EDGEREMOVE);
        mnRemove.add(btnEdge_1);

        JButton btnLabel = new JButton("Change \n Label");
        btnLabel.setForeground(new Color(0, 200, 0));
        btnLabel.setMaximumSize(new Dimension(133, 23));
        btnLabel.setMinimumSize(new Dimension(133, 23));
        btnLabel.setPreferredSize(new Dimension(133, 23));
        btnLabel.setBackground(Color.DARK_GRAY);
        btnLabel.addActionListener(e -> settings.currently = CurrentAction.CHANGELABEL);
        mnRemove.add(btnLabel);

        JMenu myMenu = new JMenu("Graphs elements");
        myMenu.setForeground(new Color(0, 200, 0));
        myMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(myMenu);

        JButton btnCenter = new JButton("Center");
        btnCenter.setForeground(new Color(0, 200, 0));
        btnCenter.setMinimumSize(new Dimension(122, 23));
        btnCenter.setMaximumSize(new Dimension(122, 23));
        btnCenter.setPreferredSize(new Dimension(122, 23));
        btnCenter.setBackground(Color.DARK_GRAY);
        btnCenter.addActionListener(arg0 -> {
            //graphPanel.G.graphCenter(graphPanel);
        });
        myMenu.add(btnCenter);

        JButton btnPeryfery = new JButton("Peryfery");
        btnPeryfery.setForeground(new Color(0, 200, 0));
        btnPeryfery.setPreferredSize(new Dimension(122, 23));
        btnPeryfery.setMinimumSize(new Dimension(122, 23));
        btnPeryfery.setMaximumSize(new Dimension(122, 23));
        btnPeryfery.setBackground(Color.DARK_GRAY);
        btnPeryfery.addActionListener(arg0 -> {
            //graphPanel.G.graphPeryfery(graphPanel);
        });
        myMenu.add(btnPeryfery);

        JButton btnShortestPatch = new JButton("Shortest path");
        btnShortestPatch.setForeground(new Color(0, 200, 0));
        btnShortestPatch.setMinimumSize(new Dimension(122, 23));
        btnShortestPatch.setMaximumSize(new Dimension(122, 23));
        btnShortestPatch.setPreferredSize(new Dimension(122, 23));
        btnShortestPatch.setBackground(Color.DARK_GRAY);
        btnShortestPatch.addActionListener(e -> settings.currently = CurrentAction.PATCH);
        myMenu.add(btnShortestPatch);

        JMenu mnInformacje = new JMenu("Informations");
        mnInformacje.setForeground(new Color(0, 200, 0));
        mnInformacje.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnInformacje);

        JButton btnSpjny = new JButton("Connectivity");
        btnSpjny.setForeground(new Color(0, 200, 0));
        btnSpjny.setMinimumSize(new Dimension(88, 23));
        btnSpjny.setMaximumSize(new Dimension(88, 23));
        btnSpjny.setPreferredSize(new Dimension(88, 23));
        btnSpjny.setBackground(Color.DARK_GRAY);
        btnSpjny.addActionListener(arg0 -> {
//                String s="Is not connected";
//                if(graphPanel.getG().getLogic().connected()){
//                    s="Is connected";
//                }
//                JOptionPane.showMessageDialog(graphPanel,s);
        });
        mnInformacje.add(btnSpjny);

        JButton btnStopieWierzchoka = new JButton("Vertexes degree");
        btnStopieWierzchoka.setForeground(new Color(0, 200, 0));
        btnStopieWierzchoka.addActionListener(arg0 -> settings.currently=CurrentAction.DEGREE);
        btnStopieWierzchoka.setPreferredSize(new Dimension(88, 23));
        btnStopieWierzchoka.setMinimumSize(new Dimension(88, 23));
        btnStopieWierzchoka.setMaximumSize(new Dimension(88, 23));
        btnStopieWierzchoka.setBackground(Color.DARK_GRAY);
        mnInformacje.add(btnStopieWierzchoka);

        JButton btnEkscentrycznocWierzchoka = new JButton("Vertexes eccenticity");
        btnEkscentrycznocWierzchoka.setForeground(new Color(0, 200, 0));
        btnEkscentrycznocWierzchoka.addActionListener(e -> settings.currently=CurrentAction.ECCENTRICY);
        btnEkscentrycznocWierzchoka.setMaximumSize(new Dimension(88, 23));
        btnEkscentrycznocWierzchoka.setMinimumSize(new Dimension(88, 23));
        btnEkscentrycznocWierzchoka.setPreferredSize(new Dimension(88, 23));
        btnEkscentrycznocWierzchoka.setBackground(Color.DARK_GRAY);
        mnInformacje.add(btnEkscentrycznocWierzchoka);

        JButton btnAll = new JButton("All");
        btnAll.addActionListener(arg0 -> info.setText("Info: \n"+graphPanel.allInfo()));
        btnAll.setBackground(new Color(0, 0, 0));
        btnAll.setForeground(new Color(50, 205, 50));
        btnAll.setMaximumSize(new Dimension(88, 23));
        btnAll.setMinimumSize(new Dimension(88, 23));
        btnAll.setPreferredSize(new Dimension(88, 23));
        mnInformacje.add(btnAll);

        JMenu mnSettings = new JMenu("Settings");
        mnSettings.setDoubleBuffered(true);
        mnSettings.setFocusCycleRoot(true);
        mnSettings.setFocusPainted(true);
        mnSettings.setFocusTraversalPolicyProvider(true);
        mnSettings.setForeground(new Color(0, 200, 0));
        mnSettings.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnSettings);
        mnSettings.setBackground(Color.DARK_GRAY);

        JLabel lblNewLabel = new JLabel("Animations speed");
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
