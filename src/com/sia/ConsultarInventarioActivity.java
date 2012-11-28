package com.sia;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultarInventarioActivity extends Activity 
{
	Spinner cmbProductos;
	TextView txtNombreProducto;
	TextView txtStock;
	TextView txtDisponible;
	TextView txtComprometido;
	TextView txtTransito;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_inventario);
		
		//Inicializar componentes
		cmbProductos = retornarSpinner(R.id.cmProductos);
		txtNombreProducto = (TextView)findViewById(R.id.txtNombreProducto);
		txtStock = (TextView)findViewById(R.id.txtStock);
		
		String[] nombresArt = getIntent().getStringArrayExtra("listaArticulos");
		//asiganar lista de productos al combox de productos
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.listaProductos,android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbProductos.setAdapter(arrayAdapter);
		cmbProductos.setOnItemSelectedListener(new Function());
		
		
	}
	private Spinner retornarSpinner(int pidSpinner)
	{
    	return (Spinner) findViewById(pidSpinner);
    }
	private void mostrarInfoProducto(String pNombreProducto)
	{
		txtNombreProducto.setText("Nombre: " + pNombreProducto);
		txtStock.setText("En Stock: " + pNombreProducto);
		//txtInfoProducto.setText("Disponible: " + pNombreProducto + "\n");
		//txtInfoProducto.setText("Comprometida: " + pNombreProducto + "\n");
		//txtInfoProducto.setText("En transito: " + pNombreProducto + "\n");
	}
	private class Function implements OnItemSelectedListener 
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int pos,long id)
		{
			String nombreProducto = parent.getItemAtPosition(pos).toString();
			mostrarInfoProducto(nombreProducto);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0)
		{
			// TODO Auto-generated method stub

		}

	}
}
