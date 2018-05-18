package com.easyapp.image.resize.operations;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

import com.easyapp.image.resize.utils.ImageDecoder;
import com.easyapp.image.resize.utils.ImageOrientation;

import java.io.File;
import java.io.IOException;


public class ImageResize {
	
	private static final String TAG = ImageResize.class.getName();
	
	public static Bitmap resize(Resources resources, int resId, int width, int height, ResizeMode mode) {
		Bitmap sampledSrcBitmap = ImageDecoder.decodeResource(resources, resId, width, height);
		if(sampledSrcBitmap == null) {
			return null;
		}
		
		return resize(sampledSrcBitmap, width, height, mode);
	}
	
	public static Bitmap resize(byte[] original, int width, int height, ResizeMode mode) {
		Bitmap sampledSrcBitmap = ImageDecoder.decodeByteArray(original, width, height);
		if(sampledSrcBitmap == null) {
			return null;
		}
		
		return resize(sampledSrcBitmap, width, height, mode);
	}
	
	public static Bitmap resize(File original, int width, int height, ResizeMode mode) {
		int degree = readPictureDegree(original.getAbsolutePath());

		Bitmap sampledSrcBitmap = ImageDecoder.decodeFile(original, width, height);
		Matrix matrix = new Matrix();
		matrix.postRotate(degree); /*翻转90度*/
		width = sampledSrcBitmap.getWidth();
		height =sampledSrcBitmap.getHeight();
		sampledSrcBitmap = Bitmap.createBitmap(sampledSrcBitmap, 0, 0, width, height, matrix, true);

		if(sampledSrcBitmap == null) {
			return null;
		}
		
		return resize(sampledSrcBitmap, width, height, mode);
	}
	
	protected static Bitmap resize(Bitmap sampledSrcBitmap, int width, int height, ResizeMode mode) {
		int sourceWidth = sampledSrcBitmap.getWidth();
		int sourceHeight = sampledSrcBitmap.getHeight();
		
		if(mode == null || mode == ResizeMode.AUTOMATIC) {
			mode = calculateResizeMode(sourceWidth, sourceHeight);
		}
		
		if(mode == ResizeMode.FIT_TO_WIDTH) {
			height = calculateHeight(sourceWidth, sourceHeight, width);
		} else if(mode == ResizeMode.FIT_TO_HEIGHT) {
			width = calculateWidth(sourceWidth, sourceHeight, height);
		}
		
		return Bitmap.createScaledBitmap(sampledSrcBitmap, width, height, true);
	}
	
	private static ResizeMode calculateResizeMode(int width, int height) {
		if(ImageOrientation.getOrientation(width, height) == ImageOrientation.LANDSCAPE) {
			return ResizeMode.FIT_TO_WIDTH;
		} else {
			return ResizeMode.FIT_TO_HEIGHT;
		}
	}
	
	private static int calculateWidth(int originalWidth, int originalHeight, int height) {
		return (int) Math.ceil(originalWidth / ((double) originalHeight/height));
	}

	private static int calculateHeight(int originalWidth, int originalHeight, int width) {
		return (int) Math.ceil(originalHeight / ((double) originalWidth/width));
	}

	/**
	 * 读取照片exif信息中的旋转角度
	 * @param path 照片路径
	 * @return角度
	 */
	public static int readPictureDegree(String path) {
		int degree  = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

}
