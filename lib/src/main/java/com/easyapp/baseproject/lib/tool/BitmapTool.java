package com.easyapp.baseproject.lib.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by easyapp_jim on 2015/10/22.
 */
public class BitmapTool {

    /**
     * 正式取得可使用的bitmap
     *
     * @param dir_path
     * @return
     */
    public static Bitmap getBitmapFromFilePath(String dir_path) {
        Bitmap bmp = null;
        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inJustDecodeBounds = true; //設定BitmapFactory.decodeStream不decode，只抓取原始圖片的長度和寬度
        BitmapFactory.decodeFile(dir_path, opt);//抓取原始圖片的長度和寬度

        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = computeSampleSize(opt, -1, 384 * 384); //計算適合的縮放大小，避免OutOfMenery
        opt.inJustDecodeBounds = false;//設定BitmapFactory.decodeStream需decodeFile
        bmp = BitmapFactory.decodeFile(dir_path, opt);
        if (bmp != null) {
            return bmp;
        } else {
            return null;
        }
    }

    /**
     * 計算壓縮比
     *
     * @param opts
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options opts, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(opts, minSideLength, maxNumOfPixels);

        int roundedSize;

        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    /**
     * 計算初始化的長寬
     *
     * @param opts
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeInitialSampleSize(BitmapFactory.Options opts, int minSideLength, int maxNumOfPixels) {
        double w = opts.outWidth;
        double h = opts.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (maxNumOfPixels == -1) ? 384 : (int) Math.min(Math.floor(w / minSideLength)
                , Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

}
