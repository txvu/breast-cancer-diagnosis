
import javax.swing.JFrame;

import Communication.*;
import View.LoginScreen;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(400, 200);
        var login = new LoginScreen(window);
        login.init();
        window.pack();
        window.setVisible(true);
        
        Server server = new Server();
        server.start(2000);
    }
}
