package com.open.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.open.image.R;

public class CircularImageView4 extends ImageView {

	protected Path clipPath = new Path();
	protected RectF mRectF = new RectF();

	public CircularImageView4(Context context) {
		super(context);
	}

	public CircularImageView4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircularImageView4(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mRectF.set(0,0,getWidth(),getHeight());
		clipPath.reset();
		clipPath.addOval(mRectF,Path.Direction.CW);
		canvas.clipPath(clipPath);
		super.onDraw(canvas);
	}
}
