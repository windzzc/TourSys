package Service;

import Dao.RouteDao;
import Dao.RouteDaoImpl;
import mapper.Route;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    @Override
    public boolean deleteRoute(int id) {

        if(!routeDao.findById(id)) return false;
        routeDao.delete(id);
        return true;
    }

    @Override
    public boolean addRoute(String title, String content,int id) {
        if(title==null||content==null) return false;
        Route route = new Route();
        route.setTitle(title);
        route.setContent(content);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        route.setCtime(date);
        route.setDaren_id(id);
        return routeDao.add(route);
    }

    @Override
    public String[][] getAll() {
        List<Route> list = routeDao.findAll();
        int size = list.size();
        String[][] res = new String[size][4];
        for(int i=0;i<size;i++){
            res[i][0]=String.valueOf(list.get(i).getRoute_id());
            res[i][1]=list.get(i).getTitle();
            res[i][2]=list.get(i).getContent();
            res[i][3]=list.get(i).getCtime();
        }
        return res;
    }

    @Override
    public String[][] getMine(int id) {
        List<Route> list = routeDao.findMine(id);
        int size = list.size();
        String[][] res = new String[size][4];
        for(int i=0;i<size;i++){
            res[i][0]=String.valueOf(list.get(i).getRoute_id());
            res[i][1]=list.get(i).getTitle();
            res[i][2]=list.get(i).getContent();
            res[i][3]=list.get(i).getCtime();
        }
        return res;
    }

    @Override
    public String[][] getPlan(int id) {
        List<Route> list = routeDao.findPlan(id);
        int size = list.size();
        String[][] res = new String[size][4];
        for(int i=0;i<size;i++){
            res[i][0]=String.valueOf(list.get(i).getRoute_id());
            res[i][1]=list.get(i).getTitle();
            res[i][2]=list.get(i).getContent();
            res[i][3]=list.get(i).getCtime();
        }
        return res;
    }
}
