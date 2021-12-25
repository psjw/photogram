package com.psjw.photogram.util;

/**
 * packageName : com.psjw.photogram.util
 * fileName : Script
 * author : psjw
 * date : 2021-12-25
 * description :
 * ===========================================================
 * DATE              AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2021-12-25        psjw         최초 생성
 */
public class Script {
    public static String back(String msg){
        StringBuffer sf = new StringBuffer();
        sf.append("<script>");
        sf.append("alert('"+msg+"');");
        sf.append("history.back();");
        sf.append("</script>");
        return sf.toString();
    }
}
