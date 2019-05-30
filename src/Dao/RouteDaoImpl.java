package Dao;

import Utils.JdbcUtil;
import mapper.Route;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    JdbcUtil jdbcUtil = new JdbcUtil();
    ResultSet rs1=null;
    ResultSet rs2=null;
    ResultSet rs3=null;
    ResultSet rs4=null;
    @Override
    public boolean delete(int id) {
        return jdbcUtil.update("delete from route where route_id =?",id);
    }

    @Override
    public boolean add(Route route) {
        return jdbcUtil.update("insert into route(title,content,ctime,daren_id) value(?,?,?,?)",route.getTitle(),route.getContent(),route.getCtime(),route.getDaren_id());
    }

    @Override
    public boolean addPlan(int vId, int rId) {
        return jdbcUtil.update("insert into visitor_route(visitor_id,route_id) values(?,?)",vId,rId);
    }

    @Override
    public List<Route> findAll() {
        rs1= jdbcUtil.query("select * from route");
        List<Route> routes = new ArrayList<>();
        try{
            while(rs1.next()){
                Route route = new Route();
                route.setRoute_id(rs1.getInt(1));
                route.setTitle(rs1.getString(2));
                route.setContent(rs1.getString(3));
                route.setCtime(rs1.getString(4));
                routes.add(route);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return routes;
    }

    @Override
    public boolean findById(int id) {
        rs2 = jdbcUtil.query("select title from route where route_id=?",id);
        try {
            if(rs2.next()){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return false;
    }

    @Override
    public List<Route> findMine(int id) {
        rs4 = jdbcUtil.query("select route.route_id,title,content,ctime from route,daren " +
                "where route.daren_id=daren.daren_id and daren.daren_id=?",id);
        List<Route> routes = new ArrayList<>();
        try{
            while(rs4.next()){
                Route route = new Route();
                route.setRoute_id(rs4.getInt(1));
                route.setTitle(rs4.getString(2));
                route.setContent(rs4.getString(3));
                route.setCtime(rs4.getString(4));
                routes.add(route);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return routes;
    }

    @Override
    public List<Route> findPlan(int id) {

        rs3 = jdbcUtil.query("select route.route_id,title,content,ctime from route,visitor_route " +
                "where route.route_id=visitor_route.route_id and visitor_route.visitor_id=?",id);
        List<Route> routes = new ArrayList<>();
        try{
            while(rs3.next()){
                Route route = new Route();
                route.setRoute_id(rs3.getInt(1));
                route.setTitle(rs3.getString(2));
                route.setContent(rs3.getString(3));
                route.setCtime(rs3.getString(4));
                routes.add(route);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return routes;
    }

    @Override
    public boolean deletePlan(int id) {
        return jdbcUtil.update("delete from visitor_route where route_id=?",id);
    }
}
