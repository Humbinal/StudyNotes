package cn.itcast.demo1;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * 演示JSON-LIB小工具　
 * @author cxf
 *
 */
public class Demo1 {
	/*
	 * 当map来用
	 */
	@Test
	public void fun1() {
		JSONObject map = new JSONObject();
		map.put("name", "zhangSan");
		map.put("age", 23);
		map.put("sex", "male");
		
		String s = map.toString();
		System.out.println(s);
	}
	
	/*
	 * 当你已经有一个Person对象时，可以把Person转换成JSONObject对象
	 */
	@Test
	public void fun2() {
		Person p = new Person("liSi", 32, "female");
		// 把对象转换成JSONObject类型
		JSONObject map = JSONObject.fromObject(p);
		System.out.println(map.toString());
	}
	
	/**
	 * JSONArray
	 */
	@Test
	public void fun3() {
		Person p1 = new Person("zhangSan", 23, "male");
		Person p2 = new Person("liSi", 32, "female");
		
		JSONArray list = new JSONArray();
		list.add(p1);
		list.add(p2);
		
		System.out.println(list.toString());
	}
	
	/**
	 * 原来就有一个List，我们需要把List转换成JSONArray
	 */
	@Test
	public void fun4() {
		Person p1 = new Person("zhangSan", 23, "male");
		Person p2 = new Person("liSi", 32, "female");
		List<Person> list = new ArrayList<Person>();
		list.add(p1);
		list.add(p2);
		
		
		System.out.println(JSONArray.fromObject(list).toString());
	}
}
