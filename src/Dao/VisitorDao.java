package Dao;

import mapper.Visitor;

import java.util.List;

public interface VisitorDao {
    boolean deleteById(int id);
    List<Visitor> findAll();
    boolean findById(int id);
    Visitor findMe(int id);
    Visitor findByNamePwd(String name,String pwd);
    boolean update(String name,String phone,String address,String about,String email,int id);
}
