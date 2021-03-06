package dao;

import db.DBUtil;
import model.Goddess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoddessDao {

    Connection conn = DBUtil.getConnection();

    public void addGoddess(Goddess g) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "" + "INSERT INTO imooc_goddess" +
                "(user_name,sex,age,birthday,email,mobile," +
                "create_user,create_date,update_user,update_date,isdel)" +
                "VALUES(" +
                "?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, g.getUser_name());
        ptmt.setInt(2, g.getSex());
        ptmt.setInt(3, g.getAge());
        ptmt.setDate(4, new Date(g.getBirthday().getTime()));
        ptmt.setString(5, g.getEmail());
        ptmt.setString(6, g.getMobile());
        ptmt.setString(7, g.getCreate_user());
        ptmt.setString(8, g.getUpdate_user());
        ptmt.setInt(9, g.getIsdel());
        ptmt.execute();
    }

    public void updateGoddess(Goddess g) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "" +
                " UPDATE imooc_goddess" +
                " SET user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?," +
                " update_user=?,update_date=current_date(),isdel=?" +
                " WHERE ID=? ";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, g.getUser_name());
        ptmt.setInt(2, g.getSex());
        ptmt.setInt(3, g.getAge());
        ptmt.setDate(4, new Date(g.getBirthday().getTime()));
        ptmt.setString(5, g.getEmail());
        ptmt.setString(6, g.getMobile());
        ptmt.setString(7, g.getUpdate_user());
        ptmt.setInt(8, g.getIsdel());
        ptmt.setInt(9, g.getId());
        ptmt.execute();
    }

    public void delGoddess(Integer id) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "" +
                " DELETE FROM imooc_goddess" +
                " WHERE ID=? ";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ptmt.execute();
    }

    public List<Goddess> query() throws Exception {
        Connection conn = DBUtil.getConnection();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, user_name,age FROM imooc_goddess");

        List<Goddess> gs = new ArrayList<Goddess>();
        Goddess g = null;
        while (rs.next()) {
            g = new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            gs.add(g);
        }
        return gs;
    }

    public Goddess get(Integer id) throws Exception {
        Goddess g = null;
        Connection conn = DBUtil.getConnection();
        String sql = "" +
                " SELECT  * FROM imooc_goddess" +
                " WHERE ID=? ";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            g = new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            g.setSex(rs.getInt("sex"));
            g.setBirthday(rs.getDate("birthday"));
            g.setEmail(rs.getString("email"));
            g.setMobile(rs.getString("mobile"));
            g.setCreate_date(rs.getDate("create_date"));
            g.setCreate_user(rs.getString("create_user"));
            g.setUpdate_date(rs.getDate("update_date"));
            g.setUpdate_user(rs.getString("update_user"));
            g.setIsdel(rs.getInt("isdel"));
        }
        return g;
    }

    public List<Goddess> query(String name, String mobile) throws Exception {
        List<Goddess> result = new ArrayList<Goddess>();

        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM imooc_goddess");
        sb.append(" where user_name like ? and mobile like ?");

        PreparedStatement ptmt = conn.prepareStatement(sb.toString());
        ptmt.setString(1, "%" + name + "%");
        ptmt.setString(2, "%" + mobile + "%");

        ResultSet rs = ptmt.executeQuery();
        Goddess g = null;
        while (rs.next()) {
            g = new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            g.setSex(rs.getInt("sex"));
            g.setBirthday(rs.getDate("birthday"));
            g.setEmail(rs.getString("email"));
            g.setMobile(rs.getString("mobile"));
            g.setCreate_date(rs.getDate("create_date"));
            g.setCreate_user(rs.getString("create_user"));
            g.setUpdate_date(rs.getDate("update_date"));
            g.setUpdate_user(rs.getString("update_user"));
            g.setIsdel(rs.getInt("isdel"));

            result.add(g);
        }
        return result;
    }

    public List<Goddess> query(List<Map<String, Object>> params) throws Exception {
        List<Goddess> result = new ArrayList<Goddess>();

        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM imooc_goddess where 1=1 ");
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                Map<String, Object> map = params.get(i);
                sb.append(" and " + map.get("name") + " " + map.get("rela") + " " + map.get("value"));
            }
        }
        PreparedStatement ptmt = conn.prepareStatement(sb.toString());
        System.out.println(sb.toString());

        ResultSet rs = ptmt.executeQuery();
        Goddess g = null;
        while (rs.next()) {
            g = new Goddess();
            g.setId(rs.getInt("id"));
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            g.setSex(rs.getInt("sex"));
            g.setBirthday(rs.getDate("birthday"));
            g.setEmail(rs.getString("email"));
            g.setMobile(rs.getString("mobile"));
            g.setCreate_date(rs.getDate("create_date"));
            g.setCreate_user(rs.getString("create_user"));
            g.setUpdate_date(rs.getDate("update_date"));
            g.setUpdate_user(rs.getString("update_user"));
            g.setIsdel(rs.getInt("isdel"));

            result.add(g);
        }
        return result;
    }
}
