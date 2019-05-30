package Service;

public interface NoticeService {
    boolean addNotice(String content);
    boolean deleteNotice(int id);
    String[][] getAll();
}
