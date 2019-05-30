package Dao;

import mapper.Notice;

import java.util.List;

public interface NoticeDao {
    boolean add(Notice notice);
    boolean delete(int id);
    List<Notice> findAll();
    boolean findById(int id);
}
