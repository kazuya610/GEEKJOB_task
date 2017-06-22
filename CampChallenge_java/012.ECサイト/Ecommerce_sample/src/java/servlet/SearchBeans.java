/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import net.arnx.jsonic.JSON;

/**
 *
 * @author takahashi
 */
public class SearchBeans implements Serializable{
    private String name;
    private String imageUrl;
    private int price;
    private String itemCode;
    
    public SearchBeans(){
        
    };

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
    /**
     * YahooショッピングAPIから引数に指定した商品コードの情報を抽出し、
     * メンバ変数に抽出した値を設定するメソッド
     * @param itemCode YahooAPIで指定する商品コード。ユニークな値
     * @throws Exception 呼び出し元にcatchさせるためにスロー 
     */
    public boolean searchByJanCode(String itemCode) throws Exception {
        try {
            
            //json取得URL設定
            String itemCode4url = URLEncoder.encode(itemCode, "UTF-8");
            String url_str = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=" 
                    + Search_common.appid + "&itemcode=" + itemCode4url;
            
            //URLからjsonを受けとってFileバッファに渡す
            URL url = new URL(url_str);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            StringBuffer responseBuffer = new StringBuffer();

            //URLのjsonからテキストを読み込む処理
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                responseBuffer.append(line);
            }
            reader.close();
            
            //取得したjsonテキストを文字列に変換
            String jsonText = responseBuffer.toString();
            
            //jsonテキストをjsonic使ってパース
            Map<String, Map<String, Object>> json = JSON.decode(jsonText);
            
            
            //"Resultset"->"0"->"Result"以降のオブジェクトを保存
            Map<String, Object> result = 
                    ((Map<String, Object>)((Map<String, Map<String, Object>>)json.get("ResultSet").get("0")).get("Result").get("0"));
            
            String name = result.get("Name").toString();
            String imageUrl = ((Map<String, Object>)result.get("Image")).get("Small").toString();
            String price = ((Map<String, Object>) result.get("Price")).get("_value").toString();
            
            
            this.name = name;
            this.imageUrl = imageUrl;
            this.price = Integer.parseInt(price);
            
            return true;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
    }
}