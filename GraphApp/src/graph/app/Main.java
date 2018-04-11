package graph.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
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
import java.awt.Dimension;

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

        JMenu mnNewMenu = new JMenu("Nowy");
        mnNewMenu.setVerticalAlignment(SwingConstants.TOP);
        mnNewMenu.setBorderPainted(true);
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu);

        JButton btnVertex = new JButton("Wierzchołek");
        btnVertex.setPreferredSize(new Dimension(144, 23));
        btnVertex.setMinimumSize(new Dimension(144, 23));
        btnVertex.setMaximumSize(new Dimension(144, 23));
        btnVertex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                currently = Now.VERTEX;
            }
        });
        mnNewMenu.add(btnVertex);

        JButton btnEdge = new JButton("Krawędź");
        btnEdge.setPreferredSize(new Dimension(144, 23));
        btnEdge.setMinimumSize(new Dimension(144, 23));
        btnEdge.setMaximumSize(new Dimension(144, 23));
        btnEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Main.GRAPH ==Type.UNDEFINED||Main.GRAPH==Type.SIMPLE) {
                	currently = Now.EDGE;
                }
                else {
                	JOptionPane.showMessageDialog(Main.graphPanel,"To nie jest graf prosty");
                }
            }
        });
        mnNewMenu.add(btnEdge);

        JButton btnWeightedEdge = new JButton("Krawędź ważona");
        btnWeightedEdge.setPreferredSize(new Dimension(144, 23));
        btnWeightedEdge.setMinimumSize(new Dimension(144, 23));
        btnWeightedEdge.setMaximumSize(new Dimension(144, 23));
        btnWeightedEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(Main.GRAPH ==Type.UNDEFINED||Main.GRAPH==Type.SIMPLEWEIGHT) {
                	currently = Now.SIMPLEWEIGHT;
                }
                else {
                	JOptionPane.showMessageDialog(Main.graphPanel,"To nie jest graf prosty z wagami");
                }
            }
        });
        mnNewMenu.add(btnWeightedEdge);

        JMenu mnAlgorithms = new JMenu("Algorytmy");
        mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnAlgorithms);

        JButton btnDfs = new JButton("Algorytm DFS");
        btnDfs.setPreferredSize(new Dimension(133, 23));
        btnDfs.setMinimumSize(new Dimension(133, 23));
        btnDfs.setMaximumSize(new Dimension(133, 23));
        btnDfs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                currently = Now.DFS;
            }
        });
        mnAlgorithms.add(btnDfs);

        JButton btnDeepSearch = new JButton("Deep search");
        btnDeepSearch.setPreferredSize(new Dimension(133, 23));
        btnDeepSearch.setMinimumSize(new Dimension(133, 23));
        btnDeepSearch.setMaximumSize(new Dimension(133, 23));
        btnDeepSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.DEEPSEARCH;
            }
        });
        mnAlgorithms.add(btnDeepSearch);

        JButton btnBfs = new JButton("Algorytm BFS");
        btnBfs.setPreferredSize(new Dimension(133, 23));
        btnBfs.setMinimumSize(new Dimension(133, 23));
        btnBfs.setMaximumSize(new Dimension(133, 23));
        btnBfs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.BFS;
            }
        });
        mnAlgorithms.add(btnBfs);

        JButton btnBreadthSearch = new JButton("Breadth search");
        btnBreadthSearch.setPreferredSize(new Dimension(133, 23));
        btnBreadthSearch.setMinimumSize(new Dimension(133, 23));
        btnBreadthSearch.setMaximumSize(new Dimension(133, 23));
        btnBreadthSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.BREADTHSEARCH;
            }
        });
        mnAlgorithms.add(btnBreadthSearch);

        JButton btnKraskal = new JButton("Algorytm Kruskala");
        btnKraskal.setPreferredSize(new Dimension(133, 23));
        btnKraskal.setMinimumSize(new Dimension(133, 23));
        btnKraskal.setMaximumSize(new Dimension(133, 23));
        btnKraskal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.KRASKAL;
            }
        });
        mnAlgorithms.add(btnKraskal);

        JButton btnPrim = new JButton("Algorytm Prima");
        btnPrim.setMinimumSize(new Dimension(133, 23));
        btnPrim.setMaximumSize(new Dimension(133, 23));
        btnPrim.setPreferredSize(new Dimension(133, 23));
        btnKraskal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.PRIM;
            }
        });
        mnAlgorithms.add(btnPrim);

        JMenu mnRemove = new JMenu("Zmień");
        mnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnRemove);

        JButton btnVertex_1 = new JButton("Usuń wierzchołek");
        btnVertex_1.setPreferredSize(new Dimension(133, 23));
        btnVertex_1.setMinimumSize(new Dimension(133, 23));
        btnVertex_1.setMaximumSize(new Dimension(133, 23));
        btnVertex_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.VERTEXREMOVE;
            }
        });

        JButton btnRefresh = new JButton("Wyczyść");
        btnRefresh.setMinimumSize(new Dimension(133, 23));
        btnRefresh.setMaximumSize(new Dimension(133, 23));
        btnRefresh.setPreferredSize(new Dimension(133, 23));
        mnRemove.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.refresh();
            }
        });

        JButton btnClear = new JButton("Odśwież");
        btnClear.setPreferredSize(new Dimension(133, 23));
        btnClear.setMinimumSize(new Dimension(133, 23));
        btnClear.setMaximumSize(new Dimension(133, 23));
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.clear();
            }
        });
        mnRemove.add(btnClear);
        mnRemove.add(btnVertex_1);

        JButton btnEdge_1 = new JButton("Usuń krawędź");
        btnEdge_1.setMaximumSize(new Dimension(133, 23));
        btnEdge_1.setMinimumSize(new Dimension(133, 23));
        btnEdge_1.setPreferredSize(new Dimension(133, 23));
        btnEdge_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.EDGEREMOVE;
            }
        });
        mnRemove.add(btnEdge_1);

        JMenu mnNewMenu_1 = new JMenu("Elementy grafu");
        mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_1);

        JButton btnCenter = new JButton("Centrum");
        btnCenter.setMinimumSize(new Dimension(122, 23));
        btnCenter.setMaximumSize(new Dimension(122, 23));
        btnCenter.setPreferredSize(new Dimension(122, 23));
        btnCenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.G.graphCenter(graphPanel);
            }
        });
        mnNewMenu_1.add(btnCenter);

        JButton btnPeryfery = new JButton("Peryferium");
        btnPeryfery.setPreferredSize(new Dimension(122, 23));
        btnPeryfery.setMinimumSize(new Dimension(122, 23));
        btnPeryfery.setMaximumSize(new Dimension(122, 23));
        btnPeryfery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.G.graphPeryfery(graphPanel);
            }
        });
        mnNewMenu_1.add(btnPeryfery);

        JButton btnShortestPatch = new JButton("Najkr\u00F3tsza \u015Bcie\u017Cka");
        btnShortestPatch.setMinimumSize(new Dimension(122, 23));
        btnShortestPatch.setMaximumSize(new Dimension(122, 23));
        btnShortestPatch.setPreferredSize(new Dimension(122, 23));
        btnShortestPatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.PATCH;
            }
        });
        mnNewMenu_1.add(btnShortestPatch);
        
        JMenu mnInformacje = new JMenu("Informacje");
        mnInformacje.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnInformacje);
        
        JButton btnSpjny = new JButton("Sp\u00F3jno\u015B\u0107");
        btnSpjny.setMinimumSize(new Dimension(88, 23));
        btnSpjny.setMaximumSize(new Dimension(88, 23));
        btnSpjny.setPreferredSize(new Dimension(88, 23));
        btnSpjny.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String s="Nie jest sp�jny";
        		if(Main.graphPanel.G.Connected(Main.graphPanel.G)) {
        			s="Jest sp�jny";
        		}
        		JOptionPane.showMessageDialog(Main.graphPanel,s);
        	}
        });
        mnInformacje.add(btnSpjny);
        
        JButton btnPoczenie = new JButton("Po\u0142\u0105czenie");
        btnPoczenie.setMinimumSize(new Dimension(88, 23));
        btnPoczenie.setMaximumSize(new Dimension(88, 23));
        mnInformacje.add(btnPoczenie);

        JMenu mnSettings = new JMenu("Ustawienia");
        mnSettings.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnSettings);

        JLabel lblNewLabel = new JLabel("Szybkość animacji");
        mnSettings.add(lblNewLabel);

        JSpinner spinner = new JSpinner();
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {

            }
        });
        spinner.setModel(new SpinnerNumberModel(500, 0, 1000000, 10));
        mnSettings.add(spinner);

        JButton btnApplay = new JButton("Zastosuj");
        btnApplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int t = (Integer) spinner.getValue();
                Graph.t = t;
            }
        });
        mnSettings.add(btnApplay);
    }
}
