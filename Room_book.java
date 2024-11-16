import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReservationSystemGUI {
    
    private JFrame frame;
    private JTextArea infoArea, billArea;
    private JTextField nameField, mobileField, checkinField, daysField, childrenField, adultsField, roomsField;
    private JComboBox<String> roomTypeCombo;

    public HotelReservationSystemGUI() {
        // Set up the main frame
        frame = new JFrame("Hotel Reservation System");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(new JLabel(":::: RRVS INTERNATIONAL :::::"));
        frame.add(headerPanel, BorderLayout.NORTH);

        // Information Area
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Welcome to RRVS International Hotel!\n" +
                         "Address: 5/4, Roja Garden Selvapuram, Coimbatore, TamilNadu, INDIA\n" +
                         "Mobile Booking: +919345286535\n" +
                         "Visit us at: http://www.RRVShotels.com\n" +
                         "Check-in and Room Booking Information...");
        frame.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        // Booking Form Panel
        JPanel bookingPanel = new JPanel(new GridLayout(8, 2));
        bookingPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        bookingPanel.add(nameField);
        
        bookingPanel.add(new JLabel("Mobile Number:"));
        mobileField = new JTextField();
        bookingPanel.add(mobileField);
        
        bookingPanel.add(new JLabel("Check-in Date:"));
        checkinField = new JTextField();
        bookingPanel.add(checkinField);
        
        bookingPanel.add(new JLabel("No. of Days:"));
        daysField = new JTextField();
        bookingPanel.add(daysField);
        
        bookingPanel.add(new JLabel("No. of Children:"));
        childrenField = new JTextField();
        bookingPanel.add(childrenField);
        
        bookingPanel.add(new JLabel("No. of Adults:"));
        adultsField = new JTextField();
        bookingPanel.add(adultsField);
        
        bookingPanel.add(new JLabel("No. of Rooms:"));
        roomsField = new JTextField();
        bookingPanel.add(roomsField);
        
        // Room Type Selection
        bookingPanel.add(new JLabel("Select Room Type:"));
        String[] roomTypes = {"Super Deluxe (Rs.4500)", "Deluxe (Rs.3800)", "AC Room (Rs.3000)", "Non-AC (Rs.1900)"};
        roomTypeCombo = new JComboBox<>(roomTypes);
        bookingPanel.add(roomTypeCombo);

        frame.add(bookingPanel, BorderLayout.SOUTH);

        // Buttons
        JButton bookButton = new JButton("Book Room");
        bookButton.addActionListener(new BookRoomAction());
        frame.add(bookButton, BorderLayout.EAST);

        // Bill Area
        billArea = new JTextArea();
        billArea.setEditable(false);
        frame.add(new JScrollPane(billArea), BorderLayout.WEST);

        // Finalize frame setup
        frame.setVisible(true);
    }

    private class BookRoomAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic to handle room booking and bill generation
            String name = nameField.getText();
            String mobile = mobileField.getText();
            String checkin = checkinField.getText();
            int days = Integer.parseInt(daysField.getText());
            int children = Integer.parseInt(childrenField.getText());
            int adults = Integer.parseInt(adultsField.getText());
            int rooms = Integer.parseInt(roomsField.getText());
            String selectedRoom = (String) roomTypeCombo.getSelectedItem();

            // Calculate amount (pseudo logic, adapt as needed)
            int amount = 0;
            switch (selectedRoom.split(" ")[0]) {
                case "Super": amount = 4500 * days * rooms; break;
                case "Deluxe": amount = 3800 * days * rooms; break;
                case "AC": amount = 3000 * days * rooms; break;
                case "Non-AC": amount = 1900 * days * rooms; break;
            }

            // Ask for payment method
            String paymentMethod = (String) JOptionPane.showInputDialog(frame,
                    "Select Payment Method:",
                    "Payment Method",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Cash", "Card"},
                    "Cash");

            if ("Cash".equals(paymentMethod)) {
                // If payment is cash
                JOptionPane.showMessageDialog(frame, "Room Booked! Thank you for booking with us.");
                billArea.setText("BILL\nName: " + name + "\nMobile: " + mobile + 
                                 "\nCheck-in: " + checkin + "\nDays: " + days +
                                 "\nRoom Type: " + selectedRoom + "\nTotal Amount: Rs." + amount);
            } else if ("Card".equals(paymentMethod)) {
                // If payment is by card
                String bankInfo = "Bank Name: XYZ Bank\nAccount No: 123456789\nIFSC Code: XYZ12345\nPlease proceed with your card payment.";
                JOptionPane.showMessageDialog(frame, bankInfo, "Bank Information", JOptionPane.INFORMATION_MESSAGE);
                billArea.setText("BILL\nName: " + name + "\nMobile: " + mobile + 
                                 "\nCheck-in: " + checkin + "\nDays: " + days +
                                 "\nRoom Type: " + selectedRoom + "\nTotal Amount: Rs." + amount);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelReservationSystemGUI::new);
    }
}