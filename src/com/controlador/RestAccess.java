package com.controlador;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.*;
import com.entity.Articulo;
import com.entity.RestObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RestAccess
{
	
	private RestAccess(){}

	public static synchronized RestAccess getInstance()
	{
		if (ref == null	)
			ref = new RestAccess();		
		return ref;
	}
	
	
	public String[] getArticulos()
	{	
			
		
		HttpClient httpClient = new DefaultHttpClient();				
		HttpGet del = new HttpGet(url + "obtenerArticulos2");
		del.setHeader("Content-type","application/json");
	
		try
		{
		        HttpResponse resp = httpClient.execute(del);	
		        String respStr = EntityUtils.toString(resp.getEntity());
		        System.out.println("-->>>> "+ respStr);
		        JSONArray respJSON = new JSONArray(respStr);
		        
		        String[] articulos = new String[respJSON.length()];
		 		        
		        for(int i=0; i<respJSON.length(); i++)
		        {
		        	articulos[i] = respJSON.getString(i);	            		        			 		         
		        }
		        return articulos;
		}
		catch(Exception ex)
		{			
		    System.out.println(ex.getMessage());
		    return new String[] {};
		}
		
	}
	public Articulo[] getArticulos2()
	{	
			
		HttpClient httpClient = new DefaultHttpClient();				
		HttpGet del = new HttpGet(url + "obtenerArticulos"); 
		del.setHeader("Content-type","application/json");	
		
		try
		{
		        HttpResponse resp = httpClient.execute(del);	
		        String respStr = EntityUtils.toString(resp.getEntity());
		        System.out.println("-->>>> "+ respStr);
		        JSONArray respJSON = new JSONArray(respStr);
		        
		        Articulo[] articulos = new Articulo[respJSON.length()];
		 		        
		        for(int i=0; i<respJSON.length(); i++)
		        {
		        	Articulo art = new Articulo();
		        	
		        	//art.Nombre = respJSON.getString("Codigo");
		        	//articulos[i] = respJSON.getString(i);
		            
		            //nuevoArt.Nombre = obj.getString("Descripcion");
		            //nuevoArt.cantStock = obj.getDouble("");
		           // nuevoArt.CantDisponible = obj.getDouble("");
		           //nuevoArt.cantComprometida = obj.getDouble("Comprometido");
		           //nuevoArt.cantTransito = obj.getDouble("");
		            
		            //respuesta.listaArticulos.add(nuevoArt);	            		        			 		         
		        }
		        return articulos;
		}
		catch(Exception ex)
		{			
		    System.out.println(ex.getMessage());
		    return null;
		}
		
	}
	
	
	public Articulo getArticulo(String pNombre) 
	{	
		try
		{
			
			HttpClient httpClient = new DefaultHttpClient();
					    
			pNombre=pNombre.replace(" " ,"-" );
			HttpGet del = new HttpGet(url + "obtenerArticuloXNombre/"+pNombre); 
			
			del.setHeader("Content-type","application/json");
			
			Articulo respuesta = new Articulo();
			/*
		        HttpResponse resp = httpClient.execute(del);	
		        
		        BufferedReader inStream = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));

				StringBuffer buffer = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = inStream.readLine()) != null)
				{
					buffer.append(line + NL);
				}
				inStream.close();

				String result = buffer.toString();
				*/
			HttpResponse resp = httpClient.execute(del);
		        HttpEntity ent = resp.getEntity();
		        String respStr = EntityUtils.toString(ent);
		        System.out.println("-->>>> "+ respStr);
		        String result = respStr;
		        JSONArray respJSON = new JSONArray(result);
		        
		 		        
		        for(int i=0; i<respJSON.length(); i++)
		        {
		            JSONObject obj = respJSON.getJSONObject(i);
		        	respuesta.Nombre = obj.getString("Descripcion");
		           respuesta.cantStock = obj.getDouble("Stock");
		           respuesta.CantDisponible = obj.getDouble("Disponible");
		           respuesta.cantComprometida = obj.getDouble("Comprometido");
		           //respuesta.Imagen =  getArreglo(obj.get("Foto"));
		        }
		        System.out.println("j");
		        return respuesta;
		}
		catch(Exception ex)
		{	
		    return new Articulo();
		}
		
		
	}
	
	
	public byte[] getArreglo(Object pFoto) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] bytes = new byte[] {};
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(pFoto);
		  bytes = bos.toByteArray();
		  out.close();
		  bos.close();
		} 
		catch(IOException e){
			return new byte[] {};
		}
		return bytes;
	}
		  
	
	public String getData(String pMethod, String pId)
	{
		HttpClient httpClient = new DefaultHttpClient();
		 
		HttpGet del =
		    new HttpGet(url + pMethod +"/" + pId);
		del.setHeader("content-type", "application/json");
		try
		{
	        HttpResponse resp = httpClient.execute(del);
	        String respStr = EntityUtils.toString(resp.getEntity());
	 
	        return respStr;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public String postData (String pMethod, Object pParametro) 
	{
		HttpClient httpClient = new DefaultHttpClient();
		 
		HttpPost post =
		    new HttpPost(url + pMethod);
		 
		post.setHeader("content-type", "application/json; charset=utf-8");
		try
		{
			Gson jsonParameters = new Gson();
			String jsonResult = jsonParameters.toJson(pParametro);
			
			StringEntity entity = new StringEntity(jsonResult,"UTF-8");
			post.setEntity(entity);
			
			HttpResponse resp = httpClient.execute(post);
			String respStr = EntityUtils.toString(resp.getEntity());
			
			return respStr;
		}
		catch(Exception ex)
		{
			return null;
		}
    }
	
	public String postDataRestObject(String pMethod, List<RestObject> pLstObjetos)
	{
		HttpClient httpClient = new DefaultHttpClient();
		 
		HttpPost post =
		    new HttpPost(url + pMethod);
		 
		post.setHeader("content-type", "application/json; charset=utf-8");
		try
		{
			JSONObject jsonSend = new JSONObject();
			for (RestObject restObject : pLstObjetos) 
			{
				jsonSend.put(restObject.getNombreParametro(), restObject.getParametro());
			}
			
			StringEntity entity = new StringEntity(jsonSend.toString(),"UTF-8");
			post.setEntity(entity);
			
			HttpResponse resp = httpClient.execute(post);
			String respStr = EntityUtils.toString(resp.getEntity());
			
			return respStr;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public String postData(String pMethod, RestObject pObjeto){
		HttpClient httpClient = new DefaultHttpClient();
		 
		HttpPost post =
		    new HttpPost(url + pMethod);
		 
		post.setHeader("content-type", "application/json; charset=utf-8");
		try{
			JSONObject jsonSend = new JSONObject();
			jsonSend.put(pObjeto.getNombreParametro(), pObjeto.getParametro());
			
			StringEntity entity = new StringEntity(jsonSend.toString(),"UTF-8");
			post.setEntity(entity);
			
			HttpResponse resp = httpClient.execute(post);
			String respStr = EntityUtils.toString(resp.getEntity());
			
			return respStr;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	
	String url = "http://10.0.2.2:1797/RestService.svc/";  
	private static RestAccess ref;

}
