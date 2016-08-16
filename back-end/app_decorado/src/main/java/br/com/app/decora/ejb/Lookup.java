package br.com.app.decora.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Lookup {

public static Map<String, Object> list = new HashMap<String, Object>();
	
	public static Object lookup(String nomeClasse) {
		Object bean = null;
		try {
			InitialContext in = new InitialContext();
			if (!list.containsKey(nomeClasse)) {
				bean = in.lookup("java:module/"+nomeClasse);
				list.put(nomeClasse, bean);
			} else {
				bean = list.get(nomeClasse);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
