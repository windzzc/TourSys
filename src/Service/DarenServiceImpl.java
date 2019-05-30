package Service;

import Dao.DarenDao;
import Dao.DarenDaoImpl;
import mapper.Daren;

import java.util.List;

public class DarenServiceImpl implements DarenService{
    private DarenDao darenDao = new DarenDaoImpl();
    @Override
    public boolean addDaren(String name, String pwd, String type) {
        if(name==""||pwd==""||type==""||darenDao.findByName(name)) return false;
        Daren daren = new Daren();
        daren.setName(name);
        daren.setPwd(pwd);
        daren.setType(type);
        return darenDao.add(daren);
    }

    @Override
    public boolean deleteDaren(int id) {
        if(!darenDao.findById(id)) return false;
        darenDao.delete(id);
        return true;
    }

    @Override
    public String[][] getAll() {
        List<Daren> list = darenDao.findAll();
        int size = list.size();
        String[][] res = new String[size][5];

        for(int i=0;i<size;i++){
           res[i][0]=String.valueOf(list.get(i).getDaren_id());
           res[i][1]=list.get(i).getName();
           res[i][2]=list.get(i).getType();
           res[i][3]=list.get(i).getPhone();
           res[i][4]=list.get(i).getAbout();
        }
        return res;
    }
}
