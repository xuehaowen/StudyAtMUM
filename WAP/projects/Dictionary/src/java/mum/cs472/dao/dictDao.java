/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.dao;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mum.cs472.entity.Definition;
import mum.cs472.tool.DbConnection;

/**
 *
 * @author toby_
 */
public class dictDao {

    private static dictDao instance = null;

    public static dictDao getInstance() {
        if (instance == null) {
            synchronized (dictDao.class) {
                instance = new dictDao();
            }
        }
        return instance;
    }

    public String lookUpByTerm(String term) {
        String word = term;
        System.err.printf("lookUpByTerm: %s \n", word);
        List<Definition> list = new ArrayList<>();
        if (word != null && word.length() > 0) {
            String sql = String.format("SELECT * FROM entries WHERE word = '%s'", word);
            System.err.println(sql);
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                conn = DbConnection.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
//                        System.err.println(rs.getString(1));
                        Definition de = new Definition();
                        de.setWord(rs.getString(1));
                        de.setType(rs.getString(2));
                        de.setDefinition(rs.getString(3));
                        list.add(de);
                    }
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                DbConnection.close(conn, pstmt, rs);
            }

        }
        System.err.printf("result size = %d \n", list.size());
//        String res = JSONArray.toJSONString(list);
//        System.err.println(res);
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    public List<Definition> lookUpByTermInList(String term) {
        String word = term;
        System.err.printf("lookUpByTermInList: %s \n", word);
        List<Definition> list = new ArrayList<>();
        if (word != null && word.length() > 0) {
            String sql = String.format("SELECT * FROM entries WHERE word = '%s'", word);
            System.err.println(sql);
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                conn = DbConnection.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
//                        System.err.println(rs.getString(1));
                        Definition de = new Definition();
                        de.setWord(rs.getString(1));
                        de.setType(rs.getString(2));
                        de.setDefinition(rs.getString(3));
                        list.add(de);
                    }
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                DbConnection.close(conn, pstmt, rs);
            }

        }
        System.err.printf("result size = %d \n", list.size());
        return list;
    }

    public static void main(String args[]) {
        System.out.println("mum.cs472.dao.dictDao.main()");
        System.out.println(dictDao.getInstance().lookUpByTerm("A"));
    }

}
