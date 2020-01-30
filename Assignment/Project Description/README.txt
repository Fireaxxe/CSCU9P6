CSCU9P6 Object Oriented Implementation Group Project   Spring 2019

This project is based on the University's car parking arrangements around year 2000:
The University's road entrances were protected by automatic barriers. There were 
number plate reading cameras at each entrance. Drivers had to buy permits for
their car(s). The camera/barrier system would only allow a vehicle onto the campus
if the number plate was associated with a valid permit in the central computer system.
The document CarEntryAndParkingRegulations.pdf contains the guidance and regulations 
applying to vehicular campus access at that time.

There is description of the general arrangements for the group project
in file GroupProjectDescription.pdf 

In folder PACSUS is a Together project. Take a copy of this, and remove the
read-only properties if necessary. 

To make use of the PACSUS project, launch Together Architect, switch to the
Modelling perspective and use File menu / Import. In the Import dialogue
box choose "Existing projects into workspace" and then Next. Click Browse
to Select root directory, and browse to locate your copied PACSUS folder.
Then click Finish.

THEN

**** Read the "Introduction to PACSUS" use case diagram FIRST.****

The Model Navigator may display a *very* long list of items that includes
individual notes (from all diagrams), use cases and actors (from use case 
diagrams), and objects (from sequence diagrams). You can hide these so that 
you can more clearly see the classes, use case diagrams, etc as follows: 
At the right hand end of the Model Navigator button bar there is a small, 
white, down pointing triangle. Click it - the View control menu drops down, 
then select Filters option to choose what to *hide*: 
    Make sure the box next to Design Entities is checked to hide actors, objects, 
    notes, etc in the Model Navigator.
    That should help - though unfortunately the filter settings are not a part of 
    the project itself and so you may need to set them up each time. The Model 
    Navigator simplifies considerably.

In PACSUS-HTML is a browsable HTML version of the Together-
generated documentation for PACSUS. Open it and double click on index.html 
to get started. Includes diagrams as GIFs in a subdirectory.

Extra information:

The Together design contains a great deal of information:
   the main default class diagram,
   use case diagrams,
   some typical sequence diagrams for important processing steps.

Hints on viewing the Together design:

- I recommend that for improved readability of the class diagram you make the following
  changes to the diagram viewing preferences (if not already done): 
  Select Window menu, Preferences, then expand the Modelling section, then:
  - Click Java, and un-select the two Java Bean Properties Support options.
  - Click View management, Show/Hide tab, and un-select the Dependencies option.

- Since most diagrams oveflow a single screen remember: 
  - You can Zoom the Diagram pane in and out 
  - The eye button above the Diagram pane pops up an Overview pane that 
    lets you pan around the Diagram pane very easily.

- Many diagram elements have important extra information in the Description 
  tab of the Properties pane, or, for Association links in the class
  diagram, in the Javadoc tab in the Properties Pane.   
  - If you open the Properties pane, and select Description, then you can select 
    items in the diagrams one after the other and the Description will stay open to show 
    you each item's description.
  - Some descriptions are quite long - a good way to view these is to click on the ... that 
    appears after the text in the selected line - a separate window with the description 
    will pop up.

- And there are various Notes scattered around the diagrams.

- Diagram elements with blue names have *hyperlinks* to related parts of the
  design: right-clicking then choosing Hyperlink from the pop-up menu will
  show the other links.
  
Various notes:

o The folder PACSUS-Java contains all the outline Java files extracted from the Together 
  project.  I recommend that you use these files to build an Eclipse Java project, and upload 
  to our Subversion server, from where all members of the group can work collaboratively.

o You should NOT use threads and concurrency.

o You do not need to populate the vehicle and permit list databases from
  files. Your PACSUS can start 'empty', or you could have some explicit
  initialization in your code, and the information can be lost when it exits.

o Each of the boundary classes can be implemented by a fairly
  straightforward on-screen interface. No need to get into real timers or
  clocks, or real registration number recognition, etc. See the boundary
  class descriptions for info.

o No changes to the class structure are required.

o The actual user interfaces do not need to be beautified, nor too clever
  - just lists, buttons and text fields/areas, will be fine.

o Remember that there is a lot of Java already constructed for you, by
  Together. However, it won't compile as given since various import lines are
  needed, and you'll have to flesh out all sorts of detail. However, it
  should only take a modest amount of work to put the code in compilable
  form, even though when it runs it doesn't do anything!

o Not all parts of the actual University system are required: for example,
  I have omitted things like "drop-off" permits (because they are
  indistinguishable from ordinary permits as far as the barrier system is
  concerned), and the individual barriers do not have "over-ride" buttons for
  use by staff supervising the barriers (not too hard to add though?).

o You can deal with easier parts of PACSUS first, and leave more awkward
  things to later, such as cancelling permits, annual updates, modifying
  permit details (that's potentially the worst one). To start with you could
  just implement the activate/deactivate functionality of the campus security
  screens, system status and barriers - that should give a warm feeling. To
  start with the new permit creation could just deal with one vehicle per
  permit.

o You might find the ListInterface example in the GroupProject folder useful as
  one way to produce a certain kind of user interface/interaction. See the 
  ReadMe.txt file in the ListInterface folder.

o I have suggested that you use Java built-in Hashtables for the permit
  and vehicle lists (so you can avoid writing searching/inserting/deleting
  code). I have put some old basic lecture notes from CSCU9A3 on using the standard
  (non-generic) Hashtable and Vector classes in OldHashTablesAndVectorsNotes.pdf.

o If you decide to use hash tables: More about the Hashtables: 

Let's take the Permit_list:

The individual permits will be stored as the "values". The permit holder
names are used as the "keys". The permit holder name is a String, which is
already equipped with its own suitable "hashCode" and "equals" methods. So,
adding a new Regular_visitor_permit in Permit_list could look like this,
assuming that the has table is in, say, permitList:

   Regular_visitor_permit rvp = new Regular_visitor_permit(name, startDate, endDate);
   permitList.put(name, rvp);

Checking whether a name is present could look like this:

   if (permitList.containsKey(name)) ...

Removing a warning from a named permit could be done like this (assuming
that there is a permit with that permit holder name):

   Permit p = (Permit)permitList.get(name);    // Note the cast
   p.removeWarning();

Slightly harder is doing something to *every* permit (eg the daily
resetting actions required by the timer "new day" tick). The Hashtable
class will produce an "Enumeration" object that allows code like the
following to run systematically through all the permits in the table:

   Enumeration e = permitList.elements(); 
   while(e.hasMoreElements()) {
     Permit p = (Permit)e.nextElement();
     p.dailyReset();
   }

Good luck!

SBJ     February 2019
