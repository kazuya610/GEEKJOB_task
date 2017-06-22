package servlet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * フォームからの出入力されるデータを格納するBeansオブジェクト
 * @author takahashi
 */
public class UserData implements Serializable {
    private String name;
    private String mail;
    private String password;
    private String address;
    
    public UserData(){
        this.name = "";
        this.mail = "";
        this.password = "";
        this.address = "";
    }
    
    // メモ：InsertConfirm.javaでset
    // メモ：InsertConfirm.jspでget
    public String getName() {
        return name;
    }
    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){  // trim：先頭と最後の空白を省略
            this.name = "";
        }else{
            this.name = name;
        }
    }
    
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        if(mail.trim().length()==0){
            this.mail = "";
        }else{
            this.mail = mail;
        }
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if(password.trim().length()==0){
            this.password = "";
        }else{
            this.password = password;
        }
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        if(address.trim().length()==0){
            this.address = "";
        }else{
            this.address = address;
        }
    }
    
    // メモ：未入力チェック　エラーを出すため。
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        // メモ：変数nameが空だったら「name」というパラメータを配列chkListに格納する
        // メモ：もし正しく入力されていれば、chkListには何も格納されないので、戻り値chkListはnullになる。
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }
        
        return chkList;
    }
    
    // メモ：DB専用のパラメータに変換するためのメソッド。DTOに格納するときに呼び出す。
    // メモ：正確には生年月日をYYYY-MM-DD形式にするためのメソッド。
    // メモ：DTOのsetメソッドを使用してDTOに格納。
    public void Mapping(UserDataDTO dto){
        dto.setName(this.name);
        dto.setMail(this.mail);
        dto.setPassword(this.password);
        dto.setAddress(this.address);
                
    }
}