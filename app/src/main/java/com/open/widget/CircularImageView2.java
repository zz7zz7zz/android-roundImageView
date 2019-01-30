package com.open.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
/**
 * 2.Path
 * 
 * @author Long
 *
 */
public class CircularImageView2 extends ImageView {

	private RectF mRoundRect=new RectF();
	private Path mPath = new Path();
	private PaintFlagsDrawFilter pfd=new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
	
	public CircularImageView2(Context context) {
		super(context);
		initObjectAttribute();
	}

	public CircularImageView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircularImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		initObjectAttribute();		
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
        canvas.setDrawFilter(pfd); 
        canvas.save();
        
        mPath.reset();
        canvas.clipPath(mPath); // makes the clip empty

        mPath.addCircle(getWidth()/2, getHeight()/2, Math.min(getWidth()/2, getHeight()/2), Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);

        
        Matrix mDrawMatrix = getImageMatrix();
        if (mDrawMatrix != null) {
            canvas.concat(mDrawMatrix);
        }
        mDrawable.draw(canvas);
        
        canvas.restore();
	}
}
