package com.open.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 3.setXfermode
 * 
 * @author Administrator
 *
 */
public class CircularImageView3 extends ImageView {
	
	private Paint paint = new Paint();  
	private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
	private Bitmap maskBitmap=null;
	private RectF mRoundRect=new RectF();
	private int oldWidth;
	private int oldHeight;
	
	public CircularImageView3(Context context) {
		super(context);
		initObjectAttribute();
	}

	public CircularImageView3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircularImageView3(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		initObjectAttribute();		

	}

	private void initObjectAttribute()
	{
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);  
        paint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了   
        
//		if(getScaleType() != ScaleType.CENTER_CROP)
//		{
//			setScaleType(ScaleType.CENTER_CROP);
//		}
	}
	
	private void create_mask_bitmap(int w , int h)
	{
		if(null != maskBitmap && !maskBitmap.isRecycled())
		{
			maskBitmap.recycle();
			maskBitmap=null;
		}
		
		maskBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);  
        Canvas mCanvas = new Canvas(maskBitmap);  
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);    
        mPaint.setColor(Color.TRANSPARENT);  
        mCanvas.drawCircle(w/2, h/2, Math.min(w/2, h/2), paint);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mRoundRect.set(0,0,getMeasuredWidth(),getMeasuredHeight());
		
		if( oldWidth!=getMeasuredWidth() || oldHeight!=getMeasuredHeight() )
		{
			oldWidth = getMeasuredWidth() ;
			oldHeight = getMeasuredHeight();
			
			if(oldWidth > 0 && oldHeight >0)
			{
				create_mask_bitmap(oldWidth , oldHeight);
			}
		}
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Drawable mDrawable = getDrawable();
		if (mDrawable == null)
		{
			return;
		}
        
        if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
            return;     // nothing to draw (empty bounds)
        }
        
        if(null == maskBitmap)
        {
        	return;
        }
        
        int saveCount = canvas.getSaveCount();
        canvas.save();
        paint.setFilterBitmap(false);
        paint.setXfermode(mXfermode);
        canvas.drawBitmap(maskBitmap, null, mRoundRect, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
	}
}
