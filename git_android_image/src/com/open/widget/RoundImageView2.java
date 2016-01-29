package com.open.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.open.image.R;
/**
 * 2.Path
 * 
 * @author Long
 *
 */
public class RoundImageView2 extends ImageView {

	private final int DEFAULT_RADIUS=10;
	
	private RectF mRoundRect=new RectF();
	private int x_radius;
	private int y_radius;
	private Path mPath = new Path();
	
	public RoundImageView2(Context context) {
		super(context);
		initObjectAttribute();
	}

	public RoundImageView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		initObjectAttribute();
		
		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
		x_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_x_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
		y_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_y_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
		a.recycle();
		
		
	}

	private void initObjectAttribute()
	{
//		if(getScaleType() != ScaleType.CENTER_CROP)
//		{
//			setScaleType(ScaleType.CENTER_CROP);
//		}
	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mRoundRect.set(0,0,getMeasuredWidth(),getMeasuredHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		Drawable mDrawable = getDrawable();
		if (mDrawable == null)
		{
			return;
		}
        
        if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
            return;     // nothing to draw (empty bounds)
        }
        
        canvas.save();
        mPath.reset();
        canvas.clipPath(mPath); // makes the clip empty
        mPath.addRoundRect(mRoundRect, x_radius, y_radius, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        
        Matrix mDrawMatrix = getImageMatrix();
        if (mDrawMatrix != null) {
            canvas.concat(mDrawMatrix);
        }
        mDrawable.draw(canvas);
        
        canvas.restore();
	}
	
	
}
