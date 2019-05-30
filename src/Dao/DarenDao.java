package Dao;

import mapper.Daren;

import java.util.List;

public interface DarenDao {
    boolean add(Daren daren);
    boolean update(Daren daren);
    boolean delete(int id);
    List<Daren> findAll();
    boolean findByName(String name);
    Daren findByNamePwd(String name,String pwd);
    boolean findById(int id);
}
