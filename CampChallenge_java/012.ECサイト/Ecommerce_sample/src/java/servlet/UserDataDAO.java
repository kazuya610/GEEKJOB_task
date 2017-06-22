package servlet;

import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author takahashi
 */
public class UserDataDAO{
    // メモ：今回のDB操作一覧
    // ①会員登録 insert
    // ②ログインチェック select
    // ③会員情報表示 select
    // ④会員情報更新 update
    // ⑤会員情報削除 delete
    // ⑥商品検索 select
    // ⑦商品購入 insert
    // ⑧購入履歴表示 select
    
    // ①会員登録 insert　現在時刻は挿入直前に生成
    //@param dto 対応したデータを保持しているJavaBeans
    //@throws SQLException 呼び出し元にcatchさせるためにスロー 
    public void insert(UserDataDTO dto) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name,password,mail,address,total,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, dto.getName());
            st.setString(2, dto.getPassword());
            st.setString(3, dto.getMail());
            st.setString(4, dto.getAddress());
            st.setInt(5, dto.getTotal());
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
    
    // ②ログインチェック select
    // メモ：ログイン結果を返すためにboolean型にする
    public boolean login(UserDataDTO dto) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        boolean login_result;
        
        try{
            con = DBManager.getConnection();
            st = con.prepareStatement("select * from user_t where mail = ? && password = ?");
            st.setString(1, dto.getMail());
            st.setString(2, dto.getPassword());
            
            ResultSet rs = st.executeQuery();
            System.out.println("login completed");
            
            if (rs.next()){
                login_result = true;
            }else{
                login_result = false;
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
        return login_result;
    }
    
    // ③会員情報表示 select
    public UserDataDTO Member(UserDataDTO dto) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM user_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, dto.getUserID());
            
            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));
            resultUd.setTotal(rs.getInt(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            resultUd.setDeleteFlg(rs.getInt(8));
            
            System.out.println("searchByID completed");
            
            // メモ：検索結果を返す
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
    
    // ④会員情報更新 update
    public void Update(UserDataDTO ud) throws SQLException{
        
        Connection con = null;
        PreparedStatement st = null;
        
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("UPDATE user_t SET name=?,password=?,mail=?,address=? WHERE userID=? && deleteFlg=0");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, ud.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    // ⑤会員情報削除 delete　deleteFlgを0から1にする。
    public void delete(int id) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("UPDATE user_t SET deleteFlg = 1 WHERE userID=? && deleteFlg=0");
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("update completed");
         
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    // ⑥商品検索
    // 【修正】検索結果は複数件になる可能性があるため、配列ArrayList型にする
    
    
    // ⑦商品購入 insert
    public void Buy(UserDataDTO dto) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            // メモ：データ操作１、購入情報の挿入
            st = con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?,?,?)");
            st.setInt(1, dto.getUserID());
            st.setString(2, dto.getItemCode());
            st.setInt(3, dto.getType());
            // メモ：System.currentTimeMillis()＝現在の協定世界時の取得
            // メモ：Timestampオブジェクトの生成→現在時刻をDB登録
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            
            // メモ：データ操作２、現在の総購入金額の取得
            st = con.prepareStatement("SELECT * FROM user_t WHERE userID = ?");
            st.setInt(1, dto.getUserID());
            ResultSet rs = st.executeQuery();
            rs.next();
            int total = rs.getInt("total");
            
            // メモ：データ操作３、総購入金額の更新
            st = con.prepareStatement("UPDATE user_t SET total = ? WHERE id = ?");
            // st.setInt(1, (total + ~~~)); // ★★今回の購入金額が必要★★
            st.setInt(2, dto.getUserID());
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
    
    // ⑧購入履歴表示 select
    
    
}