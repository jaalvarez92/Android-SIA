package com.sia;

import java.util.LinkedList;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ColocarPedidosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_colocar_pedidos);
		
		
		String[] nombresArt = getIntent().getStringArrayExtra("listaArticulos");
		//asiganar lista de productos al combox de productos
		LinkedList<String> spinner = new LinkedList<String>();
		for(int i= 0; i!= nombresArt.length; i++)
		{
			spinner.add(nombresArt[i]);
		}
		ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner);
		//Añadimos el layout para el menú y se lo damos al spinner
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner cmbProductos = (Spinner)findViewById(R.id.spinner1);
		cmbProductos.setAdapter(spinner_adapter);
		cmbProductos.setOnItemSelectedListener(new Function());
	
	}
	private class Function implements OnItemSelectedListener 
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int pos,long id)
		{
			String nombreProducto = parent.getItemAtPosition(pos).toString();
			//mostrarInfoProducto(nombreProducto);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0)
		{
			// TODO Auto-generated method stub

		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_colocar_pedidos, menu);
		return true;
	}

}
