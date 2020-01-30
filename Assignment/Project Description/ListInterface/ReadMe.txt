Here are the key points for using a JList:
------------------------------------------

A JList gives a nice visible list of items on the screen (not a drop-down combo-box).
The user can select an item (or multiple items if the JList is configured).

A convenient way to use a JList is to allow the user to select an item, and then 
click a button to indicate the action to take. The  actionPerformed event handler 
can interrogate the JList  to find out what is selected.

Alternatively, you can add a ListSelectionListener to the JList, which causes the
valueChanged(...) event handler to be called whenever there is a selection change.
But be careful because you can also get events when items are *de*selected. 

How to use:
--------------

Imports:

import javax.swing.JList;
import javax.swing.DefaultListModel;     (to hold the items displayed by the JList)
import javax.swing.JScrollPane;          (useful to make the list scrollable)

Setting up:

Global widget creation:

  private JList myList = new JList(new DefaultListModel());

  private JScrollPane scrollingList = new JScrollPane(myList);  // Scrollable version
  
In GUI construction:

    myList.setVisibleRowCount(5);    // Or whatever you want; default is 8
    
    panel.add(myList);               // To get the non-scrolling version
    panel.add(scrollingList);        // To get the scrolling version


Managing the items in the list - updates are reflected immediately in the displayed JList:

    DefaultListModel theList = (DefaultListModel) myList.getModel();  // Fetch the internal "list model"
    
    theList.clear();             // Empty the list
    
    theList.addElement(elem);    // Add an element, where elem is any object reference, eg a String
                                 // Note: The elements, elem, can be *any* class - the text displayed in the
                                 // visible widget will be elem.toString()
    
    theList.removeElement(elem); // Remove an element

    See DefaultListModel on-line documentation for more operations

Managing the displayed JList:

    myList.clearSelection();            // Deselect currently selected item

    int n = myList.getSelectedIndex();  // Get index of currently selected item
    
    Object elem = myList.getSelectedValue(); // Get the actual element object behind the 
                                             // visible selected value (note: elem will usually need casting)

    See JList on-line documentation for more operations
