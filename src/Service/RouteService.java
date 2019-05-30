package Service;

public interface RouteService {
    boolean deleteRoute(int id);
    boolean addRoute(String title,String content,int id);
    String[][] getAll();
    String[][] getMine(int id);
    String[][] getPlan(int id);
}
