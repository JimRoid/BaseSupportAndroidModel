package com.easyapp.baseproject.lib.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by easyapp_jim on 2015/11/4.
 */
public class CircleImageView extends ImageView {

    private Paint mPaint = null;

    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        CircleImageDrawable circle = new CircleImageDrawable(bitmap);
        this.setImageDrawable(circle);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    private class CircleImageDrawable extends Drawable {

        private final BitmapShader mBitmapShader;
        private int circleRadius;

        /**
         * Instantiates a new circle image drawable.
         *
         * @param bitmap the bitmap
         */
        public CircleImageDrawable(Bitmap bitmap) {
            mBitmapShader = new BitmapShader(bitmap,
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(mBitmapShader);
        }

        /* (non-Javadoc)
         * @see android.graphics.drawable.Drawable#onBoundsChange(android.graphics.Rect)
         */
        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            int width, height;
            width = bounds.width();
            height = bounds.height();
            if (width > 0 && height > 0) {
                if (width < height) {
                    circleRadius = width / 2;
                } else {
                    circleRadius = height / 2;
                }
            }
        }

        /* (non-Javadoc)
         * @see android.graphics.drawable.Drawable#draw(android.graphics.Canvas)
         */
        @Override
        public void draw(Canvas canvas) {
            if (circleRadius > 0) {
                canvas.drawCircle(getBounds().exactCenterX(), getBounds().exactCenterY(), circleRadius, mPaint);
            }
        }

        /* (non-Javadoc)
         * @see android.graphics.drawable.Drawable#getOpacity()
         */
        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

        /* (non-Javadoc)
         * @see android.graphics.drawable.Drawable#setAlpha(int)
         */
        @Override
        public void setAlpha(int alpha) {
            mPaint.setAlpha(alpha);
        }

        /* (non-Javadoc)
         * @see android.graphics.drawable.Drawable#setColorFilter(android.graphics.ColorFilter)
         */
        @Override
        public void setColorFilter(ColorFilter cf) {
            mPaint.setColorFilter(cf);
        }
    }
}

