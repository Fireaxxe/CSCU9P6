import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/* Generated by Together */

/**
 * This represents a hypothetical clock set to produce a "new day" signal every
 * midnight.
 *
 * In this emulation the signal can be triggered by a simple button all by
 * itself in a JFrame. The uses made of this timing signal are described in the
 * Timer use case diagram (hyperlinked from this class).
 *
 * There will only be a single instance of this class. It will maintain a
 * current Date object, incremented with each new day. The interface will always
 * show the current date.
 *
 * @stereotype boundary
 */
public class Timer extends JFrame {
	private Container contentPane;

	public Timer(System_status systemStatus, Permit_list permitList) {
		lnkSystem_status = systemStatus;
		lnkPermit_list = permitList;
		today = new Date();
		lnkSystem_status.changeDay(today);

		/*
		 * set window size and location for new day Button
		 */
		this.setBounds(700, 250, 200, 120);
		this.setResizable(false);
		this.setTitle("Developer Timer");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = this.getContentPane();
		contentPane.setLayout(null);

		/*
		 * Button to initiate a new day
		 */
		JButton btnNewDay = new JButton("New Day");
		btnNewDay.setBounds(25, 10, 140, 50);

		/*
		 * calls increment method in Date class to manually start a new day in
		 * the system.
		 */
		btnNewDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				today.increment();
				lnkPermit_list.informNewDate(today);
				lnkSystem_status.changeDay(today);
			}
		});

		contentPane.add(btnNewDay);
		this.setVisible(true);

	}

	/**
	 * The Timer has a navigable association to the system status so that it can
	 * send the new date each time that a new day starts.
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 * @label It's a new day
	 * @directed
	 */
	private System_status lnkSystem_status;

	/**
	 * The Timer has a navigable association to the permit list so that it can
	 * send the new date each time that a new day starts.
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 * @label It's a new day
	 * @directed
	 */
	private Permit_list lnkPermit_list;

	/**
	 * This attribute holds today's date.
	 *
	 * It is incremented every time a midnight "tick" occurs (by a click on the
	 * button in the visible interface).
	 *
	 * The date is sent to the System_status every time that a tick occurs, so
	 * that it can keep an up-to-date note of the date (for example for
	 * attaching to each entry in the log).
	 *
	 * The date is also sent to the Permit_list every time that a tick occurs so
	 * that tidying up actions on the permits can be carried out - for example
	 * automatically cancelling expired permits.
	 * 
	 * @supplierCardinality 1
	 * @clientCardinality 1
	 * @link aggregation
	 * @label Contains
	 * @directed
	 */
	private Date today;

}