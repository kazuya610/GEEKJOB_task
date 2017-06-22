/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author takahashi
 */
public class HtmlSpecialchars {
    public String h(String str) {
        String ret_val = new String(str);

        String[] escape = {"&", "<", ">", "\"", "\'", "\n", "\t"};
        String[] replace = {"&amp;", "&lt;", "&gt;", "&quot;", "&#39;", "<br>", "&#x0009;"};

        for (int i = 0; i < escape.length; i++) {
            ret_val = ret_val.replace(escape[i], replace[i]);
        }

        return ret_val;
    }
}
