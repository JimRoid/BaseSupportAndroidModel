package com.easyapp.lib.tool;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * base 64 加解密工具
 */
public class Base64Tool {

    /**
     * 對圖片作對圖片作base64
     *
     * @param filePath
     * @return
     */
    public static String encodeFileToBase64(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }// You can get an inputStream using any IO API
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        return "data:image/jpeg;base64," + Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 對base 64 進行解碼
     *
     * @param base64
     * @return
     */
    public static byte[] decodeBase64(String base64) {
        return Base64.decode(base64, Base64.DEFAULT);
    }
}
