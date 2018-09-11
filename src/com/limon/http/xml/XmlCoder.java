package com.limon.http.xml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class XmlCoder {
	/**
	 * xml编码
	 * @param obj
	 * @return
	 */
	public String encode(Object obj){
		StringBuilder buf = new StringBuilder();
		Class c = obj.getClass();
		XmlNode root = (XmlNode)c.getAnnotation(XmlNode.class);
		encode(obj,buf,root.name(),root.cname());
		return buf.toString();
	}
	
	private void encode(Object obj,StringBuilder buf,String name,String cname){
		if (obj == null){
			return;
		}
		if (obj instanceof String || obj instanceof Integer || obj instanceof Double ||
			obj instanceof Float || obj instanceof Byte || obj.getClass().isPrimitive()){
			if (cname.length() > 0){
				buf.append("<").append(cname).append(">");
				buf.append(obj);
				buf.append("</").append(cname).append(">");
			}else{
				buf.append(obj);
			}
		}else if (obj instanceof List){
			List list = (List)obj;
			for (Object o:list){
				XmlNode anno = (XmlNode)o.getClass().getAnnotation(XmlNode.class);
				if (anno == null){
					encode(o, buf, name, cname);
				}else{
					encode(o, buf, anno.name(), anno.cname());
				}
			}
		}else{
			buf.append("<").append(name).append(">");
			Class c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			if (fields != null){
				for (Field field:fields){
					XmlNode anno = (XmlNode)field.getAnnotation(XmlNode.class);
					if (anno == null){
						continue;
					}
					try{
						Method m = c.getDeclaredMethod(getGetMethodName(field.getName()), null);
						Object result = m.invoke(obj, null);
						buf.append("<").append(anno.name()).append(">");
						encode(result, buf, anno.name(), anno.cname());
						buf.append("</").append(anno.name()).append(">");
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			buf.append("</").append(name).append(">");
		}
	}
	
	/**
	 * xml 解码
	 * @param <T>
	 * @param xml
	 * @param c
	 * @return
	 */
	public <T> T decode(String xml,Class<T> c){
		T obj = null;
		if (xml == null){
			return null;
		}
		try{
			obj = c.newInstance();
			XmlNode anno = (XmlNode)c.getAnnotation(XmlNode.class);
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			decode(obj,root,"/" + anno.name());
		}catch(Exception e){
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}
	
	private void decode(Object obj,Element root,String path) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		Field[] fields = obj.getClass().getDeclaredFields();
		Class c = obj.getClass();
		if (fields == null || fields.length == 0){
			return;
		}
		for (Field field:fields){
			Class type = field.getType();
			String setMethodName = getSetMethodName(field.getName());
			XmlNode anno = (XmlNode)field.getAnnotation(XmlNode.class);
			if (anno == null){
				continue;
			}
			if(type.equals(List.class)){
				List list = new ArrayList();
				Method setMethod = c.getDeclaredMethod(setMethodName, type);
				setMethod.invoke(obj, list);
				Class childClass = getChildClass(field);
				if (childClass.equals(String.class)){
					List<Node> nodes = root.selectNodes(path + "/" + anno.name() + "/" + anno.cname());
					if (nodes != null){
						for (Node node:nodes){
							list.add(node.getText());
						}
					}
				}else if (childClass.equals(Integer.class)){
					List<Node> nodes = root.selectNodes(path + "/" + anno.name() + "/" + anno.cname());
					if (nodes != null){
						for (Node node:nodes){
							list.add(Integer.parseInt(node.getText()));
						}
					}
				}else{
					XmlNode childAnno = (XmlNode)childClass.getAnnotation(XmlNode.class);
					List<Node> nodes = root.selectNodes(path + "/" + anno.name() + "/" + childAnno.name());
					if (nodes != null){
						for (int i = 0; i < nodes.size(); ++i){
							Object childObj = childClass.newInstance();
							list.add(childObj);
							decode(childObj,root, path + "/" + anno.name() + "/" + childAnno.name() + "[" + (i + 1) + "]");
						}
					}
				}
			}else if (type.equals(String.class)){
				Method setMethod = c.getDeclaredMethod(setMethodName, type);
				Node node = root.selectSingleNode(path + "/" + anno.name());
				if (node != null){
					//setMethod.invoke(obj, node.getText());
					setMethod.invoke(obj,node.asXML().replace("<"+node.getName()+">","").replace("</"+node.getName()+">",""));
					if(node.asXML().replace("<"+node.getName()+">","").replace("</"+node.getName()+">","").equals("<"+node.getName()+"/>")){
						setMethod.invoke(obj,"");
					}
				}
			}else if (type.equals(Integer.class)){
				Method setMethod = c.getDeclaredMethod(setMethodName, type);
				Node node = root.selectSingleNode(path + "/" + anno.name());
				if (node != null){
					if(node.getText()==null||node.getText().equals("")){
						setMethod.invoke(obj, Integer.parseInt("0"));
					}else{
						setMethod.invoke(obj, Integer.parseInt(node.getText()));
					}
				}
			}else{
				Object childObj = type.newInstance();
				Method setMethod = c.getDeclaredMethod(setMethodName, type);
				setMethod.invoke(obj, childObj);
				decode(childObj,root, path + "/" + anno.name());
			}
		}
	}
	
	private String getGetMethodName(String name){
		String one = name.substring(0, 1);
		StringBuilder buf = new StringBuilder(name);
		buf.setCharAt(0, one.toUpperCase().charAt(0));
		return "get" + buf.toString();
	}
	
	private String getSetMethodName(String name){
		String one = name.substring(0, 1);
		StringBuilder buf = new StringBuilder(name);
		buf.setCharAt(0, one.toUpperCase().charAt(0));
		return "set" + buf.toString();
	}
	
	//获取泛型信息
	private Class getChildClass(Field field){
		Type t =  field.getGenericType();
		if (t instanceof ParameterizedType){
			ParameterizedType pt = (ParameterizedType)t;
			return (Class)pt.getActualTypeArguments()[0];
		}else{
			return null;
		}
	}
}
