package Utils;

import java.sql.*;

public class JdbcUtil {
    public static final String DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost/tour?useSSL=FALSE&serverTimezone=UTC";
    public static final String USER="root";
    public static final String PWD="0901chao";
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public JdbcUtil(){
        try {
            //加载驱动程序
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getCon(){
        try {
            con= DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 关闭资源
     */
    public void closeAll(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if(con!=null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public boolean update(String sql,Object... pras){
        int resu=0;
        con=getCon();
        try {
            ps=con.prepareStatement(sql);
            if(pras!=null){
                for(int i=0;i<pras.length;i++){
                    ps.setObject(i+1, pras[i]);
                }
            }
            resu=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeAll();
        }
        return resu>0;
    }
    public ResultSet query(String sql,Object... pras){
        con=getCon();
        try {
            ps=con.prepareStatement(sql);
            if(pras!=null){
                for(int i=0;i<pras.length;i++){
                    ps.setObject(i+1, pras[i]);
                }
            }
            rs=ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }
}
