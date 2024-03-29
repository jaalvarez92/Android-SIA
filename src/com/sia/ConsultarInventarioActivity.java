package com.sia;

import java.util.LinkedList;

import com.controlador.RestAccess;
import com.entity.Articulo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
	ImageView imgViewArticulo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_inventario);
		
		//Inicializar componentes
		cmbProductos = retornarSpinner(R.id.cmProductos);
		txtNombreProducto = (TextView)findViewById(R.id.txtNombreProducto);
		txtStock = (TextView)findViewById(R.id.txtStock);
		txtDisponible = (TextView)findViewById(R.id.txtDisponible);
		imgViewArticulo = (ImageView)findViewById(R.id.imageViewArticulo);
		
		String[] nombresArt = getIntent().getStringArrayExtra("listaArticulos");
		//asiganar lista de productos al combox de productos
		LinkedList<String> spinner = new LinkedList<String>();
		for(int i= 0; i!= nombresArt.length; i++)
		{
			spinner.add(nombresArt[i]);
		}
		ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner);
		//A�adimos el layout para el men� y se lo damos al spinner
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbProductos.setAdapter(spinner_adapter);
		cmbProductos.setOnItemSelectedListener(new Function());
		
	}
	private Spinner retornarSpinner(int pidSpinner)
	{
    	return (Spinner) findViewById(pidSpinner);
    }
	private void mostrarInfoProducto(String pNombreProducto)
	{
		RestAccess rAccess = RestAccess.getInstance();				
		Articulo art = rAccess.getArticulo(pNombreProducto);
		txtNombreProducto.setText("Nombre: " + pNombreProducto);
		txtStock.setText("En Stock: " + art.cantStock);
		txtDisponible.setText("Disponible: " +  art.CantDisponible);
		//byte[] b = art.Imagen;
		//ByteArrayInputStream is = new ByteArrayInputStream(b);
		//Drawable drw = Drawable.createFromStream(is, "articleImage");
		//imgViewArticulo.setImageDrawable(drw);
		//txtInfoProducto.setText("Comprometida: " + pNombreProducto + "\n");
		//txtInfoProducto.setText("En transito: " + pNombreProducto + "\n");
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
}
