import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LogInPage extends AppFrame implements ActionListener {
    final JComboBox<String> positionComboBox;
    JButton loginBtn;
    AppFrame frame;
    final JPasswordField pfPassword;
    LogInPage(){
        frame = new AppFrame(); //Create our app frame
        frame.setLayout(new CardLayout());


        //Class for panel - inherits the main frame
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //class for position picker - inherits the panel
        frame.add(panel);
        JLabel workPosition = new JLabel("Pick position");
        workPosition.setAlignmentX(Component.CENTER_ALIGNMENT);
        workPosition.setVisible(true);
        panel.add(workPosition);

        String[] choices = {"Executive director", "Business Development Manager", "Project Manager Tech", "Project Manager Logistics"};
        positionComboBox = new JComboBox<String>(choices);

        positionComboBox.setMaximumSize(positionComboBox.getPreferredSize());
        positionComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        positionComboBox.setVisible(true);
        panel.add(positionComboBox);


        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        pfPassword = new JPasswordField();
        pfPassword.setSize(180, 30);
        pfPassword.setMaximumSize(new Dimension(180, 30));
        pfPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPassword.setLabelFor(pfPassword);


        JButton btnGet = new JButton("Display Password");
        btnGet.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        String password = new String(pfPassword.getPassword());
                        JOptionPane.showMessageDialog(frame,
                                "Password is " + password);
                    }
                });
        btnGet.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(btnGet);



        loginBtn = new JButton("Log In");
        loginBtn.addActionListener(this);

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loginBtn);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBtn){
            String edPass = "ivanivan";
            String bdPass = "ivan123";
            String pmtPass = "ivanski";
            String pmlPass = "ivanivanivan";
            String p = new String(pfPassword.getPassword());

            if(positionComboBox.getSelectedItem() == "Executive director"){
                if(p.equals(edPass)){
                    frame.dispose();
                    SelectionMenu selectStep = new SelectionMenu();
                }else{
                    JOptionPane.showMessageDialog(frame,
                            "Wrong password!");
                }

            } else if (positionComboBox.getSelectedItem() == "Business Development Manager") {
                if(p.equals(bdPass)){
                    frame.dispose();
                    SelectionMenu selectStep = new SelectionMenu();
                } else{
                    JOptionPane.showMessageDialog(frame,
                            "Wrong password!");
                }
            } else if (positionComboBox.getSelectedItem() == "Project Manager Tech") {
                if(p.equals(pmtPass)){
                    frame.dispose();
                    SelectionMenu selectStep = new SelectionMenu();
                }else{
                    JOptionPane.showMessageDialog(frame,
                            "Wrong password!");
                }

            } else if (positionComboBox.getSelectedItem() == "Project Manager Logistics") {
                if(p.equals(pmlPass)){
                    frame.dispose();
                    SelectionMenu selectStep = new SelectionMenu();

                }else{
                    JOptionPane.showMessageDialog(frame,
                            "Wrong password!");
                }

            }
        }
    }
}

