package com.easyapp.file;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * 建立檔案
 * 建立資料夾
 *
 */
public class FileTool {

    public static File CreateFile(File file, String filename) {
        String local_file = file.getAbsolutePath() + "/" + filename;
        File f = new File(local_file);
        try {
            if (!f.createNewFile()) {
                System.out.println("File already exists");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return f;
    }


    public static File CreateDir(String dir) {
        String local_file = Environment.getExternalStorageDirectory().getAbsolutePath() + dir;
        File f = new File(local_file);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }
}
