package com.sia;

import java.util.ArrayList;

import com.controlador.RestAccess;
import com.entity.Articulo;

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
		retornarButton(R.id.btnConsultarInventario).setOnClickListener((OnClickListener) this);
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
				Articulo arts = rAccess.getArticulos();
				System.out.println("ME CAGO EN TENCHAA");
				
				if(arts != null)
				{
					String[] nombresArt = new String[arts.listaArticulos.size()];
									
					for(int x = 0; x < arts.listaArticulos.size(); x++)
					{
						nombresArt[x] = arts.listaArticulos.get(x).Nombre;
						System.out.println("Articulo " + x + ": " + arts.listaArticulos.get(x).Nombre);
					}
					
					i = new Intent(this, ConsultarInventarioActivity.class);
					i.putExtra("listaArticulos", nombresArt);
					startActivityForResult(i, 0);					
					//startActivityForResult(i, R.id.menuprincipal_imgBtnEncuesta);
				}
				else
					System.out.println("ME CAGO EN TENCHAA NULLL");
				break;
			default:
				break;
		}		
	}
}
