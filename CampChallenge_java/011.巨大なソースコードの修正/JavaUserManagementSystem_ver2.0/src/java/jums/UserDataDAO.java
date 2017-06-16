package jums;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author hayashi-s
 */
public class UserDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name,birthday,tell,type,comment,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            // メモ：DTOのgetBirthdayメソッド。
            st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            // メモ：System.currentTimeMillis()＝現在の協定世界時の取得
            // メモ：Timestampオブジェクトの生成→現在時刻をDB登録
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /**
     * データの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    // 【修正】検索結果は複数件になる可能性があるため、配列ArrayList型にする
    public ArrayList<UserDataDTO> search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            // メモ：search.jspはor検索
            // メモ：どの項目が入力されているかで、select文を追記している
            String sql = "SELECT * FROM user_t";
            boolean flag = false;
            ArrayList<Integer> SQL = new ArrayList<Integer>(); 
            
            if (!ud.getName().equals("")) {
                sql += " WHERE name like ?";
                flag = true;
                SQL.add(0);
            }
            if (ud.getBirthday()!=null) {
                if(!flag){
                    sql += " WHERE birthday like ?";
                    flag = true;
                }else{
                    sql += " AND birthday like ?";
                }
                 SQL.add(1);
            }
            if (ud.getType()!=0) {
                if(!flag){
                    sql += " WHERE type like ?";
                }else{
                    sql += " AND type like ?";
                }
                SQL.add(2);
            }
            st =  con.prepareStatement(sql);
            /*
            st.setString(1, "%"+ud.getName()+"%");
            st.setString(2, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
            st.setInt(3, ud.getType());
            */
            int i=1;
            while(i<=SQL.size()){
                switch(SQL.get(i-1)){
                    case 0:
                    st.setString(i, "%"+ud.getName()+"%");
                    break;
                    
                    case 1:
                    st.setString(i, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    break;
                    
                    case 2:
                    st.setInt(i, ud.getType());
                    break;
                }
                i++;
            }
            ResultSet rs = st.executeQuery();
            /*
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setBirthday(rs.getDate(3));
            resultUd.setTell(rs.getString(4));
            resultUd.setType(rs.getInt(5));
            resultUd.setComment(rs.getString(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            return resultUd;
            */
            
            ArrayList<UserDataDTO> resultList = new ArrayList();
          
            while(rs.next()){
                UserDataDTO resultUd = new UserDataDTO();
                resultUd.setUserID(rs.getInt(1));
                resultUd.setName(rs.getString(2));
                resultUd.setBirthday(rs.getDate(3));
                resultUd.setTell(rs.getString(4));
                resultUd.setType(rs.getInt(5));
                resultUd.setComment(rs.getString(6));
                resultUd.setNewDate(rs.getTimestamp(7));
                resultList.add(resultUd);
            }
            
            return resultList;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /**
     * ユーザーIDによる1件のデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public UserDataDTO searchByID(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM user_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setBirthday(rs.getDate(3));
            resultUd.setTell(rs.getString(4));
            resultUd.setType(rs.getInt(5));
            resultUd.setComment(rs.getString(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            
            System.out.println("searchByID completed");

            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    // 【修正・追記】データ削除用のメソッドを新規追加
    public void delete(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
           String sql="delete from user_t where userID=?";
           st =  con.prepareStatement(sql);
           st.setInt(1,ud.getUserID());
           st.executeUpdate();
           
           System.out.println("delete completed");
         
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    // 【修正・追記】データ更新用のメソッドを新規追加
    // メモ：引数１は旧データ、引数２は新データ。
    public UserDataDTO Update(UserDataDTO pud,UserDataDTO ud) throws SQLException{
        
        Connection con = null;
        PreparedStatement st = null;
        
        try{
            con = DBManager.getConnection();
            String sql = "update user_t set ";
            // メモ：
            boolean flag = false;
            // メモ：ArrayListに判定用の値を格納
            ArrayList<Integer> SQL = new ArrayList<Integer>(); 
            
            // メモ：未入力チェック
            if (ud.getName() != null && !ud.getName().equals("")) {
                sql += "name=?";
                SQL.add(0);
                flag = true;
            }
            
            if (ud.getBirthday() != null) {
                if(flag){
                    sql += ",";
                }
                 sql += "birthday=?";
                 SQL.add(1);
                 flag = true;
            }
            
            if (ud.getTell() != null && ud.getTell().equals("")) {
                if(flag){
                    sql += ",";
                }
                    sql += "tell=?";
                    SQL.add(2);
                    flag = true;
            }
            
            if(ud.getType() != 0){
                if(flag){
                    sql += ",";
                }
                    sql += "type=?";
                    SQL.add(3);
                    flag = true;
            }
            
            if(ud.getComment() != null && !ud.getComment().equals("")){
                if(flag){
                    sql += ",";
                }
                    sql += "comment=?";
                    SQL.add(4);
            }
            sql += ", newDate=?";
            sql += "where userID=";
            sql += String.valueOf(pud.getUserID());
            
            st = con.prepareStatement(sql);
            
            int i = 1;
            while(i <= SQL.size()){
                 switch(SQL.get(i-1)){
                    
                    case 0: st.setString(i,ud.getName());break;
                    case 1: st.setDate(i,new java.sql.Date(ud.getBirthday().getTime()));break;
                    case 2: st.setString(i, ud.getTell());break;
                    case 3: st.setInt(i,ud.getType());break;
                    case 4: st.setString(i, ud.getComment());break;
                }
                i += 1;
            }
            st.setTimestamp(SQL.size()+1,new Timestamp(System.currentTimeMillis()));
            
            st.executeUpdate();
            UserDataDTO resultud = new UserDataDTO();
            resultud = searchByID(pud);
            
            return resultud;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
}
