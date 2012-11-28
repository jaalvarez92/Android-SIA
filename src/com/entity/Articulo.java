package com.entity;

import java.util.ArrayList;

public class Articulo 
{
	public String Nombre;
	public double cantStock;
	public double CantDisponible;
	public double cantComprometida;
	public double cantTransito;
	public ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
	
	public Articulo()
	{
		
	}

}
