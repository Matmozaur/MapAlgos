package graph.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Main {

    static Color vcolor = Color.BLACK;
    static Color ecolor = Color.BLACK;
    static int diam = 24;
    static Now currently = Now.VERTEX;
    static Type GRAPH = Type.UNDEFINED; 

    private JFrame frame;
    private static GraphPanel graphPanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 657, 468);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        GraphPanel graphPanel = new GraphPanel();
        Main.graphPanel = graphPanel;
        graphPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        graphPanel.setBackground(UIManager.getColor("info"));
        frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        graphPanel.addMouseListener(new Clicker(graphPanel));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setAutoscrolls(true);
        menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.setAlignmentX(0.0f);
        frame.setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("New");
        mnNewMenu.setVerticalAlignment(SwingConstants.TOP);
        mnNewMenu.setBorderPainted(true);
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu);

        JButton btnVertex = new JButton("Vertex");
        btnVertex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                currently = Now.VERTEX;
            }
        });
        mnNewMenu.add(btnVertex);

        JButton btnEdge = new JButton("Edge");
        btnEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currently = Now.EDGE;
            }
        });
        mnNewMenu.add(btnEdge);

        // newwww
        JButton btnWeightEdge = new JButton("Weighted Edge \n (directed)");
        btnWeightEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currently = Now.WEIGHTEDGE;
            }
        });
        
        JButton btnWeightedEdge = new JButton("Weighted Edge");
        btnWeightedEdge.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		currently= Now.SIMPLEWEIGHT;
        	}
        });
        mnNewMenu.add(btnWeightedEdge);
        mnNewMenu.add(btnWeightEdge);

        JMenu mnAlgorithms = new JMenu("Algorithms");
        mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnAlgorithms);

        JButton btnDfs = new JButton("DFS");
        btnDfs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                currently = Now.DFS;
            }
        });
        mnAlgorithms.add(btnDfs);

        JButton btnDeepSearch = new JButton("Deep search");
        btnDeepSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.DEEPSEARCH;
            }
        });
        mnAlgorithms.add(btnDeepSearch);

        JButton btnBfs = new JButton("BFS");
        btnBfs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.BFS;
            }
        });
        mnAlgorithms.add(btnBfs);

        JButton btnBreadthSearch = new JButton("Breadth search");
        btnBreadthSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.BREADTHSEARCH;
            }
        });
        mnAlgorithms.add(btnBreadthSearch);
        
        JButton btnKraskal = new JButton("Kraskal");
        btnKraskal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Main.currently=Now.KRASKAL;
        	}
        });
        mnAlgorithms.add(btnKraskal);
        
        JButton btnPrim = new JButton("Prim");
        btnKraskal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Main.currently=Now.PRIM;
        	}
        });
        mnAlgorithms.add(btnPrim);

        JMenu mnRemove = new JMenu("Remove");
        mnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnRemove);

        JButton btnVertex_1 = new JButton("Vertex");
        btnVertex_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.VERTEXREMOVE;
            }
        });

        JButton btnRefresh = new JButton("Refresh");
        mnRemove.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.refresh();
            }
        });

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.clear();
            }
        });
        mnRemove.add(btnClear);
        mnRemove.add(btnVertex_1);

        JButton btnEdge_1 = new JButton("Edge");
        btnEdge_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.EDGEREMOVE;
            }
        });
        mnRemove.add(btnEdge_1);

        JMenu mnNewMenu_1 = new JMenu("Graph Elements");
        mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_1);

        JButton btnCenter = new JButton("Center");
        btnCenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.G.graphCenter(graphPanel);
            }
        });
        mnNewMenu_1.add(btnCenter);

        JButton btnPeryfery = new JButton("Peryfery");
        btnPeryfery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.G.graphPeryfery(graphPanel);
            }
        });
        mnNewMenu_1.add(btnPeryfery);

        JButton btnShortestPatch = new JButton("Shortest patch");
        btnShortestPatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.PATCH;
            }
        });
        mnNewMenu_1.add(btnShortestPatch);
        
        JMenu mnSettings = new JMenu("Settings");
        mnSettings.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnSettings);
        
        JLabel lblNewLabel = new JLabel("Pause time");
        mnSettings.add(lblNewLabel);
        
        JSpinner spinner = new JSpinner();
        spinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent arg0) {
        		
        	}
        });
        spinner.setModel(new SpinnerNumberModel(500, 0, 1000000, 10));
        mnSettings.add(spinner);
        
        JButton btnApplay = new JButton("Applay");
        btnApplay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int t=(Integer)spinner.getValue();
        		Graph.t=t;
        	}
        });
        mnSettings.add(btnApplay);
    }
}
