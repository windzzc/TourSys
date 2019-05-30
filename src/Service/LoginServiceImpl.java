package Service;

import Dao.DarenDao;
import Dao.DarenDaoImpl;
import Dao.VisitorDao;
import Dao.VisitorDaoImpl;
import Frame.DarenFrame;
import Utils.SessionUtil;
import mapper.Daren;
import mapper.Visitor;

public class LoginServiceImpl implements LoginService {
    private DarenDao darenDao= new DarenDaoImpl();
    private VisitorDao visitorDao = new VisitorDaoImpl();
    @Override
    public int isLogin(String name, String password) {
        if(name==null||password==null){
            return -1;
        }

        Daren daren = darenDao.findByNamePwd(name,password);
        Visitor visitor = visitorDao.findByNamePwd(name,password);
        if(daren!=null&&daren.getRole()==1) {
            SessionUtil.user=daren;
            return 1;
        }
        else if(visitor!=null&&visitor.getRole()==2){
            SessionUtil.user=visitor;
            return 2;

        }
        else if(name.equals("admin")&&password.equals("admin")){
            return 0;
        }

        return -1;
    }

}
