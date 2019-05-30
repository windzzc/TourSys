package Dao;

import mapper.Route;

import java.util.List;

public interface RouteDao {
    boolean delete(int id);
    boolean add(Route route);
    boolean addPlan(int vId,int rId);
    List<Route> findAll();
    boolean findById(int id);
    List<Route> findMine(int id);
    List<Route> findPlan(int id);
    boolean deletePlan(int id);
}
