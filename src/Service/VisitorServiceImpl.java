package Service;

import Dao.VisitorDao;
import Dao.VisitorDaoImpl;
import mapper.Visitor;

import java.util.List;

public class VisitorServiceImpl implements VisitorService {
    private VisitorDao visitorDao = new VisitorDaoImpl();
    @Override
    public boolean deleteVistor(int id) {

        if(!visitorDao.findById(id)) return false;
        visitorDao.deleteById(id);
        return true;
    }

    @Override
    public String[][] getMe(int id) {
        Visitor visitor = visitorDao.findMe(id);
        String[][] res = new String[1][6];
        res[0][0]=String.valueOf(visitor.getVisitor_id());
        res[0][1]=visitor.getName();
        res[0][2]=visitor.getPhone();
        res[0][3]=visitor.getAddress();
        res[0][4]=visitor.getAbout();
        res[0][5]=visitor.getEmail();
        return res;
    }

    @Override
    public String[][] getVistors() {
        List<Visitor> list = visitorDao.findAll();
        int size = list.size();
        String[][] res = new String[size][6];

        for(int i=0;i<size;i++){
            res[i][0]=String.valueOf(list.get(i).getVisitor_id());
            res[i][1]=list.get(i).getName();
            res[i][2]=list.get(i).getPhone();
            res[i][3]=list.get(i).getAddress();
            res[i][4]=list.get(i).getAbout();
            res[i][5]=list.get(i).getEmail();
        }
        return res;
    }
}
