package com.sia;

import com.controlador.RestAccess;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		retornarButton(R.id.btnConsultarInventario).setOnClickListener(this);
	}
	
	private Button retornarButton(int pIdButton)
	{
    	return (Button) findViewById(pIdButton);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}
	@Override
	public void onClick(View arg0) 
	{
		Intent i ;
		switch (arg0.getId()) 
		{
			case R.id.btnConsultarInventario:				
				RestAccess rAccess = RestAccess.getInstance();				
				String[] arts = rAccess.getArticulos();
				
				if(arts != null)
				{
					i = new Intent(this, ConsultarInventarioActivity.class);
					i.putExtra("listaArticulos", arts);
					startActivityForResult(i, 0);					
					//startActivityForResult(i, R.id.menuprincipal_imgBtnEncuesta);
				}
				break;
			default:
				break;
		}		
	}
}
