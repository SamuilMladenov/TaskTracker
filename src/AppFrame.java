import javax.swing.*;

public class AppFrame extends JFrame {
    AppFrame(){
        this.setTitle("Task Tracker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("BEST_Logo_Letterhead.png");
        this.setIconImage(image.getImage());
        this.dispose();
    }
}
