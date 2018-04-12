package graph.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.image.ImagingOpException;
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
import java.awt.Label;
import java.awt.Toolkit;
import javax.swing.JRadioButton;

public class Main {

    static Color vcolor = Color.BLACK;
    static Color ecolor = Color.BLACK;
    static int diam = 24;
    static Now currently = Now.VERTEX;
    static Type GRAPH = Type.UNDEFINED;

    private static JFrame frame;
    private static GraphPanel graphPanel;
    private static JMenu mnNewMenu_1;

    public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		Main.frame = frame;
	}

	public  static JMenu getMnNewMenu_1() {
		return mnNewMenu_1;
	}

	public static void setMnNewMenu_1(JMenu mnNewMenu_1) {
		Main.mnNewMenu_1 = mnNewMenu_1;
	}

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
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/knmd.jpg")));
        frame.setBounds(100, 100, 657, 468);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        //frame.setBackground(Color.LIGHT_GRAY);

        GraphPanel graphPanel = new GraphPanel();
        Main.graphPanel = graphPanel;
        graphPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        graphPanel.setBackground(UIManager.getColor("info"));
        frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
        graphPanel.addMouseListener(new Clicker(graphPanel));
        graphPanel.setBackground(Color.GRAY);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setAutoscrolls(true);
        menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBar.setAlignmentX(0.0f);
        frame.setJMenuBar(menuBar);
        menuBar.setBackground(Color.DARK_GRAY);

        JMenu mnNewMenu = new JMenu("Nowy");
        mnNewMenu.setForeground(new Color(0, 200, 0));
        mnNewMenu.setVerticalAlignment(SwingConstants.TOP);
        mnNewMenu.setBorderPainted(true);
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mnNewMenu.setBackground(Color.pink);
        menuBar.add(mnNewMenu);

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JButton btnVertex = new JButton("Wierzcho\u0142ek");
        btnVertex.setForeground(new Color(0, 200, 0));
=======
        JButton btnVertex = new JButton("WierzchoÅ‚ek");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        btnVertex.setPreferredSize(new Dimension(144, 23));
        btnVertex.setMinimumSize(new Dimension(144, 23));
        btnVertex.setMaximumSize(new Dimension(144, 23));
        btnVertex.setBackground(Color.DARK_GRAY);
        btnVertex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                currently = Now.VERTEX;
            }
        });
        mnNewMenu.add(btnVertex);

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JButton btnEdge = new JButton("Kraw\u0119d\u017A");
        btnEdge.setForeground(new Color(0, 200, 0));
=======
        JButton btnEdge = new JButton("KrawÄ™dÅº");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        btnEdge.setPreferredSize(new Dimension(144, 23));
        btnEdge.setMinimumSize(new Dimension(144, 23));
        btnEdge.setMaximumSize(new Dimension(144, 23));
        btnEdge.setBackground(Color.DARK_GRAY);
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

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JButton btnWeightedEdge = new JButton("Kraw\u0119d\u017A wa\u017Cona");
        btnWeightedEdge.setForeground(new Color(0, 200, 0));
=======
        JButton btnWeightedEdge = new JButton("KrawÄ™dÅº waÅ¼ona");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        btnWeightedEdge.setPreferredSize(new Dimension(144, 23));
        btnWeightedEdge.setMinimumSize(new Dimension(144, 23));
        btnWeightedEdge.setMaximumSize(new Dimension(144, 23));
        btnWeightedEdge.setBackground(Color.DARK_GRAY);
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
        mnAlgorithms.setForeground(new Color(0, 200, 0));
        mnAlgorithms.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnAlgorithms);

        JButton btnDfs = new JButton("Algorytm DFS");
        btnDfs.setForeground(new Color(0, 200, 0));
        btnDfs.setPreferredSize(new Dimension(133, 23));
        btnDfs.setMinimumSize(new Dimension(133, 23));
        btnDfs.setMaximumSize(new Dimension(133, 23));
        btnDfs.setBackground(Color.DARK_GRAY);
        btnDfs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                currently = Now.DFS;
            }
        });
        mnAlgorithms.add(btnDfs);

        JButton btnDeepSearch = new JButton("Deep search");
        btnDeepSearch.setForeground(new Color(0, 200, 0));
        btnDeepSearch.setPreferredSize(new Dimension(133, 23));
        btnDeepSearch.setMinimumSize(new Dimension(133, 23));
        btnDeepSearch.setMaximumSize(new Dimension(133, 23));
        btnDeepSearch.setBackground(Color.DARK_GRAY);
        btnDeepSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.DEEPSEARCH;
            }
        });
        mnAlgorithms.add(btnDeepSearch);

        JButton btnBfs = new JButton("Algorytm BFS");
        btnBfs.setForeground(new Color(0, 200, 0));
        btnBfs.setPreferredSize(new Dimension(133, 23));
        btnBfs.setMinimumSize(new Dimension(133, 23));
        btnBfs.setMaximumSize(new Dimension(133, 23));
        btnBfs.setBackground(Color.DARK_GRAY);
        btnBfs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.BFS;
            }
        });
        mnAlgorithms.add(btnBfs);

        JButton btnBreadthSearch = new JButton("Breadth search");
        btnBreadthSearch.setForeground(new Color(0, 200, 0));
        btnBreadthSearch.setPreferredSize(new Dimension(133, 23));
        btnBreadthSearch.setMinimumSize(new Dimension(133, 23));
        btnBreadthSearch.setMaximumSize(new Dimension(133, 23));
        btnBreadthSearch.setBackground(Color.DARK_GRAY);
        btnBreadthSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.BREADTHSEARCH;
            }
        });
        mnAlgorithms.add(btnBreadthSearch);

        JButton btnKraskal = new JButton("Algorytm Kruskala");
        btnKraskal.setForeground(new Color(0, 200, 0));
        btnKraskal.setPreferredSize(new Dimension(133, 23));
        btnKraskal.setMinimumSize(new Dimension(133, 23));
        btnKraskal.setMaximumSize(new Dimension(133, 23));
        btnKraskal.setBackground(Color.DARK_GRAY);
        btnKraskal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.KRASKAL;
            }
        });
        mnAlgorithms.add(btnKraskal);

        JButton btnPrim = new JButton("Algorytm Prima");
        btnPrim.setForeground(new Color(0, 200, 0));
        btnPrim.setMinimumSize(new Dimension(133, 23));
        btnPrim.setMaximumSize(new Dimension(133, 23));
        btnPrim.setPreferredSize(new Dimension(133, 23));
<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        btnPrim.setBackground(Color.DARK_GRAY);
        btnKraskal.addActionListener(new ActionListener() {
=======
        btnPrim.addActionListener(new ActionListener() {
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.PRIM;
            }
        });
        mnAlgorithms.add(btnPrim);

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JMenu mnRemove = new JMenu("Zmie\u0144");
        mnRemove.setForeground(new Color(0, 200, 0));
        mnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnRemove);

        JButton btnVertex_1 = new JButton("Usu\u0144 wierzcho\u0142ek");
        btnVertex_1.setForeground(new Color(0, 200, 0));
=======
        JMenu mnRemove = new JMenu("ZmieÅ„");
        mnRemove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnRemove);

        JButton btnVertex_1 = new JButton("UsuÅ„ wierzchoÅ‚ek");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        btnVertex_1.setPreferredSize(new Dimension(133, 23));
        btnVertex_1.setMinimumSize(new Dimension(133, 23));
        btnVertex_1.setMaximumSize(new Dimension(133, 23));
        btnVertex_1.setBackground(Color.DARK_GRAY);
        btnVertex_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.currently = Now.VERTEXREMOVE;
            }
        });

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JButton btnRefresh = new JButton("Wyczy\u015B\u0107");
        btnRefresh.setForeground(new Color(0, 200, 0));
=======
        JButton btnRefresh = new JButton("WyczyÅ›Ä‡");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        btnRefresh.setMinimumSize(new Dimension(133, 23));
        btnRefresh.setMaximumSize(new Dimension(133, 23));
        btnRefresh.setPreferredSize(new Dimension(133, 23));
        btnRefresh.setBackground(Color.DARK_GRAY);
        mnRemove.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.refresh();
            }
        });

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JButton btnClear = new JButton("Od\u015Bwie\u017C");
        btnClear.setForeground(new Color(0, 200, 0));
=======
        JButton btnClear = new JButton("OdÅ›wieÅ¼");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        btnClear.setPreferredSize(new Dimension(133, 23));
        btnClear.setMinimumSize(new Dimension(133, 23));
        btnClear.setMaximumSize(new Dimension(133, 23));
        btnClear.setBackground(Color.DARK_GRAY);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.clear();
            }
        });
        mnRemove.add(btnClear);
        mnRemove.add(btnVertex_1);

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JButton btnEdge_1 = new JButton("Usu\u0144 kraw\u0119d\u017A");
        btnEdge_1.setForeground(new Color(0, 200, 0));
=======
        JButton btnEdge_1 = new JButton("UsuÅ„ krawÄ™dÅº");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        btnEdge_1.setMaximumSize(new Dimension(133, 23));
        btnEdge_1.setMinimumSize(new Dimension(133, 23));
        btnEdge_1.setPreferredSize(new Dimension(133, 23));
        btnEdge_1.setBackground(Color.DARK_GRAY);
        btnEdge_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.EDGEREMOVE;
            }
        });
        mnRemove.add(btnEdge_1);

        mnNewMenu_1 = new JMenu("Elementy grafu");
        mnNewMenu_1.setForeground(new Color(0, 200, 0));
        mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_1);

        JButton btnCenter = new JButton("Centrum");
        btnCenter.setForeground(new Color(0, 200, 0));
        btnCenter.setMinimumSize(new Dimension(122, 23));
        btnCenter.setMaximumSize(new Dimension(122, 23));
        btnCenter.setPreferredSize(new Dimension(122, 23));
        btnCenter.setBackground(Color.DARK_GRAY);
        btnCenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.G.graphCenter(graphPanel);
            }
        });
        mnNewMenu_1.add(btnCenter);

        JButton btnPeryfery = new JButton("Peryferium");
        btnPeryfery.setForeground(new Color(0, 200, 0));
        btnPeryfery.setPreferredSize(new Dimension(122, 23));
        btnPeryfery.setMinimumSize(new Dimension(122, 23));
        btnPeryfery.setMaximumSize(new Dimension(122, 23));
        btnPeryfery.setBackground(Color.DARK_GRAY);
        btnPeryfery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.graphPanel.G.graphPeryfery(graphPanel);
            }
        });
        mnNewMenu_1.add(btnPeryfery);

        JButton btnShortestPatch = new JButton("Najkr\u00F3tsza \u015Bcie\u017Cka");
        btnShortestPatch.setForeground(new Color(0, 200, 0));
        btnShortestPatch.setMinimumSize(new Dimension(122, 23));
        btnShortestPatch.setMaximumSize(new Dimension(122, 23));
        btnShortestPatch.setPreferredSize(new Dimension(122, 23));
        btnShortestPatch.setBackground(Color.DARK_GRAY);
        btnShortestPatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.currently = Now.PATCH;
            }
        });
        mnNewMenu_1.add(btnShortestPatch);
        
        JMenu mnInformacje = new JMenu("Informacje");
        mnInformacje.setForeground(new Color(0, 200, 0));
        mnInformacje.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnInformacje);
        
        JButton btnSpjny = new JButton("Sp\u00F3jno\u015B\u0107");
        btnSpjny.setForeground(new Color(0, 200, 0));
        btnSpjny.setMinimumSize(new Dimension(88, 23));
        btnSpjny.setMaximumSize(new Dimension(88, 23));
        btnSpjny.setPreferredSize(new Dimension(88, 23));
        btnSpjny.setBackground(Color.DARK_GRAY);
        btnSpjny.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String s="Nie jest spójny";
        		if(Main.graphPanel.G.Connected(Main.graphPanel.G)) {
        			s="Jest spójny";
        		}
        		JOptionPane.showMessageDialog(Main.graphPanel,s);
        	}
        });
        mnInformacje.add(btnSpjny);
        
        JButton btnStopieWierzchoka = new JButton("Stopie\u0144 wierzcho\u0142ka");
        btnStopieWierzchoka.setForeground(new Color(0, 200, 0));
        btnStopieWierzchoka.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Main.currently=Now.DEGREE;
        	}
        });
        btnStopieWierzchoka.setPreferredSize(new Dimension(88, 23));
        btnStopieWierzchoka.setMinimumSize(new Dimension(88, 23));
        btnStopieWierzchoka.setMaximumSize(new Dimension(88, 23));
        btnStopieWierzchoka.setBackground(Color.DARK_GRAY);
        mnInformacje.add(btnStopieWierzchoka);
        
        JButton btnEkscentrycznocWierzchoka = new JButton("Ekscentryczno\u015Bc wierzcho\u0142ka");
        btnEkscentrycznocWierzchoka.setForeground(new Color(0, 200, 0));
        btnEkscentrycznocWierzchoka.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Main.currently=Now.ECCENTRICY;
        	}
        });
        btnEkscentrycznocWierzchoka.setMaximumSize(new Dimension(88, 23));
        btnEkscentrycznocWierzchoka.setMinimumSize(new Dimension(88, 23));
        btnEkscentrycznocWierzchoka.setPreferredSize(new Dimension(88, 23));
        btnEkscentrycznocWierzchoka.setBackground(Color.DARK_GRAY);
        mnInformacje.add(btnEkscentrycznocWierzchoka);

        JMenu mnSettings = new JMenu("Ustawienia");
        mnSettings.setForeground(new Color(0, 200, 0));
        mnSettings.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnSettings);
        mnSettings.setBackground(Color.DARK_GRAY);

<<<<<<< HEAD:GraphApp/src/graph/app/Main.java
        JLabel lblNewLabel = new JLabel("Szybko\u015B\u0107 animacji");
        lblNewLabel.setForeground(new Color(0, 200, 0));
=======
        JLabel lblNewLabel = new JLabel("SzybkoÅ›Ä‡ animacji");
>>>>>>> 43a66a16ffb789e5dca20ef1619c6c972b21dab7:GraphApp.2.2/src/graph/app/Main.java
        mnSettings.add(lblNewLabel);
        

        JSpinner spinner = new JSpinner();
        spinner.setForeground(new Color(0, 200, 0));
        spinner.setBackground(new Color(105, 105, 105));
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {

            }
        });
        spinner.setModel(new SpinnerNumberModel(500, 0, 1000000, 10));
        mnSettings.add(spinner);

        JRadioButton rdbtnEtykietyWidoczne = new JRadioButton("Etykiety  widoczne");
        
        JButton btnApplay = new JButton("Zastosuj");
        btnApplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int t = (Integer) spinner.getValue();
                Graph.t = t;
                if(rdbtnEtykietyWidoczne.isSelected()) {
                	Main.graphPanel.showLabels();
                }
                else {
                	Main.graphPanel.hideLabels();
                }
            }
        });
        
        mnSettings.add(rdbtnEtykietyWidoczne);
        mnSettings.add(btnApplay);
	    }
		public boolean getMnNewMenu_1Enabled() {
			return mnNewMenu_1.isEnabled();
		}
		public void setMnNewMenu_1Enabled(boolean enabled) {
			mnNewMenu_1.setEnabled(enabled);
		}
}

