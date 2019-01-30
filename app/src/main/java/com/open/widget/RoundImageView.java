package com.open.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.open.image.R;
/**
 * 1.BitmapShader
 * 
 * @author Long
 *
 */
public class RoundImageView extends ImageView {

	private final int DEFAULT_RADIUS=10;
	
	private BitmapShader mBitmapShader;
	private Paint mBitmapPaint = new Paint();
	private RectF mRoundRect=new RectF();
	private int x_radius;
	private int y_radius;
	
	public RoundImageView(Context context) {
		super(context);
		initObjectAttribute();
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		initObjectAttribute();
		
		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
		x_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_x_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
		y_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_y_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
		a.recycle();
		
		
	}

	private void initObjectAttribute()
	{
		mBitmapPaint.setAntiAlias(true);
//		if(getScaleType() != ScaleType.CENTER_CROP)
//		{
//			setScaleType(ScaleType.CENTER_CROP);
//		}
	}
	
	@Override
	public void setImageResource(int resId) {
		super.setImageResource(resId);
		createBitmapShader();
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(bm);
//		createBitmapShader();
	}

	@Override
	public void setImageDrawable(Drawable drawable) {
		super.setImageDrawable(drawable);
		createBitmapShader();
	}

	private void createBitmapShader()
	{
		mBitmapShader = null;
		Drawable mDrawable = getDrawable();
		if (null == mDrawable)
		{
			return;
		}
		
		if(mDrawable instanceof BitmapDrawable)
		{
			BitmapDrawable bd = (BitmapDrawable) mDrawable;
			Bitmap bitmap = bd.getBitmap();
			mBitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
		}
		else //if Drawable instanceof NinePathDrawable ，the code below is bad , because a view reference two bitmap ( one in NinePath , other is here) 
		{
			int w = mDrawable.getIntrinsicWidth();
			int h = mDrawable.getIntrinsicHeight();
			
			Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			mDrawable.setBounds(0, 0, w, h);
			mDrawable.draw(canvas);
			mBitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mRoundRect.set(0,0,getMeasuredWidth(),getMeasuredHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		Drawable mDrawable = getDrawable();
		if (null == mDrawable || null == mBitmapShader)
		{
			return;
		}
		Matrix  mDrawMatrix= getImageMatrix();
		if(null == mDrawMatrix)
		{
			mDrawMatrix =new Matrix();
		}
		mBitmapShader.setLocalMatrix(mDrawMatrix);
		mBitmapPaint.setShader(mBitmapShader);
		canvas.drawRoundRect(mRoundRect, x_radius, y_radius,mBitmapPaint);
	}
}
