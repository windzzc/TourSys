package Frame;

import Service.*;
import Utils.UICommonUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private JPanel adminPanel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JButton deleteButton;
    private JTextField nameTextField;
    private JTextField pwdTextField;
    private JTextField typeTextField;
    private JButton submitButton;
    private JTable table2;
    private JButton deleteVisitorButton;
    private JButton refreshVisitorButton;
    private JTable table3;
    private JButton deleteNoticeButton;
    private JButton refreshNoticeButton;
    private JTextField abcdefgTextField;
    private JButton subNoticeButton;
    private JTable table4;
    private JButton refreshRouteButton;
    private JButton deleteRouteButton;
    private JButton updateNoticeButton;
    private DarenService darenService = new DarenServiceImpl();
    private VisitorService visitorService = new VisitorServiceImpl();
    private NoticeService noticeService = new NoticeServiceImpl();
    private RouteService routeService = new RouteServiceImpl();

    public void generateTable1() {
        String[] names = {"id", "name", "type", "phone", "about"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(darenService.getAll(), names);
        defaultTableModel.setColumnIdentifiers(names);
        table1.setModel(defaultTableModel);
    }

    public void generateTable2() {
        String[] names = {"id", "name", "phone", "address", "about", "email"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(visitorService.getVistors(), names);
        defaultTableModel.setColumnIdentifiers(names);
        table2.setModel(defaultTableModel);
    }

    public void generateTable3() {
        String[] names = {"id", "content", "time"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(noticeService.getAll(), names);
        defaultTableModel.setColumnIdentifiers(names);
        table3.setModel(defaultTableModel);
    }

    public void generateTable4() {
        String[] names = {"id", "title", "content", "ctime"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(routeService.getAll(), names);
        defaultTableModel.setColumnIdentifiers(names);
        table4.setModel(defaultTableModel);
    }

    public AdminFrame() {
        super("Admin");

        generateTable1();
        generateTable2();
        generateTable3();
        generateTable4();
        //darenJpanel.add(new JScrollPane(darenTable));
        this.setContentPane(adminPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(this);
        this.setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText().trim();
                String pwd = pwdTextField.getText().trim();
                String type = typeTextField.getText().trim();
                if (!darenService.addDaren(name, pwd, type)) {
                    JOptionPane.showMessageDialog(adminPanel, "输入错误！");
                }
                darenService.addDaren(name, pwd, type);
                generateTable1();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(adminPanel, "未选择");
                }
                int id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                System.out.println(id);
                if (!darenService.deleteDaren(id)) {
                    JOptionPane.showMessageDialog(adminPanel, "未找到数据");
                }
                darenService.deleteDaren(id);
                generateTable1();
            }
        });
        deleteVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table2.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(adminPanel, "未选择");
                }
                int id = Integer.parseInt(table2.getValueAt(row, 0).toString());
                System.out.println(id);
                if (!visitorService.deleteVistor(id)) {
                    JOptionPane.showMessageDialog(adminPanel, "未找到数据");
                }
                visitorService.deleteVistor(id);
                generateTable2();
            }
        });
        refreshVisitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateTable2();
            }
        });
        subNoticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = abcdefgTextField.getText().trim();
                noticeService.addNotice(content);
            }
        });
        refreshNoticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateTable3();
            }
        });
        deleteNoticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table3.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(adminPanel, "未选择");
                }
                int id = Integer.parseInt(table3.getValueAt(row, 0).toString());
                System.out.println(id);
                if (!noticeService.deleteNotice(id)) {
                    JOptionPane.showMessageDialog(adminPanel, "未找到数据");
                }
                noticeService.deleteNotice(id);
                generateTable3();
            }
        });
        refreshRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateTable4();
            }
        });
        deleteRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table4.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(adminPanel, "未选择");
                }
                int id = Integer.parseInt(table4.getValueAt(row, 0).toString());
                System.out.println(id);
                if (!routeService.deleteRoute(id)) {
                    JOptionPane.showMessageDialog(adminPanel, "未找到数据");
                }
                routeService.deleteRoute(id);
                generateTable4();
            }
        });

    }

    public static void main(String[] args) {
        new AdminFrame();
    }
}
