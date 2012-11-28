package com.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.*;
import com.entity.Articulo;
import com.entity.RestObject;

public class RestAccess
{
	
	private RestAccess(){}

	public static synchronized RestAccess getInstance()
	{
		if (ref == null	)
			ref = new RestAccess();		
		return ref;
	}
	public Articulo getArticulos()
	{		
		HttpClient httpClient = new DefaultHttpClient();				
		HttpGet del = new HttpGet(url + "obtenerArticulos");		
		del.setHeader("Content-type","application/json");	
		Articulo respuesta = new Articulo();
	
		try
		{
		        HttpResponse resp = httpClient.execute(del);		      
		        String respStr = EntityUtils.toString(resp.getEntity());
		        //respStr = "{'Codigo':'120','Comentario':'Hechos en china','Comprometido':0,'Costo':0.0000,'Descripcion':'Lápices Mongol','Disponible':0,'Foto':null,'IdArticulo':1,'IdBodega':1,'IdEmpresa':0,'Precio':0.0000,'Solicitado':0,'Stock':19,'UnidadMedida':'unidades'}";
		        System.out.println("-->>>> "+ respStr);
		        JSONArray respJSON = new JSONArray(respStr);
		        
		        //String[] articulos = new String[respJSON.length()];
		 		        
		        for(int i=0; i<respJSON.length(); i++)
		        {
		        	System.out.println("III");
		        	JSONObject obj = respJSON.getJSONObject(i);
		        	Articulo nuevoArt = new Articulo();
		            
		            nuevoArt.Nombre = obj.getString("Descripcion");
		            //nuevoArt.cantStock = obj.getDouble("");
		           // nuevoArt.CantDisponible = obj.getDouble("");
		            nuevoArt.cantComprometida = obj.getDouble("Comprometido");
		           //nuevoArt.cantTransito = obj.getDouble("");
		            
		            respuesta.listaArticulos.add(nuevoArt);	            		        			 		         
		        }
		        System.out.println("j");
		        return respuesta;
		}
		catch(Exception ex)
		{			
		    System.out.println(ex.getMessage());
		    return null;
		}
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
	
	
	String url = "http://10.0.2.2/RestService/RestService.svc/";  
	private static RestAccess ref;

}
