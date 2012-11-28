package com.sia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogInActivity extends Activity implements OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        
<<<<<<< HEAD
        retornarButton(R.id.btnIngresar).setOnClickListener(this);
=======
        retornarButton(R.id.btnIngresar).setOnClickListener((OnClickListener) this);
>>>>>>> 450c5dafcc03aa6aadb1e5b3903988cb3339421a
    }
    private Button retornarButton(int pIdButton)
	{
    	return (Button) findViewById(pIdButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_log_in, menu);
        return true;
    }

    @Override
	public void onClick(View v) 
	{
		Intent i = null;
		switch (v.getId()) 
		{
			case R.id.btnIngresar:
				i = new Intent(v.getContext(), MenuActivity.class);
                startActivityForResult(i, 0);
				break;
			default:
				break;
		}		
	}
    
}
