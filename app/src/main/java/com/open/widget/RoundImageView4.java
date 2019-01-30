package com.open.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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


public class RoundImageView4 extends ImageView {



	private final int DEFAULT_RADIUS=10;
	protected Path clipPath = new Path();
	protected RectF mRectF = new RectF();
	private int x_radius;
	private int y_radius;

	public RoundImageView4(Context context) {
		super(context);
	}

	public RoundImageView4(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundImageView4(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
		x_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_x_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
		y_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_y_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
		a.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mRectF.set(0,0,getWidth(),getHeight());
		clipPath.reset();
		clipPath.addRoundRect(mRectF, x_radius, y_radius, Path.Direction.CCW);
		canvas.clipPath(clipPath);
		super.onDraw(canvas);
	}
}
