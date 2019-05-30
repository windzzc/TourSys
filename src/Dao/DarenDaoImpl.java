package Dao;

import Utils.JdbcUtil;
import mapper.Daren;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DarenDaoImpl implements DarenDao {
    JdbcUtil jdbcUtil = new JdbcUtil();
    ResultSet rs1=null;
    ResultSet rs2= null;
    ResultSet rs3 = null;
    ResultSet rs4=null;
    @Override
    public boolean add(Daren daren) {
        return jdbcUtil.update("insert into daren(name,pwd,type,admin_id) value(?,?,?,?)",daren.getName(),daren.getPwd(),daren.getType(),1);

    }

    @Override
    public boolean update(Daren daren) {
        return  jdbcUtil.update("update daren set phone=?,about=? where daren_id =?",daren.getPhone(),daren.getAbout(),daren.getDaren_id());
    }

    @Override
    public boolean delete(int id) {
        return jdbcUtil.update("delete from daren where daren_id =?",id);
    }
    @Override
    public List<Daren> findAll() {
         rs1= jdbcUtil.query("select * from daren");
        List<Daren> darens = new ArrayList<>();
        try{
            while(rs1.next()){
                Daren daren = new Daren();
                daren.setDaren_id(rs1.getInt(1));
                daren.setAdmin_id(1);
                daren.setName(rs1.getString(2));
                daren.setPwd(rs1.getString(3));
                daren.setType(rs1.getString(4));
                daren.setPhone(rs1.getString(5));
                daren.setAbout(rs1.getString(6));
                darens.add(daren);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return darens;
    }

    @Override
    public boolean findByName(String name) {
        rs2 = jdbcUtil.query("select name from daren where name=?",name);
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
    public Daren findByNamePwd(String name, String pwd) {
        rs4 = jdbcUtil.query("select * from daren where name =? and pwd =?",name,pwd);
        Daren daren = new Daren();
        try {
           while(rs4.next()){
               daren.setDaren_id(rs4.getInt(1));
               daren.setAdmin_id(1);
               daren.setName(rs4.getString(2));
               daren.setPwd(rs4.getString(3));
               daren.setType(rs4.getString(4));
               daren.setPhone(rs4.getString(5));
               daren.setAbout(rs4.getString(6));
               daren.setRole(rs4.getInt(8));
           }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return daren;
    }

    @Override
    public boolean findById(int id) {
        rs3 = jdbcUtil.query("select name from daren where daren_id=?",id);
        try {
            if(rs3.next()){
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

}
