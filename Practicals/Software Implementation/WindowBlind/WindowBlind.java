// A basic Java Swing application: A slider can be adjusted to open and close a "blind"

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class WindowBlind extends JFrame
                         implements ChangeListener {

    private JSlider slider;           // A slider to adjust a "blind"
    private int sliderValue = 0;      // To record the current slider/blind setting
    private JPanel panel;             // For drawing on

    // The main method launches the application
    public static void main(String[] args) {

        WindowBlind applic = new WindowBlind();
        applic.setLocation(100,100);
        applic.setVisible(true);

    } // main

    // Constructor - executed at instantiation of the class
    // Sets up the application's window
    public WindowBlind() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("WindowBlind");
        setSize(300,300);

        Container window = getContentPane();
        window.setLayout(new FlowLayout());     // The default is that JFrame uses BorderLayout

        panel = new JPanel() {
                    // This paintComponent overrides the default one in JPanel (which does nothing).
                    // paintComponent is called automatically when a screen refresh is needed.
                    // The screen (Graphics parameter g) has already been cleared before paintComponent is called
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g); // Paint the panel's background
                        paintScreen(g);          // Now do our custom drawing
                    } // paintComponent
                };
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setBackground(Color.white);
        window.add(panel);

        slider = new JSlider(JSlider.VERTICAL,0,100,0);
        slider.setInverted(true);               // 0 will be at top, not bottom, of vertical slider
        window.add(slider);
        slider.addChangeListener(this);         // Register for slider events

    } // WindowBlind constructor

    // Re-draws the window image: a "blind" covering a "window" through which the blue sky can be seen,
    // referring to the global variable sliderValue to determine the blind size
    public void paintScreen(Graphics g) {

        g.setColor(Color.cyan);
        g.fillRect(70, 40, 60, 100);         // The blue sky
        g.setColor(Color.lightGray);
        g.fillRect(70, 40, 60, sliderValue); // The blind, partially closed
        g.setColor(Color.black);
        g.drawRect(70, 40, 60, 100);         // The window frame

    } // paintScreen

    // When the slider is adjusted, this method is called automatically
    public void stateChanged(ChangeEvent e) {

          sliderValue = slider.getValue();  // Fetch the slider's current setting
          repaint();                        // Force a screen refresh (paintComponent is called indirectly)

    } // stateChanged

} // class WindowBlind
