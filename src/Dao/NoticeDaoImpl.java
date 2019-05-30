package Dao;

import Utils.JdbcUtil;
import mapper.Notice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao{
    private JdbcUtil jdbcUtil = new JdbcUtil();
    ResultSet rs1 = null;
    ResultSet rs2=null;
    @Override
    public boolean add(Notice notice) {
        return jdbcUtil.update("insert into notice(content,ctime,admin_id) value(?,?,?)",notice.getContent(),notice.getCtime(),1);

    }

    @Override
    public boolean delete(int id) {
        return jdbcUtil.update("delete from notice where notice_id =?",id);
    }

    @Override
    public List<Notice> findAll() {
        rs1= jdbcUtil.query("select * from notice");
        List<Notice> notices = new ArrayList<>();
        try{
            while(rs1.next()){
                Notice notice = new Notice();
                notice.setNotice_id(rs1.getInt(1));
                notice.setContent(rs1.getString(2));
                notice.setCtime(rs1.getString(3));
                notice.setAdmin_id(rs1.getInt(4));
                notices.add(notice);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtil.closeAll();
        }
        return notices;
    }

    @Override
    public boolean findById(int id) {
        rs2 = jdbcUtil.query("select content from notice where notice_id=?",id);
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
}
