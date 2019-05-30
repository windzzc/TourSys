package Service;

public interface DarenService {
    boolean addDaren(String name,String pwd,String type);
    boolean deleteDaren(int id);
    String[][] getAll();
}
