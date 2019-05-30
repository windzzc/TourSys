package Frame;

import Dao.RouteDao;
import Dao.RouteDaoImpl;
import Dao.VisitorDao;
import Dao.VisitorDaoImpl;
import Service.*;
import Utils.SessionUtil;
import Utils.UICommonUtils;
import mapper.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisitorFrame extends JFrame {
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JButton addButton;
    private JButton msgButton;
    private JPanel paneel1;
    private JTable table2;
    private JButton deletePlanButton;
    private JTable table3;
    private JButton updateButton;
    private RouteService routeService = new RouteServiceImpl();
    private RouteDao routeDao = new RouteDaoImpl();
    private VisitorService visitorService = new VisitorServiceImpl();
    private VisitorDao visitorDao = new VisitorDaoImpl();
    Visitor visitor =(Visitor) SessionUtil.user;
    int id = visitor.getVisitor_id();
    public void generateTable1() {
        String[] names = {"id", "title", "content", "ctime"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(routeService.getAll(), names);
        defaultTableModel.setColumnIdentifiers(names);
        table1.setModel(defaultTableModel);
    }
    public void generateTable2() {
        String[] names = {"id", "title", "content", "ctime"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(routeService.getPlan(id), names);
        defaultTableModel.setColumnIdentifiers(names);
        table2.setModel(defaultTableModel);
    }
    public void generateTable3() {
        String[] names = {"id", "name", "phone", "address", "about", "email"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(visitorService.getMe(id), names);
        defaultTableModel.setColumnIdentifiers(names);
        table3.setModel(defaultTableModel);
    }
    public VisitorFrame(){
        generateTable1();
        generateTable2();
        generateTable3();
        this.setContentPane(paneel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(this);
        this.setVisible(true);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(paneel1, "未选择");
                }
                int route_id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                routeDao.addPlan(id,route_id);
                generateTable2();
            }
        });
        deletePlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table2.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(paneel1, "未选择");
                }
                int route_id = Integer.parseInt(table2.getValueAt(row, 0).toString());
                routeDao.deletePlan(id);
                generateTable2();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = table3.getSelectedColumn();
                int row = table3.getSelectedRow();

                CellEditor ce = table3.getCellEditor(row, column);
                ce.stopCellEditing();
                DefaultTableModel dtm = (DefaultTableModel) table3.getModel();
                Object [] res= new Object[6];
                for(int i=0;i<6;i++){
                    res[i]=dtm.getValueAt(0,i);
                }
                System.out.println(res[1].toString()+res[2].toString()+res[3].toString()+res[4].toString()+res[5].toString()
                        );
                visitorDao.update(res[1].toString(),res[2].toString(),res[3].toString(),res[4].toString(),res[5].toString(),
                        Integer.parseInt(res[0].toString())
                        );
                generateTable3();

            }
        });

    }

    public static void main(String[] args) {

    }
}
