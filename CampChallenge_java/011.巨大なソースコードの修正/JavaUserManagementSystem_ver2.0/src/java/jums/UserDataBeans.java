package jums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * ページで入出力されるユーザー情報を持ちまわるJavaBeans。DTOと違い画面表示系への結びつきが強い
 * DTOへの変換メソッド、入力チェックリストを出力するメソッドも準備されている←ちょっと仕事しすぎかも
 * @author hayashi-s
 */
public class UserDataBeans implements Serializable{
    private String name;
    private int year;
    private int month;
    private int day;
    private String tell;
    private int type;
    private String comment;
    
    public UserDataBeans(){
        this.name = "";
        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.tell = "";
        this.type = 0;
        this.comment= "";
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

    public int getYear() {
        return year;
    }
    public void setYear(String year) {
        //初期選択状態の場合0をセット
        if(year.equals("")){
            this.year = 0;
        }else{
            this.year = Integer.parseInt(year);
        }
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(String month) {
        if(month.equals("")){
            this.month = 0;
        }else{
            this.month = Integer.parseInt(month);
        }
    }

    public int getDay() {
        return day;
    }
    public void setDay(String day) {
        if(day.equals("")){
            this.day = 0;
        }else{
            this.day = Integer.parseInt(day);
        }
    }

    public String getTell() {
        return tell;
    }
    public void setTell(String tell) {
        if(tell.trim().length()==0){
            this.tell = "";
        }else{
            this.tell = tell;
        }
    }

    public int getType() {
        return type;
    }
    public void setType(String type) {
        if(type == null){
            this.type = 0;
        }else{
            this.type = Integer.parseInt(type);
        }
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        if(comment.trim().length()==0){
            this.comment = "";
        }else{
            this.comment = comment;
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
        if(this.year == 0){
            chkList.add("year");
        }
        if(this.month == 0){
            chkList.add("month");
        }
        if(this.day == 0){
            chkList.add("day");
        }
        if(this.tell.equals("")){
            chkList.add("tell");
        }
        if(this.type == 0){
            chkList.add("type");
        }
        if(this.comment.equals("")){
            chkList.add("comment");
        }
        
        return chkList;
    }
    
    // メモ：DB専用のパラメータに変換するためのメソッド。DTOに格納するときに呼び出す。
    // メモ：正確には生年月日をYYYY-MM-DD形式にするためのメソッド。
    // メモ：DTOのsetメソッドを使用してDTOに格納。
    public void UD2DTOMapping(UserDataDTO udd){
        udd.setName(this.name);
        // メモ：最低でも1つは格納されている場合
        if(this.year != 0 || this.month != 0 || this.day != 0){
            // メモ：カレンダーのインスタンス生成（この時点で現在時刻が格納されている）
            Calendar birthday = Calendar.getInstance();
            // メモ：両方0、もしくは片方0　＝年だけが0じゃないときだけこのif文を通る
            if(this.month == 0 || this.day == 0){
                // メモ：●年1月1日
                birthday.set(this.year,0,1);
            }else{
                // メモ：すべて格納されている場合
                birthday.set(this.year,(this.month)-1,this.day);
            }
            // メモ：年が0の場合は現在時刻
            udd.setBirthday(birthday.getTime());
        }else{
        // メモ：3つとも0だった場合
            udd.setBirthday(null);
        }
        udd.setTell(this.tell);
        udd.setType(this.type);
        udd.setComment(this.comment);
                
    }
    
}
