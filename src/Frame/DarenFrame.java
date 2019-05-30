package Frame;

import Dao.DarenDao;
import Dao.DarenDaoImpl;
import Dao.RouteDao;
import Dao.RouteDaoImpl;
import Service.RouteService;
import Service.RouteServiceImpl;
import Utils.SessionUtil;
import Utils.UICommonUtils;
import mapper.Daren;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DarenFrame extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable table1;
    private JLabel nameLabel;
    private JTextField titleTextField;
    private JTextField contentTextField;
    private JButton subRouteButton;
    private JButton deleteRouteButton;
    private JTextField phoneTextField;
    private JTextField aboutTextField;
    private JButton updateButton;
    private JLabel dname;
    private JLabel typeLable;
    private JTable table2;
    private JButton updateButton1;

    Daren daren =(Daren) SessionUtil.user;
    int id = daren.getDaren_id();
    private RouteService routeService = new RouteServiceImpl();
//    private RouteDao routeDao = new RouteDaoImpl();
    private DarenDao darenDao = new DarenDaoImpl();
    public void generateTable1() {
        String[] names = {"id", "title", "content", "time"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(routeService.getMine(daren.getDaren_id()), names);
        defaultTableModel.setColumnIdentifiers(names);
        table1.setModel(defaultTableModel);
    }
    public void generateTable2() {
        String[] names = {"id", "title", "content", "ctime"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(routeService.getAll(), names);
        defaultTableModel.setColumnIdentifiers(names);
        table2.setModel(defaultTableModel);
    }

    public DarenFrame(){
        super("daren");
        generateTable1();
        generateTable2();
        System.out.println(daren.getDaren_id());
        nameLabel.setText(daren.getName());
        dname.setText(daren.getName());
        typeLable.setText(daren.getType());
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(this);
        this.setVisible(true);
        subRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String content = contentTextField.getText();
                if(title==null||content==null){
                    JOptionPane.showMessageDialog(panel1,"发布失败");
                }
                routeService.addRoute(title,content,id);
                generateTable1();
            }
        });
        deleteRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(panel1, "未选择");
                }
                int id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                //System.out.println(id);
                if (!routeService.deleteRoute(id)) {
                    JOptionPane.showMessageDialog(panel1, "未找到数据");
                }
                routeService.deleteRoute(id);
                generateTable1();
            }

        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneTextField.getText();
                String about = aboutTextField.getText();
                if(phone==null||about==null){
                    JOptionPane.showMessageDialog(panel1,"不能为空");
                }
                Daren daren = new Daren();
                daren.setPhone(phone);
                daren.setAbout(about);
                daren.setDaren_id(id);
                darenDao.update(daren);

            }
        });

    }
}
