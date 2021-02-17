
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.net.URL;
//import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling 
 * @version 1.0 (Jan 2003) DB edited (2019) BV edited (Feb 2021)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;
    private JPanel aBDrct, aBCmdL, aBCmdR;
    private JButton aBHelp, aBQuit, aBLook, aBEat;
    private JButton aBNorth, aBSouth, aBEast, aBWest, aBUp, aBDown, aBVoid1, aBVoid2, aBVoid3;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "./Images/" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Zork" ); // change the title
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel1 = new JPanel();
        JPanel vPanel2 = new JPanel();

        this.aBCmdR = new JPanel(); 
        this.aBCmdR.setLayout( new GridLayout(2,1) );

        this.aBLook = new JButton( "Look" );
        this.aBEat = new JButton( "Eat" );

        this.aBCmdR.add( this.aBLook );
        this.aBCmdR.add( this.aBEat );

        this.aBCmdL = new JPanel(); 
        this.aBCmdL.setLayout( new GridLayout(2,1) );

        this.aBHelp = new JButton( "Help" );
        this.aBQuit = new JButton( "Quit" );

        this.aBCmdL.add( this.aBHelp );
        this.aBCmdL.add( this.aBQuit );

        
        this.aBDrct = new JPanel(); 
        this.aBDrct.setLayout( new GridLayout(3,3) );

        this.aBNorth = new JButton( "North" );
        this.aBSouth = new JButton( "South" );
        this.aBEast = new JButton( "East" );
        this.aBWest = new JButton( "West" );
        this.aBUp = new JButton( "Up" );
        this.aBDown = new JButton( "Down" );
        this.aBVoid1 = new JButton( "" );
        this.aBVoid2 = new JButton( "" );
        this.aBVoid3 = new JButton( "" );
        
        this.aBDrct.add( this.aBUp );
        this.aBDrct.add( this.aBNorth );
        this.aBDrct.add( this.aBVoid1 );
        this.aBDrct.add( this.aBWest );
        this.aBDrct.add( this.aBVoid2 );
        this.aBDrct.add( this.aBEast );
        this.aBDrct.add( this.aBDown );
        this.aBDrct.add( this.aBSouth );
        this.aBDrct.add( this.aBVoid3 );
        
        
        this.aImage = new JLabel();

        vPanel1.setLayout( new BorderLayout() ); // ==> only five places
        vPanel1.add( this.aImage, BorderLayout.NORTH );
        vPanel1.add( vListScroller, BorderLayout.CENTER );
        vPanel1.add( this.aEntryField, BorderLayout.SOUTH );
        
        vPanel2.setLayout( new BorderLayout() );
        vPanel2.add( this.aBCmdL, BorderLayout.WEST );
        vPanel2.add( this.aBDrct, BorderLayout.CENTER );
        vPanel2.add( this.aBCmdR, BorderLayout.EAST );

        this.aMyFrame.getContentPane().add( vPanel1, BorderLayout.CENTER );
        this.aMyFrame.getContentPane().add( vPanel2, BorderLayout.SOUTH );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
            // aBCmdL
        this.aBLook.addActionListener( this );
        this.aBEat.addActionListener( this );
            // aBCmdL
        this.aBHelp.addActionListener( this );
        this.aBQuit.addActionListener( this );
            // aBDrct
        this.aBNorth.addActionListener( this );
        this.aBSouth.addActionListener( this );
        this.aBEast.addActionListener( this );
        this.aBWest.addActionListener( this );
        this.aBUp.addActionListener( this );
        this.aBDown.addActionListener( this );
        
        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     * @param action
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if ( pE.getSource() == this.aBLook ) 
            this.aEngine.interpretCommand("look");
        else if ( pE.getSource() == this.aBEat ) 
            this.aEngine.interpretCommand("eat");
        else if ( pE.getSource() == this.aBHelp ) 
            this.aEngine.interpretCommand("help");
        else if ( pE.getSource() == this.aBQuit ) 
            this.aEngine.interpretCommand("quit");
        else if ( pE.getSource() == this.aBNorth ) 
            this.aEngine.interpretCommand("go north");
        else if ( pE.getSource() == this.aBSouth ) 
            this.aEngine.interpretCommand("go south");
        else if ( pE.getSource() == this.aBEast ) 
            this.aEngine.interpretCommand("go east");
        else if ( pE.getSource() == this.aBWest )  
            this.aEngine.interpretCommand("go west");
        else if ( pE.getSource() == this.aBUp ) 
            this.aEngine.interpretCommand("go up");
        else if ( pE.getSource() == this.aBDown ) 
            this.aEngine.interpretCommand("go down");
        else this.processCommand(); // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
