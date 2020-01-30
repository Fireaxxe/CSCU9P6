import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Boundary extends JFrame implements ActionListener {

 	// Owner Information
    private JLabel ownerLbl = new JLabel("Owner",JLabel.RIGHT);
	private JTextField ownerTxt = new JTextField(20);

	// Pet Information
	private JLabel petNameLbl = new JLabel("Pet Name",JLabel.RIGHT);
	private JTextField petNameTxt = new JTextField(20);
	private JLabel petAgeLbl = new JLabel("Pet Age",JLabel.RIGHT);
	private JTextField petAgeTxt = new JTextField(20);

	// Buttons 
	private JButton mkPetBtn = new JButton("New Pet");
	private JButton getPetBtn = new JButton("Get Pet");
	private JButton mkOwnerBtn = new JButton("New Owner");

	// Our array of pet owners...
	
	/**
	 * @directed true
	 * @supplierCardinality 0..*
	 */
	
	private Owner[] ownList = new Owner[10];
	private int numOwners = 0;


	// Create the application
	public static void main(String[] args) {
		Boundary app = new Boundary();
		app.setTitle("Pet Example");
		app.setSize(300,300);
		app.setLocation(300, 300);
		app.setVisible(true);
	} // main

    // Constructor for the Boundary Application
	public Boundary() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container window = getContentPane();

	    // Pick a layout manager for the interface
	    window.setLayout(new GridLayout(5,2,4,4));

		// Set up the text fields for creating owners
	    window.add(ownerLbl);
	    window.add(ownerTxt);

		// Set up the text fields for creating a pet
	    window.add(petNameLbl);
	    window.add(petNameTxt);

		// Set up the text fields for the pet's age
	    window.add(petAgeLbl);
	    window.add(petAgeTxt);

		// Add all the relevant buttons to the layout
	    window.add(mkOwnerBtn);
	    window.add(mkPetBtn);
	    window.add(getPetBtn);
	    window.add(new JLabel(" ")); // Pad out the display

		// Listen for any JButton clicks so that
		// actionPerformed can catch them
		mkOwnerBtn.addActionListener(this);
		mkPetBtn.addActionListener(this);
		getPetBtn.addActionListener(this);

	} // constructor

	public void actionPerformed(ActionEvent event)
	{
		// Check to see if the event has come from the
		// make owner JButton
 		if (event.getSource() == mkOwnerBtn)
 		{
 			mkOwner(ownerTxt.getText());
			ownerTxt.setText("");
		}

		// Check to see if the event has come from the mkPetBtn JButton
		if (event.getSource() == mkPetBtn)
		{
			try
			{
				int age = Integer.parseInt(petAgeTxt.getText());
				if (!mkPet(ownerTxt.getText(),petNameTxt.getText(),age))
					ownerTxt.setText("Wrong owner");
				petNameTxt.setText("");
				petAgeTxt.setText("");
			}
			catch (Exception ex) {
				petNameTxt.setText("");
				petAgeTxt.setText(ex.toString());
			}
		}

		// Check to see if the event has come from the getPetBtn JButton
		if (event.getSource() == getPetBtn)
		{
			petNameTxt.setText(petName(ownerTxt.getText()));
		}

	} // actionPerformed

	// --- Public operations as shown in the class diagram ---

	// Make a new owner
	public void mkOwner(String ownerName) {

		if (numOwners < 10) 
			ownList[numOwners++] = new Owner(ownerName);

	}

	// Add a pet to an owner
	public boolean mkPet(String ownerName, String petName, int petAge)
	{
		// Look through the array of owners for
		// a match to the one provided
		Owner own = find(ownerName);

		// If we have a valid owner and that
		// owner matches the name that we provided
		if (own != null && own.equals(ownerName))
		{
			own.mkPet(petName, petAge);
			return true;
		}
		else
			return false;
	}

	// Find an owner's pet name
	public String petName(String ownerName)
	{
		// Look through the array of owners for
		// a match to the one provided
		Owner own = find(ownerName);

		if (own != null && own.equals(ownerName))
			return own.getPetName();
		else
			return "Wrong owner";
	}

	// Find the owner 'ownerName' in the array of
	// available owners.
	private Owner find(String ownerName)
	{
		for (int i = 0; i < numOwners; i++) {
			if (ownList[i].equals(ownerName))
				return ownList[i];
		}
		return null;
	}

} // Boundary