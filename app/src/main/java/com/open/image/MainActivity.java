package com.open.image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * @author Long
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initView();
	}
	
	private void initView()
	{
		findViewById(R.id.default_imageView).setOnClickListener(listener);
		
		findViewById(R.id.round_imageView).setOnClickListener(listener);
		findViewById(R.id.round_imageView2).setOnClickListener(listener);
		findViewById(R.id.round_imageView3).setOnClickListener(listener);
		findViewById(R.id.round_imageView4).setOnClickListener(listener);
		
		findViewById(R.id.circular_imageView).setOnClickListener(listener);
		findViewById(R.id.circular_imageView2).setOnClickListener(listener);
		findViewById(R.id.circular_imageView3).setOnClickListener(listener);
		findViewById(R.id.circular_imageView4).setOnClickListener(listener);
		
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
				case R.id.default_imageView:
					startActivity(new Intent(getApplicationContext(), DefaultActivity.class));
					break;
					
					
				case R.id.round_imageView:
					startActivity(new Intent(getApplicationContext(), RoundImageActivity.class));
					break;
				case R.id.round_imageView2:
					startActivity(new Intent(getApplicationContext(), RoundImage2Activity.class));
					break;
				case R.id.round_imageView3:
					startActivity(new Intent(getApplicationContext(), RoundImage3Activity.class));
					break;
				case R.id.round_imageView4:
					startActivity(new Intent(getApplicationContext(), RoundImage4Activity.class));
					break;
					
				case R.id.circular_imageView:
					startActivity(new Intent(getApplicationContext(), CircularImageActivity.class));
					break;

				case R.id.circular_imageView2:
					startActivity(new Intent(getApplicationContext(), CircularImage2Activity.class));
					break;
				case R.id.circular_imageView3:
					startActivity(new Intent(getApplicationContext(), CircularImage3Activity.class));
					break;
				case R.id.circular_imageView4:
					startActivity(new Intent(getApplicationContext(), CircularImage4Activity.class));
					break;
				default:
					break;
			}
		}
	};

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) 
        {
        	android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
