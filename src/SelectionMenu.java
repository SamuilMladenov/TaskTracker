import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionMenu {
    AppFrame frame;
    JButton addBtn;
    JPanel panel1;
    JButton editBtn;
    JButton delBtn;
    JButton logOutBtn;
    SelectionMenu(){
        frame = new AppFrame();
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        addBtn = new JButton("Add Task");
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AddTaskPanel addTaskPanel = new AddTaskPanel();

            }
        });
        editBtn = new JButton("Edit Task");
        editBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //add new window

            }
        });
        delBtn = new JButton("Delete Task");
        delBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //add new window

            }
        });
        logOutBtn = new JButton("Log Out");
        logOutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LogInPage afterLogOut = new LogInPage();

            }
        });
        panel1.add(addBtn);
        panel1.add(editBtn);
        panel1.add(delBtn);
        panel1.add(logOutBtn);
        frame.add(panel1);
        frame.setVisible(true);
    }
}
