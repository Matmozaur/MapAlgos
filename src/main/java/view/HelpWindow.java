package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class HelpWindow extends JFrame {
    HelpWindow(URL helpFile){
        setTitle("MapApp");
        setBounds(100, 100, 800, 800);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if ( helpFile == null )
        {
            System.err.println( "helpFile url is null." );
            return;
        }
        try
        {
            setBounds(100, 100, 200, 300);
            final JEditorPane editorPane = new JEditorPane( helpFile );
            editorPane.setEditable( false );
            editorPane.setBorder( BorderFactory.createEmptyBorder( 10, 0, 10, 10 ) );
            final JScrollPane editorScrollPane = new JScrollPane( editorPane );
            editorScrollPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
            editorScrollPane.setPreferredSize( new Dimension( 800, 800 ) );
            getContentPane().add( editorScrollPane, BorderLayout.CENTER );
            final ActionMap am = getRootPane().getActionMap();
            final InputMap im = getRootPane().getInputMap( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
            final Object hideKey = new Object();
            final Action hideAction = new AbstractAction() {
                private static final long serialVersionUID = 6288745091648466880L;
                @Override
                public void actionPerformed( final ActionEvent e )
                {
                    setVisible( false );
                }
            };
            im.put( KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0 ), hideKey );
            am.put( hideKey, hideAction );
            pack();
            setDefaultCloseOperation( WindowConstants.HIDE_ON_CLOSE );
        }
        catch ( final IOException e )
        {
            e.printStackTrace();
        }
    }
}
