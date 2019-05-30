package Frame;

import Service.LoginService;
import Service.LoginServiceImpl;
import Utils.SessionUtil;
import Utils.UICommonUtils;
import mapper.Daren;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JPanel panel1;
    private JTextField nameTextField;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private LoginService loginService = new LoginServiceImpl();
    //Daren daren =(Daren) SessionUtil.user;

    public LoginFrame() {
        super("login");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(this);
        this.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(nameTextField.getText().trim() + new String(passwordPasswordField.getPassword()));
                int res = loginService.isLogin(nameTextField.getText().trim(), new String(passwordPasswordField.getPassword()));
                System.out.println(res);
                if (res == 1) {
                    new DarenFrame();
                }
                if (res == 0) {
                    new AdminFrame();
                }
                if (res == 2) {
                    System.out.println("visitor");
                    new VisitorFrame();
                }
                if (res == -1) {
                    JOptionPane.showMessageDialog(panel1, "输入有误");
                }

            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
