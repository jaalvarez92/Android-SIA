package com.entity;


public class RestObject 
{
	String nombreParametro;
	Object parametro;
	
	public RestObject(String pNombreParametro, Object pParametro)
	{
		nombreParametro = pNombreParametro;
		parametro = pParametro;
	}
	
	public RestObject()
	{
		nombreParametro = "";
		parametro = null;
	}
	
	public String getNombreParametro()
	{
		return nombreParametro;
	}
	public void setNombreParametro(String _nombreParametro) 
	{
		this.nombreParametro = _nombreParametro;
	}
	public Object getParametro() 
	{
		return parametro;
	}
	public void setParametro(Object _parametro)
	{
		parametro = _parametro;
	}

}
