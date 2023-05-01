/**
 * The first project of COS70006
 * A parking slot system : Class Application is the Console GUI Interface class including the main() method and
 *                         handling all inputs and outputs.
 * @author    Mona Ma (103699029)
 * @version   JDK1.8 , javac 11.0.14
 * @date      Created on 31 March 2022
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ApplicationGUI extends JFrame{
    //the GUI frame of the car park and other static layers
    private static JFrame jFrame = new JFrame("Swinburne Car Park");
    private static ParkingSlot ps;
    private static Container container;
    private static JPanel jP,bottom1JP,bottom2JP,bottom3JP,bottom4JP,bottom5JP,panel1,panel2,panel3,panel4,panel5;
    private static Box registerNoBox1,ownerBox1,slotNoBox1,typeBox1,registerNoBox2,registerNoBox3,slotNoBox4,slotNoBox5,typeBox4,hBox;
    private static JLabel registerNoLabel1,ownerLabel1,slotNoLabel1,typeLabel1,registerNoLabel2,registerNoLabel3,slotNoLabel4,slotNoLabel5,typeLabel4;
    private static JTextField registerNoField1,ownerField1,slotNoField1,registerNoField2,registerNoField3,slotNoField4,slotNoField5;
    private static JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,confirmButton1,confirmButton2,confirmButton3,confirmButton4,confirmButton5,slotDisplayJB;
    private static JRadioButton staffBtn1,visitorBtn1,staffBtn4,visitorBtn4;
    private static ButtonGroup bg1,bg4;
    private static ArrayList<JButton> slotDisplay;

    //the initial size of the frame
    final int JWIDTH = 1300;
    final int JHEIGHT = 700;
    private static CarPark carPark = new CarPark();

    /**
     * the main function to start the car park GUI system
     */
    public static void main(String[] args){
        slotDisplay = new ArrayList<>();
        slotDisplayJB = new JButton();
        new ApplicationGUI().init();
    }

    /**
    The initial frame layout
     */
    public void init(){
        //set the frame in the middle of the screen
        jFrame.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-JWIDTH)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-JHEIGHT)/2,JWIDTH,JHEIGHT);
        jFrame.setResizable(true);
        container = new Container();
        //set layout manager as border layout
        container.setLayout(new BorderLayout(5,5));
        container.setBackground(new Color(248,215,236));
        //add an input dialog to get the staff slot number from the user
        String staffSlotDialog;
        //make sure the user input is number
        while (true){
            staffSlotDialog = JOptionPane.showInputDialog(jFrame,"Please enter the number of slots for staff:", "Start",JOptionPane.INFORMATION_MESSAGE);
            if(staffSlotDialog == null){
                return;
            }else if(staffSlotDialog.isEmpty()){
                JOptionPane.showMessageDialog(jFrame,"Please input only numbers.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(!staffSlotDialog.matches("^[0-9]{0,3}$")){
                JOptionPane.showMessageDialog(jFrame,"Please input only numbers.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                break;
            }
        }
        int staffSlots = Integer.parseInt(staffSlotDialog.trim());
        //add an input dialog to get the visitor slot number from the user
        String visitorSlotDialog;
        //make sure the user input is number
        while (true){
            visitorSlotDialog = JOptionPane.showInputDialog(jFrame,"Please enter the number of slots for visitor:", "Start",JOptionPane.INFORMATION_MESSAGE);
            if(visitorSlotDialog == null){
                return;
            }else if(visitorSlotDialog.isEmpty()){
                JOptionPane.showMessageDialog(jFrame, "Please input only numbers.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (!visitorSlotDialog.matches("^[0-9]{0,3}$")) {
                JOptionPane.showMessageDialog(jFrame, "Please input only numbers.", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                break;
            }
        }
        int visitorSlots = Integer.parseInt(visitorSlotDialog.trim());
        //make each of the staff slots as a button
        if (staffSlots>0){
            for (int i = 0; i < staffSlots; i++) {
                int newStaffSlot = i + 1;
                String StaffParkNo = String.format("S"+ "%03d",newStaffSlot);   //determine the format of the parking slots identifier for the staff, EG "S001"
                ParkingSlot ps = new ParkingSlot("Staff", StaffParkNo);  //create a new ParkingSlot object and get the information
                carPark.addASlot(ps);                       //add the staffParkingSlot to the ParkingSlot arraylist object for staff
                String slotDisplayST = carPark.listAllSlots().get(i).getSlotNumber();
                String description = carPark.listAllSlots().get(i).toString();
                slotDisplayJB = new JButton(description);
                slotDisplayJB.setName(slotDisplayST);
                slotDisplayJB.setPreferredSize(new Dimension(575,HEIGHT));
                slotDisplayJB.addActionListener(ClickButton);
                slotDisplay.add(slotDisplayJB);
            }
        }
        //make each of the visitor slots as a button
        if (visitorSlots>0) {
            for (int i = 0; i < visitorSlots; i++) {
                int newVisitorSlot = i + 1;
                String VisitorParkNo = String.format("V" + "%03d", newVisitorSlot);  //determine the format of the parking slots identifier for the visitor, eg "V001"
                ParkingSlot ps = new ParkingSlot("Visitor", VisitorParkNo);  //create a new ParkingSlot object and get the information
                carPark.addASlot(ps);                        //add the visitorParkingSlot to the ParkingSlot arraylist object for visitor
                String slotDisplayST2 = carPark.listAllSlots().get(i+staffSlots).getSlotNumber();
                String description2 = carPark.listAllSlots().get(i+staffSlots).toString();
                slotDisplayJB = new JButton(description2);
                slotDisplayJB.setPreferredSize(new Dimension(575,HEIGHT));
                slotDisplayJB.setName(slotDisplayST2);
                slotDisplayJB.addActionListener(ClickButton);
                slotDisplay.add(slotDisplayJB);
            }
        }

        //create panel1 and make all functions as buttons
        panel1 = new JPanel(new GridLayout(7,1,3,3));
        panel1.setPreferredSize(new Dimension(150,700));
        btn1 = new JButton("List all parking slots");
        btn1.setBackground(new Color(255,218,185));
        btn2 = new JButton("Park a car");
        btn2.setBackground(new Color(255,218,185));
        btn3 = new JButton("Find a car");
        btn3.setBackground(new Color(255,218,185));
        btn4 = new JButton("Remove a car");
        btn4.setBackground(new Color(255,218,185));
        btn5 = new JButton("Add a slot");
        btn5.setBackground(new Color(255,218,185));
        btn6 = new JButton("Delete a slot");
        btn6.setBackground(new Color(255,218,185));
        btn7 = new JButton("Exit the system");
        btn7.setBackground(new Color(255,218,185));

        //put these buttons into panel1
        panel1.add(btn1);
        panel1.add(btn2);
        panel1.add(btn3);
        panel1.add(btn4);
        panel1.add(btn5);
        panel1.add(btn6);
        panel1.add(btn7);

        //add different functions as listeners to the buttons
        btn1.addActionListener(ListAll);
        btn2.addActionListener(ParkCar);
        btn3.addActionListener(FindCar);
        btn4.addActionListener(RemoveCar);
        btn5.addActionListener(AddSlot);
        btn6.addActionListener(DeleteSlot);
        btn7.addActionListener(ExitCarPark);



        //create panel4 and display all staff slot in panel 3
        panel4 = new JPanel(new GridLayout(0,1,1,3));

        for (int i = 0; i < slotDisplay.size(); i++) {
            if(slotDisplay.get(i).getName().contains("S")){
                slotDisplay.get(i).setBackground(new Color(176,196,222));
                panel4.add(slotDisplay.get(i));
            }
        }

        //create panel5 and display all visitor slot in panel 4
        panel5 = new JPanel(new GridLayout(0,1,1,3));
        for (int i = 0; i < slotDisplay.size(); i++) {
            if(slotDisplay.get(i).getName().contains("V")){
                slotDisplay.get(i).setBackground(new Color(211,218,196));
                panel5.add(slotDisplay.get(i));
            }
        }

        //add staffSlotTitle button to the north of panel2 and add panel4 to center of panel2
        panel2 = new JPanel(new BorderLayout());
        JButton staffSlotTitle = new JButton("Staff parking slots");
        staffSlotTitle.setBackground(new Color(134,150,167));
        staffSlotTitle.setPreferredSize(new Dimension(575,50));
        panel2.add(staffSlotTitle, BorderLayout.NORTH);
        panel2.add(panel4,BorderLayout.CENTER);

        //add visitorSlotTitle button to the north of panel3 and add panel5 to center of panel3
        panel3 = new JPanel(new BorderLayout());
        JButton visitorSlotTitle = new JButton("Visitor parking slots");
        visitorSlotTitle.setBackground(new Color(123,139,111));
        visitorSlotTitle.setPreferredSize(new Dimension(575,50));
        panel3.add(visitorSlotTitle,BorderLayout.NORTH);
        panel3.add(panel5,BorderLayout.CENTER);

        //pack panel 1, panel 3, panel 4 in a horizontal box
        hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(panel2);
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(panel3);
        hBox.add(Box.createHorizontalStrut(20));

        //add a panel named jP in to the bottom of the frame
        jP = new JPanel();
        jP.setVisible(false);
        jP.setPreferredSize(new Dimension(1300,70));
        jP.setBackground(new Color(160,203,222));

        //make all the input of park a car together to a panel named bottom1JP and add it to panel jP
        //pack the car register number input into a box layout
        registerNoBox1 = Box.createHorizontalBox();
        registerNoLabel1 = new JLabel("Car register number:");
        registerNoField1 = new JTextField(8);
        registerNoBox1.add(registerNoLabel1);
        registerNoBox1.add(Box.createHorizontalStrut(5));
        registerNoBox1.add(registerNoField1);
        //pack the owner name input into a box layout
        ownerBox1 = Box.createHorizontalBox();
        ownerLabel1 = new JLabel("Owner name:");
        ownerField1 = new JTextField(10);
        ownerBox1.add(ownerLabel1);
        ownerBox1.add(Box.createHorizontalStrut(5));
        ownerBox1.add(ownerField1);
        //pack the slot ID input into a box layout
        slotNoBox1 = Box.createHorizontalBox();
        slotNoLabel1 = new JLabel("Slot ID:");
        slotNoField1 = new JTextField(5);
        slotNoBox1.add(slotNoLabel1);
        slotNoBox1.add(Box.createHorizontalStrut(5));
        slotNoBox1.add(slotNoField1);
        //pack the type input into a box layout
        typeBox1 = Box.createHorizontalBox();
        typeLabel1 = new JLabel("Type:");
        staffBtn1 = new JRadioButton("Staff", true);
        staffBtn1.setBackground(new Color(160,203,222));
        visitorBtn1 = new JRadioButton("Visitor", false);
        visitorBtn1.setBackground(new Color(160,203,222));
        bg1 = new ButtonGroup();
        bg1.add(staffBtn1);
        bg1.add(visitorBtn1);
        typeBox1.add(typeLabel1);
        typeBox1.add(Box.createHorizontalStrut(5));
        typeBox1.add(staffBtn1);
        typeBox1.add(Box.createHorizontalStrut(5));
        typeBox1.add(visitorBtn1);
        //create a confirm Buttons to park car
        confirmButton1 = new JButton("confirm");
        confirmButton1.addActionListener(ParkCarAction);
        //create a panel to add all these input together for parking a car
        bottom1JP = new JPanel();
        bottom1JP.add(registerNoBox1);
        bottom1JP.add(Box.createHorizontalStrut(5));
        bottom1JP.add(ownerBox1);
        bottom1JP.add(Box.createHorizontalStrut(5));
        bottom1JP.add(slotNoBox1);
        bottom1JP.add(Box.createHorizontalStrut(5));
        bottom1JP.add(typeBox1);
        bottom1JP.add(Box.createHorizontalStrut(5));
        bottom1JP.add(confirmButton1);
        bottom1JP.setBackground(new Color(160,203,222));
        bottom1JP.setVisible(false);
        jP.add(bottom1JP);

        //make all the input of find a car together to a panel named bottom2JP and add it to panel jP
        //pack the car register number input into a box layout
        registerNoBox2 = Box.createHorizontalBox();
        registerNoLabel2 = new JLabel("Car register number:");
        registerNoField2 = new JTextField(8);
        registerNoBox2.add(registerNoLabel2);
        registerNoBox2.add(Box.createHorizontalStrut(5));
        registerNoBox2.add(registerNoField2);
        //create a comfirm button for search a car
        confirmButton2 = new JButton("confirm");
        confirmButton2.addActionListener(FindCarAction);
        bottom2JP = new JPanel();
        bottom2JP.add(registerNoBox2);
        bottom2JP.add(Box.createHorizontalStrut(5));
        bottom2JP.add(confirmButton2);
        bottom2JP.setBackground(new Color(160,203,222));
        bottom2JP.setVisible(false);
        jP.add(bottom2JP);

        //make all the input of remove a car together to a panel named bottom3JP and add it to panel jP
        //pack the car register number input into a box layout
        registerNoBox3 = Box.createHorizontalBox();
        registerNoLabel3 = new JLabel("Car register number:");
        registerNoField3 = new JTextField(8);
        registerNoBox3.add(registerNoLabel3);
        registerNoBox3.add(Box.createHorizontalStrut(5));
        registerNoBox3.add(registerNoField3);
        //create a confirm button to remove the car
        confirmButton3 = new JButton("confirm");
        confirmButton3.addActionListener(RemoveCarAction);
        bottom3JP = new JPanel();
        bottom3JP.add(registerNoBox3);
        bottom3JP.add(Box.createHorizontalStrut(5));
        bottom3JP.add(confirmButton3);
        bottom3JP.setBackground(new Color(160,203,222));
        bottom3JP.setVisible(false);
        jP.add(bottom3JP);


        //make all the input of add a slot together to a panel named bottom4JP and add it to panel jP
        //pack the slot ID input into a box layout
        slotNoBox4 = Box.createHorizontalBox();
        slotNoLabel4 = new JLabel("Slot ID:");
        slotNoField4 = new JTextField(5);
        slotNoBox4.add(slotNoLabel4);
        slotNoBox4.add(Box.createHorizontalStrut(5));
        slotNoBox4.add(slotNoField4);
        //pack the type input into a box layout
        typeBox4 = Box.createHorizontalBox();
        typeLabel4 = new JLabel("Type:");
        staffBtn4 = new JRadioButton("Staff", true);
        staffBtn4.setBackground(new Color(160,203,222));
        visitorBtn4 = new JRadioButton("Visitor", false);
        visitorBtn4.setBackground(new Color(160,203,222));
        bg4 = new ButtonGroup();
        bg4.add(staffBtn4);
        bg4.add(visitorBtn4);
        typeBox4.add(typeLabel4);
        typeBox4.add(Box.createHorizontalStrut(5));
        typeBox4.add(staffBtn4);
        typeBox4.add(Box.createHorizontalStrut(5));
        typeBox4.add(visitorBtn4);
        //create a confirm button to add a slot
        confirmButton4 = new JButton("confirm");
        confirmButton4.addActionListener(AddSlotAction);
        bottom4JP = new JPanel();
        bottom4JP.add(slotNoBox4);
        bottom4JP.add(Box.createHorizontalStrut(5));
        bottom4JP.add(typeBox4);
        bottom4JP.add(Box.createHorizontalStrut(5));
        bottom4JP.add(confirmButton4);
        bottom4JP.setBackground(new Color(160,203,222));
        bottom4JP.setVisible(false);
        jP.add(bottom4JP);

        //make all the input of delete a slot together to a panel named bottom5JP and add it to panel jP
        //pack the slot ID input into a box layout
        slotNoBox5 = Box.createHorizontalBox();
        slotNoLabel5 = new JLabel("Slot ID:");
        slotNoField5 = new JTextField(5);
        slotNoBox5.add(slotNoLabel5);
        slotNoBox5.add(Box.createHorizontalStrut(5));
        slotNoBox5.add(slotNoField5);
        //create a confirm button to delete a slot
        confirmButton5 = new JButton("confirm");
        confirmButton5.addActionListener(DeleteSlotAction);
        bottom5JP = new JPanel();
        bottom5JP.add(slotNoBox5);
        bottom5JP.add(Box.createHorizontalStrut(5));
        bottom5JP.add(confirmButton5);
        bottom5JP.setBackground(new Color(160,203,222));
        bottom5JP.setVisible(false);
        jP.add(bottom5JP);

        //add panel1, hBox and jP panel to the container
        container.add(panel1,BorderLayout.WEST);
        container.add(hBox,BorderLayout.CENTER);
        container.add(jP,BorderLayout.SOUTH);

        //add the container to the frame
        jFrame.add(container);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * the action listener list all parking slots in the car park
     */
    static ActionListener ListAll = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //change the color of the clicked button
            btn1.setBackground(new Color(244,164,96));
            btn2.setBackground(new Color(255,218,185));
            btn3.setBackground(new Color(255,218,185));
            btn4.setBackground(new Color(255,218,185));
            btn5.setBackground(new Color(255,218,185));
            btn6.setBackground(new Color(255,218,185));
            btn7.setBackground(new Color(255,218,185));

            Refresh();
        }
    };

    /**
     * the action listener display the park car panel for user to input information
     */
    static ActionListener ParkCar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //change the color of park a car button
            btn1.setBackground(new Color(255,218,185));
            btn2.setBackground(new Color(244,164,96));
            btn3.setBackground(new Color(255,218,185));
            btn4.setBackground(new Color(255,218,185));
            btn5.setBackground(new Color(255,218,185));
            btn6.setBackground(new Color(255,218,185));
            btn7.setBackground(new Color(255,218,185));

            //set visible true of the park a car input panel
            jP.setVisible(true);
            bottom1JP.setVisible(true);
            bottom2JP.setVisible(false);
            bottom3JP.setVisible(false);
            bottom4JP.setVisible(false);
            bottom5JP.setVisible(false);
        }
    };

    /**
     * the action listener display the find a  car panel for user to input information
     */
    static ActionListener FindCar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //change the color of find a car button
            btn1.setBackground(new Color(255,218,185));
            btn2.setBackground(new Color(255,218,185));
            btn3.setBackground(new Color(244,164,96));
            btn4.setBackground(new Color(255,218,185));
            btn5.setBackground(new Color(255,218,185));
            btn6.setBackground(new Color(255,218,185));
            btn7.setBackground(new Color(255,218,185));

            //set visible true of the find a car input panel
            jP.setVisible(true);
            bottom1JP.setVisible(false);
            bottom2JP.setVisible(true);
            bottom3JP.setVisible(false);
            bottom4JP.setVisible(false);
            bottom5JP.setVisible(false);
        }
    };

    /**
     * the action listener display the remove a car panel for user to input information
     */
    static ActionListener RemoveCar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //change the color of remove a car button
            btn1.setBackground(new Color(255,218,185));
            btn2.setBackground(new Color(255,218,185));
            btn3.setBackground(new Color(255,218,185));
            btn4.setBackground(new Color(244,164,96));
            btn5.setBackground(new Color(255,218,185));
            btn6.setBackground(new Color(255,218,185));
            btn7.setBackground(new Color(255,218,185));

            //set visible true of remove a car input panel
            jP.setVisible(true);
            bottom1JP.setVisible(false);
            bottom2JP.setVisible(false);
            bottom3JP.setVisible(true);
            bottom4JP.setVisible(false);
            bottom5JP.setVisible(false);
        }
    };

    /**
     * the action listener display the add a slot panel for user to input information
     */
    static ActionListener AddSlot = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //change the color of add a slot button
            btn1.setBackground(new Color(255,218,185));
            btn2.setBackground(new Color(255,218,185));
            btn3.setBackground(new Color(255,218,185));
            btn4.setBackground(new Color(255,218,185));
            btn5.setBackground(new Color(244,164,96));
            btn6.setBackground(new Color(255,218,185));
            btn7.setBackground(new Color(255,218,185));

            //set visible true of the add a slot input button
            jP.setVisible(true);
            bottom1JP.setVisible(false);
            bottom2JP.setVisible(false);
            bottom3JP.setVisible(false);
            bottom4JP.setVisible(true);
            bottom5JP.setVisible(false);
        }
    };

    /**
     * the action listener display the delete slot panel for user to input information
     */
    static ActionListener DeleteSlot = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //change the color of delete a slot button
            btn1.setBackground(new Color(255,218,185));
            btn2.setBackground(new Color(255,218,185));
            btn3.setBackground(new Color(255,218,185));
            btn4.setBackground(new Color(255,218,185));
            btn5.setBackground(new Color(255,218,185));
            btn6.setBackground(new Color(244,164,96));
            btn7.setBackground(new Color(255,218,185));

            //set visible true of the delete a slot panel
            jP.setVisible(true);
            bottom1JP.setVisible(false);
            bottom2JP.setVisible(false);
            bottom3JP.setVisible(false);
            bottom4JP.setVisible(false);
            bottom5JP.setVisible(true);
        }
    };

    /**
     * the action listener is to exit from the system
     */
    static ActionListener ExitCarPark = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jFrame.dispose();
        }
    };

    /**
     * the action listener parkCarAction, is used park a car by the input from the user
     */
    static ActionListener ParkCarAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //get all input from the user
            String carRegisterID = registerNoField1.getText().trim();  //get the register number of the car from the user's input
            String slot = slotNoField1.getText().trim();               //get the slot ID of the car from the user's input
            String type = SlotType(staffBtn1,  visitorBtn1);            //get the type of the owner from the user's input
            String ownerName = ownerField1.getText().trim();           //get the owner name of the car from the user's input

            //prompt up a dialog to tell the user if something is wrong or the car has been parked successfully
            JOptionPane.showMessageDialog(jFrame, ParkACar(carRegisterID,slot,type,ownerName), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    };

    /**
     * the action listener findCarAction, is used to find a car by the input from the user
     */
    static  ActionListener FindCarAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            //get car register number from the user
            String register = registerNoField2.getText().trim();

            //give the user information of the car by a prompt dialog
            JOptionPane.showMessageDialog(jFrame, FindACar(register), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    };

    /**
     * the action listener removeCarAction, is used to remove a car by the input from the user
     */
    static ActionListener RemoveCarAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //get input from the user
            String register = registerNoField3.getText().trim();

            //prompt a dialog to let user knows that the car has been removed or not
            JOptionPane.showMessageDialog(jFrame, RemoveACar(register), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    };

    /**
     * the action listener addSlotAction, is used to add a slot by the input from the user
     */
    static ActionListener AddSlotAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //get information from the user input
            String slotID = slotNoField4.getText().trim();
            String slotType = SlotType(staffBtn4, visitorBtn4);

            //prompt an information dialog to let the user know the status of the add slot function
            JOptionPane.showMessageDialog(jFrame, AddASlot(slotID,slotType), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    };

    /**
     * the action listener deleteSlotAction, is used to delete a slot by the input from the user
     */
    static ActionListener DeleteSlotAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //get input information from the user
            String slotNo = slotNoField5.getText().trim();

            //prompt an information to the user of the status
            JOptionPane.showMessageDialog(jFrame, DeleteASlot(slotNo), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    };

    /**
     * the action listener clickButton. When users click a slot in the car park,
     * the car park will prompt a dialog to ask user what they would like to do with this slot
     * and if the slot is not occupied, the user can choose to park a car or delete the slot
     * if the slot is occupied, the user can remove the car.
     */
    static ActionListener ClickButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //get the button name from the click from the user
            String slotNoClick = ((JComponent) e.getSource()).getName();
            int position = 0;
            //find the slot from the parkingSlot Arraylist
            for (int i = 0; i < slotDisplay.size(); i++) {
                if (slotDisplay.get(i).getName().equals(slotNoClick)){
                    position = i;
                    break;
                }
            }
            //dialog to ask the user if they would like to park a car or delete the slot
            if (carPark.listAllSlots().get(position).findCar()== null){
                Object[] choices = {"Park a car", "Delete the slot"};
                String choice = (String) JOptionPane.showInputDialog(jFrame,
                        "What would you like to do?",
                        "Selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        choices, choices[0]
                );
                if (choice != null && choice.length() > 0) {
                    // the user choose to park a car
                    if (choice.equals("Park a car")) {
                        ClickParkCar(position);
                    }
                    //the user would like to delete the slot
                    else{
                        ClickDeleteSlot(position);
                    }
                }
                else {
                    return;
                }
            }
            //dialog to ask the user if they would like to remove a car
            else {
                ClickRemoveCar(position);
            }
        }
    };

    /**
     * get the choice form the user in the JRedioButton group
     * @param staffBtn   the JRedioButton of staff
     * @param visitorBtn the JRedioButton of visitor
     * @return    the selection from the user
     */
    public static String SlotType(JRadioButton staffBtn, JRadioButton visitorBtn) {
        String slotType;
        //get the slot type from the user
        if (staffBtn.isSelected()) {
            slotType = staffBtn.getText();
        } else {
            slotType = visitorBtn.getText();
        }
        return slotType;
    }

    /**
     * Refresh the whole frame to make sure list all parking slots for the last version
     */
    public static void Refresh(){
        //repaint the whole container
        jP.setVisible(false);
        panel4.revalidate();
        panel4.repaint();
        panel5.revalidate();
        panel5.repaint();
        container.revalidate();
        container.repaint();
    }

    /**
     * park a car into a slot from the car park by get car ID, slot ID, owner name and owner type from the user
     * @param carRegisterID  The car register number input by the user
     * @param slot           The slot ID input by the user
     * @param type           The type of the owner input by the user
     * @param ownerName      The owner name input by the user
     * @return               A message include error information or success parked information
     */
    public static String ParkACar(String carRegisterID, String slot, String type, String ownerName){
        String inform = "";

        //if the input not match with the format of car register number
        if (!carRegisterID.matches("^[A-Z]\\d{5}$")) {
            inform = "The car register number should be started with a capital letter and followed by a five-digit number."+"\n";
        }
        //if the input natch with the format of car register number
        else {
            for (int i = 0; i < carPark.listAllSlots().size(); i++) {
                if(carPark.listAllSlots().get(i).findCar()!= null){
                    //compare the input register number of the car with the cars in the slots, if the car is existed, break out from the function
                    if(carPark.listAllSlots().get(i).findCar().getRegNumber().equals(carRegisterID)){
                        inform = "The car has already been parked in the car park." +"\n";
                        break;
                    }
                }
            }
        }
        //if the slot ID is incorrect format
        if(!slot.matches("^[SV]\\d{3}$")){
            inform = inform +"The slot ID should be started with a capital letter S or V and followed by three-digit numbers." + "\n";
        }
        //if the slot can not be found
        else if (carPark.findASlot(slot) == null){
            inform = inform + "The slot is not exist." +"\n";
        }
        else {
            //the user type is staff
            if(type.equals("Staff")) {
                //the slot is for visitor
                if (carPark.findASlot(slot).getSlotType().equals("Visitor")) {
                    inform = inform + "The slot is for visitor and the slot ID for staff should start with capital letter \"S\"." + "\n";
                }
                //the slot has a car
                else if (carPark.findASlot(slot).findCar() != null) {
                    inform = inform + "The slot is occupied." + "\n";
                }
            }
            //the user type is visitor
            else{
                //the slot is for staff
                if(carPark.findASlot(slot).getSlotType().equals("Staff")){
                    inform = inform + "The slot is for visitor and the slot ID for staff should start with capital letter \"V\"." +"\n";
                }
                //the slot has a car
                else if (carPark.findASlot(slot).findCar()!=null){
                    inform = inform + "The slot is occupied." +"\n";
                }
            }
        }
        //make sure the user input the owner name of the car
        if (!ownerName.matches("^[a-zA-Z]{1,15}$")){
            inform = inform + "Owner name should be only letters and under 15 letters." +"\n";
        }
        //park a car into the slot
        if (inform == ""){
            Car car = new Car(carRegisterID,type,ownerName);  //creat a new Car object and stored the new car information
            carPark.findASlot(slot).setCar(car);
            for (int i = 0; i < slotDisplay.size(); i++) {
                if (slotDisplay.get(i).getName().equals(slot)){
                    String description = carPark.listAllSlots().get(i).toString();
                    slotDisplay.get(i).setText(description);
                    //change the color of the slot button
                    //if the slot is for staff
                    if(slotDisplay.get(i).getName().contains("S")){
                        slotDisplay.get(i).setBackground(new Color(100,151,194));
                        panel4.revalidate();
                        panel4.repaint();
                    }
                    //if the slot is for visitor
                    else {
                        slotDisplay.get(i).setBackground(new Color(46,139,87));
                        panel5.revalidate();
                        panel5.repaint();
                    }
                }
            }
            inform = "The car has been parked successfully.";
        }
        return inform;
    }

    /**
     * find a car by the car register number input by the user
     * @param register  The car register number input by the user
     * @return           A message include error information or car information
     */
    public static String FindACar(String register){
        Boolean a = true;
        String inform = "";
        if (register.matches("^[A-Z]\\d{5}$")) {
            for (int i = 0; i < carPark.listAllSlots().size(); i++) {
                //if the car is in the car park
                if (carPark.listAllSlots().get(i).findCar() != null) {
                    //search the car register number which is input by the user from cars
                    if (carPark.listAllSlots().get(i).findCar().getRegNumber().equals(register)) {
                        inform = "The slot ID of the car is " + carPark.listAllSlots().get(i).getSlotNumber()+"\n";
                        inform = inform + "The owner of the car is " + carPark.listAllSlots().get(i).findCar().getOwner();
                        a = false;
                        break;
                    }
                }
            }
            //the car is not matched with cars in the car park
            if (a) {
                inform = "The car is not in the car park.";
            }
        }
        //if the input is not matched with the format of car register number
        else {
            inform = "The car register number should be started with a capital letter and followed by a five-digit number.";
        }
        return inform;
    }

    /**
     * remove a car by the car register number input by the user
     * @param register The car register number input by the user
     * @return         A message include error information or success remove information
     */
    public static String RemoveACar(String register){
        String inform = "";
        //check the format of the car register number
        if (!register.matches("^[A-Z]\\d{5}$")) {
            inform = "The car register number should be started with a capital letter and followed by a five-digit number.";
        } else {
            //check the car is in the car park or not, if the car is in the car park, remove it
            boolean judgment = true;  //set a boolean "judgment" as a mark to mark if the system find the car
            for (int i = 0; i < carPark.listAllSlots().size(); i++) {
                if (carPark.listAllSlots().get(i).findCar() != null) {
                    //search the car register number which is input by the user from cars
                    if (carPark.listAllSlots().get(i).findCar().getRegNumber().equals(register)) {
                        carPark.listAllSlots().get(i).removeCar(carPark.listAllSlots().get(i).findCar());
                        String description = carPark.listAllSlots().get(i).toString();
                        slotDisplay.get(i).setText(description);
                        //change the color of the slot button after the car has been removed
                        //if the slot is for staff
                        if (slotDisplay.get(i).getName().contains("S")){
                            slotDisplay.get(i).setBackground(new Color(176,196,222));
                            panel4.revalidate();
                            panel4.repaint();
                        }
                        //if the slot is for visitor
                        else {
                            slotDisplay.get(i).setBackground(new Color(211,218,196));
                            panel5.revalidate();
                            panel5.repaint();
                        }
                        inform = "The car has been removed successfully.";
                        judgment = false;
                        break;
                    }
                }
            }
            //if the car can not be found from the car park
            if (judgment) {
                inform = "The car is not in the car park.";
            }
        }
        return inform;
    }

    /**
     * Add a new slot to the car park input by the user
     * @param slotID   The new slot ID the user would like to add to the new slot
     * @param slotType The slot type which the user would like to add to the new slot
     * @return
     */
    public static String AddASlot(String slotID, String slotType){
        String inform = "";
        //if the input is not matched with the format of parking slot ID
        if ((slotType.equals("Staff") && !slotID.matches("^S\\d{3}$"))) {
            inform = "Please input a correct staff parking slot ID."+ "\n" + "A capital letter S with three numbers such as S001.";
        } else if ((slotType.equals("Visitor") && !slotID.matches("^V\\d{3}$"))) {
            inform = "Please input a correct visitor parking slot ID." + "\n" + "A capital letter V with three numbers such as V001.";
        }
        //check if the slot has already existed in parkingSlotsStaff arraylist
        else {
            for (int i = 0; i < carPark.listAllSlots().size(); i++) {
                //if the add slot ID is the same as what exist in the system
                if (carPark.listAllSlots().get(i).getSlotNumber().equals(slotID)) {
                    inform = "The staff slot has already exist. Please input another slot ID.";
                    break;
                }
            }
        }
        //add a new park slot to arraylist parkingSlots
        if (inform == "") {
            ps = new ParkingSlot(slotType, slotID);
            carPark.addASlot(ps);
            String description = carPark.listAllSlots().get(carPark.listAllSlots().size()-1).toString();
            JButton displayNew = new JButton(description);
            displayNew.setName(slotID);
            displayNew.addActionListener(ClickButton);
            //add new slot button to the frame
            //if the slot is for staff
            if(displayNew.getName().contains("S")){
                displayNew.setBackground(new Color(176,196,222));
                displayNew.setPreferredSize(new Dimension(575,HEIGHT));
                slotDisplay.add(displayNew);
                panel4.add(displayNew);
                panel4.revalidate();
                panel4.repaint();
            }
            //if the slot is for visitor
            else {
                displayNew.setBackground(new Color(211,218,196));
                displayNew.setPreferredSize(new Dimension(575,HEIGHT));
                slotDisplay.add(displayNew);
                panel5.add(displayNew);
                panel5.revalidate();
                panel5.repaint();
            }
            inform = "The slot has been successful added.";
        }
        return inform;
    }

    /**
     * Delete the slot by the slot ID input from the uer
     * @param slotNo  The slot ID the user would like to delete from the car park
     * @return
     */
    public static String DeleteASlot(String slotNo){
        Boolean a = true;
        String inform = "";

        //check the input slot format
        if (!slotNo.matches("^[S,V]\\d{3}$")) {
            inform = "The slot slot ID should be started with capital a letter S or V and followed by a three-digit number.";
        }
        else {
            for (int i = 0; i < carPark.listAllSlots().size(); i++) {
                //find the park slot by ID in staff slot list
                if (carPark.listAllSlots().get(i).getSlotNumber().equals(slotNo)) {
                    a = false;
                    // if the slot is occupied
                    if (carPark.listAllSlots().get(i).findCar() != null) {
                        inform = "There is a car in the slot, and the system cannot delete it.";
                    }
                    //if the slot is not occupied, remove the slot
                    else {
                        carPark.deleteASlot(carPark.listAllSlots().get(i));
                        inform = "The slot has been deleted.";
                        //remove the slot button from the frame
                        //if the slot is for staff
                        if(slotDisplay.get(i).getName().contains("S")){
                            panel4.remove(slotDisplay.get(i));
                            panel4.revalidate();
                            panel4.repaint();
                        }
                        //if the slot is for visitor
                        else {
                            panel5.remove(slotDisplay.get(i));
                            panel5.revalidate();
                            panel5.repaint();
                        }
                        slotDisplay.remove(i);
                    }
                    break;
                }
            }
            //if the slot cannot be found
            if (a) {
                inform = "The slot is not exist.";
            }
        }
        return inform;
    }

    /**
     * if the user choose to park a car into the slot, park a car to the slot and save the input from the user
     * @param position the position of the button of slot which the user clicked
     */
    public static void ClickParkCar(int position){
        String carRegNum;
        String ownerName;
        //ask user to input the car register number by an input dialog
        while (true){
            carRegNum = JOptionPane.showInputDialog(jFrame,"Please enter the car register number:", "Park a car",JOptionPane.INFORMATION_MESSAGE);
            String error="";
            if(carRegNum == null){
                return;
            }
            //check the car register number format is correct or not
            else if (!carRegNum.matches("^[A-Z]\\d{5}$")) {
                JOptionPane.showMessageDialog(jFrame,"The car register number should be started with a capital letter and followed by a five-digit number.","Error",JOptionPane.INFORMATION_MESSAGE);
                error = "The car register number should be started with a capital letter and followed by a five-digit number.";
            }
            //check if the car has already been parked in the car park
            else {
                for (int i = 0; i < carPark.listAllSlots().size(); i++) {
                    if(carPark.listAllSlots().get(i).findCar()!= null){
                        //compare the input register number of the car with the cars in the slots, if the car is existed, break out from the function
                        if(carPark.listAllSlots().get(i).findCar().getRegNumber().equals(carRegNum)){
                            JOptionPane.showMessageDialog(jFrame,"The car has already been parked in the car park.","Error",JOptionPane.INFORMATION_MESSAGE);
                            error = "The car has already been parked in the car park.";
                            break;
                        }
                    }
                }
            }
            //if the car register number is correct, break out from the while
            if (error == ""){
                break;
            }
        }
        //make sure the user input the owner name of the car
        while (true){
            //get the owner name of the car from user input
            ownerName = JOptionPane.showInputDialog(jFrame,"Please enter the car owner name:", "Park a car",JOptionPane.INFORMATION_MESSAGE);
            if (ownerName==null){
                return;
            }
            else if (!ownerName.matches("^[a-zA-Z]{1,15}$")){
                JOptionPane.showMessageDialog(jFrame,"Owner name should be only letters and under 15 letters.","Error",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                break;
            }
        }

        //creat a new Car object and stored the new car information
        Car car = new Car(carRegNum,carPark.listAllSlots().get(position).getSlotType(),ownerName);

        //park the car into the car park
        carPark.listAllSlots().get(position).setCar(car);
        //change the slot button type
        String description = carPark.listAllSlots().get(position).toString();
        slotDisplay.get(position).setText(description);
        //if the slot is for staff
        if(slotDisplay.get(position).getName().contains("S")){
            slotDisplay.get(position).setBackground(new Color(100,151,194));
            panel4.revalidate();
            panel4.repaint();
        }
        //if the slot is for visitor
        else {
            slotDisplay.get(position).setBackground(new Color(46,139,87));
            panel5.revalidate();
            panel5.repaint();
        }
    }

    /**
     * if the user choose to delete the slot, delete the slot which the user clicked
     * @param position the position of the button of slot which the user clicked
     */
    public static void ClickDeleteSlot(int position){
        //delete the slot
        carPark.deleteASlot(carPark.listAllSlots().get(position));
        //delete the slot button
        //if the slot is for staff
        if(slotDisplay.get(position).getName().contains("S")){
            panel4.remove(slotDisplay.get(position));
            panel4.revalidate();
            panel4.repaint();
        }
        //if the slot is for visitor
        else {
            panel5.remove(slotDisplay.get(position));
            panel5.revalidate();
            panel5.repaint();
        }
        slotDisplay.remove(position);
    }

    /**
     * if the user choose to remove a car from the slot, remove the car in the clicked slot
     * @param position the position of the button of slot which the user clicked
     */
    public static void ClickRemoveCar(int position){
        Object[] options = {"Remove car", "Cancel"};
        int a = JOptionPane.showOptionDialog(jFrame, "Do you want to remove the car?", "Remove car",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        // if the user would like to remove the car
        if (a == 0) {
            //remove the car
            carPark.listAllSlots().get(position).removeCar(carPark.listAllSlots().get(position).findCar());
            //remove the car from the slot button
            String description = carPark.listAllSlots().get(position).toString();
            slotDisplay.get(position).setText(description);
            //if the slot is for staff
            if (slotDisplay.get(position).getName().contains("S")) {
                slotDisplay.get(position).setBackground(new Color(176, 196, 222));
                panel4.revalidate();
                panel4.repaint();
            }
            //if the slot is for visitor
            else {
                slotDisplay.get(position).setBackground(new Color(211, 218, 196));
                panel5.revalidate();
                panel5.repaint();
            }
        }
        // the user would not like to remove the car
        else {
            return;
        }
    }

}


