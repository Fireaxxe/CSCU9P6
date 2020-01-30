import javax.swing.JButton;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Controller extends JFrame
                        implements ActionListener {
  
  private Model model;
  private View view;
  private JButton sell;        // To decrement (in the model) the stock level of the selected item (in the view)
  private JButton stock;       // To prompt the view to show stock level of the selected item
  private JButton quit;        // As it says
  
  public Controller(Model model) {
    this.model = model;
    setTitle("Controller");
    // Set up input GUI
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container window = getContentPane();
    window.setLayout(new FlowLayout());     // The default is that JFrame uses BorderLayout

    sell = new JButton("Sell");
    window.add(sell);
    sell.addActionListener(this);
    stock = new JButton("Show stock level");
    window.add(stock);
    stock.addActionListener(this);
    quit = new JButton("Quit");
    window.add(quit);
    quit.addActionListener(this);
    
    // Create view
    view = new View(this, model);
    window.add(view);
    
    // Display the frame
    setSize(500,200);
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == sell) {
      int n = view.getSelected();    // Index of item in the DB, or -1 if none selected
      view.deselect();
      if (n != -1)  
        model.decrement(n);
    }
    else if (e.getSource() == stock) {
      int n = view.getSelected();
      view.deselect();
      if (n != -1)
        view.showDetails(n);
    }
    else if (e.getSource() == quit)
      System.exit(0);
  }
  
}
