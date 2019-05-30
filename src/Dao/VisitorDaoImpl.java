package Dao;

import Utils.JdbcUtil;
import mapper.Visitor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VisitorDaoImpl implements VisitorDao {
    JdbcUtil jdbcUtil = new JdbcUtil();
    ResultSet rs1=null;
    ResultSet rs2=null;
    ResultSet rs3=null;
    ResultSet rs4=null;

    @Override
    public boolean deleteById(int id) {
        return jdbcUtil.update("delete from visitor where visitor_id =?",id);
    }

    @Override
    public List<Visitor> findAll() {
        rs1= jdbcUtil.query("select * from visitor");
        List<Visitor> visitors = new ArrayList<>();
        try{
            while(rs1.next()){
                Visitor visitor = new Visitor();
                visitor.setVisitor_id(rs1.getInt(1));
                visitor.setName(rs1.getString(2));
                visitor.setPwd(rs1.getString(3));
                visitor.setPhone(rs1.getString(4));
                visitor.setAddress(rs1.getString(5));
                visitor.setAbout(rs1.getString(6));
                visitor.setEmail(rs1.getString(7));
                visitors.add(visitor);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return visitors;
    }

    @Override
    public boolean findById(int id) {
        rs2 = jdbcUtil.query("select name from visitor where visitor_id=?",id);
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
    public Visitor findMe(int id) {
        rs4= jdbcUtil.query("select * from visitor where visitor_id=?",id);
        Visitor visitor = new Visitor();
        try{
            while(rs4.next()){
                visitor.setVisitor_id(rs4.getInt(1));
                visitor.setName(rs4.getString(2));
                visitor.setPwd(rs4.getString(3));
                visitor.setPhone(rs4.getString(4));
                visitor.setAddress(rs4.getString(5));
                visitor.setAbout(rs4.getString(6));
                visitor.setEmail(rs4.getString(7));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return visitor;
    }

    @Override
    public Visitor findByNamePwd(String name, String pwd) {
        rs3 = jdbcUtil.query("select * from visitor where name=? and pwd=?",name,pwd);
        Visitor visitor = new Visitor();
        try{
            while(rs3.next()){
                visitor.setVisitor_id(rs3.getInt(1));
                visitor.setName(rs3.getString(2));
                visitor.setPwd(rs3.getString(3));
                visitor.setPhone(rs3.getString(4));
                visitor.setAddress(rs3.getString(5));
                visitor.setAbout(rs3.getString(6));
                visitor.setEmail(rs3.getString(7));
                visitor.setRole(rs3.getInt(8));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return visitor;
    }

    @Override
    public boolean update(String name,String phone,String address,String about,String email,int id) {
        return jdbcUtil.update("update visitor set name=?,phone=?,address=?,about=?,email=? where visitor_id=?",
                name,phone,address,about,email,id
                );
    }

}
