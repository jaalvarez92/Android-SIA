package com.sia;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> 450c5dafcc03aa6aadb1e5b3903988cb3339421a
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
<<<<<<< HEAD
		retornarButton(R.id.btnConsultarInventario).setOnClickListener(this);
		retornarButton(R.id.btnColocarPedidos).setOnClickListener(this);
		retornarButton(R.id.btnConsultas).setOnClickListener(this);
=======
		retornarButton(R.id.btnConsultarInventario).setOnClickListener((OnClickListener) this);
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
<<<<<<< HEAD
				Articulo[] arts = rAccess.getArticulos2();
				
				if(arts != null)
				{
					i = new Intent(this, ConsultarInventarioActivity.class);
					i.putExtra("listaArticulos", arts);
					startActivityForResult(i, 0);					
					//startActivityForResult(i, R.id.menuprincipal_imgBtnEncuesta);
				}
				break;
			case R.id.btnColocarPedidos:
				RestAccess rAccess2 = RestAccess.getInstance();				
				String[] arts2 = rAccess2.getArticulos();
				
				if(arts2 != null)
				{
					i = new Intent(this, ColocarPedidosActivity.class);
					i.putExtra("listaArticulos", arts2);
					startActivityForResult(i, 0);					
					//startActivityForResult(i, R.id.menuprincipal_imgBtnEncuesta);
				}
				break;
				//i = new Intent(this, ColocarPedidosActivity.class);
				//startActivityForResult(i, 0);		
				//break;
			//case R.id.btnConsultas:
				//i = new Intent(this, ConsultarInventarioActivity.class);
				//startActivityForResult(i, 0);		
				//break;
=======
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
>>>>>>> 450c5dafcc03aa6aadb1e5b3903988cb3339421a
			default:
				break;
		}		
	}
}
