package cn.itcast.demo1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

/**
 * 演示XStream
 * @author cxf
 *
 */
public class Demo1 {
	// 返回javabean集合
	public List<Province> getProinvceList() {
		Province p1 = new Province();
		p1.setName("北京");
		p1.addCity(new City("东城区", "DongChengQu"));
		p1.addCity(new City("昌平区", "ChangPingQu"));
		
		Province p2 = new Province();
		p2.setName("辽宁");
		p2.addCity(new City("沈阳", "shenYang"));
		p2.addCity(new City("葫芦岛", "huLuDao"));
		
		List<Province> provinceList = new ArrayList<Province>();
		provinceList.add(p1);
		provinceList.add(p2);
		
		return provinceList;
	}
	
	/**
<list> --> List类型显示list
  <cn.itcast.demo1.Province> --> javabean的类型为Province，它元素的名称为类的完整名
    <name>北京</name> --> javabean的属性名
    <cities> --> javabean的属性名
      <cn.itcast.demo1.City> --> 类名
        <name>东城区</name> --> 属性名
        <description>DongChengQu</description> --> 属性名
      </cn.itcast.demo1.City>
      <cn.itcast.demo1.City>
        <name>昌平区</name>
        <description>ChangPingQu</description>
      </cn.itcast.demo1.City>
    </cities>
  </cn.itcast.demo1.Province>
  <cn.itcast.demo1.Province>
    <name>辽宁</name>
    <cities>
      <cn.itcast.demo1.City>
        <name>沈阳</name>
        <description>shenYang</description>
      </cn.itcast.demo1.City>
      <cn.itcast.demo1.City>
        <name>葫芦岛</name>
        <description>huLuDao</description>
      </cn.itcast.demo1.City>
    </cities>
  </cn.itcast.demo1.Province>
</list>
	 */
	@Test
	public void fun1() {
		List<Province> proList = getProinvceList();
		/*
		 * 创建XStream对象
		 * 调用toXML把集合转换成xml字符串
		 */
		XStream xstream = new XStream();
		String s = xstream.toXML(proList);
		System.out.println(s);
	}
	
	/*
	 * 别名(alias)
	 * 希望：
	 * * 默认List类型对应<list>元素，希望让List类型对应<china>元素
	 * * 默认Province类型对应<cn.itcast.demo1.Province>，希望让它对应<province>
	 * * 默认City类型对应<cn.itcast.demo1.City>，希望它对应<city>元素
	 */
	/*
<china>
  <province>
    <name>北京</name>
    <cities>
      <city>
        <name>东城区</name>
        <description>DongChengQu</description>
      </city>
      <city>
        <name>昌平区</name>
        <description>ChangPingQu</description>
      </city>
    </cities>
  </province>
  <province>
    <name>辽宁</name>
    <cities>
      <city>
        <name>沈阳</name>
        <description>shenYang</description>
      </city>
      <city>
        <name>葫芦岛</name>
        <description>huLuDao</description>
      </city>
    </cities>
  </province>
</china>
	 */
	@Test
	public void fun2() {
		List<Province> proList = getProinvceList();
		XStream xstream = new XStream();
		/*
		 * 给指定的类型指定别名
		 */
		xstream.alias("china", List.class);//给List类型指定别名为china
		xstream.alias("province", Province.class);//给Province指定别名为province
		xstream.alias("city", City.class);//给City类型指定别名为city
		
		
		String s = xstream.toXML(proList);
		System.out.println(s);
	}
	
	/*
	 * 默认javabean的属性都会生成子元素，而现在希望生成元素的属性
	 */
/*
<china>
  <province name="北京">
    <cities>
      <city>
        <name>东城区</name>
        <description>DongChengQu</description>
      </city>
      <city>
        <name>昌平区</name>
        <description>ChangPingQu</description>
      </city>
    </cities>
  </province>
  <province name="辽宁">
    <cities>
      <city>
        <name>沈阳</name>
        <description>shenYang</description>
      </city>
      <city>
        <name>葫芦岛</name>
        <description>huLuDao</description>
      </city>
    </cities>
  </province>
 */
	@Test
	public void fun3() {
		List<Province> proList = getProinvceList();
		XStream xstream = new XStream();
		xstream.alias("china", List.class);//给List类型指定别名为china
		xstream.alias("province", Province.class);//给Province指定别名为province
		xstream.alias("city", City.class);//给City类型指定别名为city
		
		
		/*
		 * 把Province类型的name属性，生成<province>元素的属性
		 */
		xstream.useAttributeFor(Province.class, "name");
		
		
		String s = xstream.toXML(proList);
		System.out.println(s);		
	}

	
	/*
	 * 去除List类型的属性，只把list中的元素生成xml元素
	 */
/*
<china>
  <province name="北京">
    <city>
      <name>东城区</name>
      <description>DongChengQu</description>
    </city>
    <city>
      <name>昌平区</name>
      <description>ChangPingQu</description>
    </city>
  </province>
  <province name="辽宁">
    <city>
      <name>沈阳</name>
      <description>shenYang</description>
    </city>
    <city>
      <name>葫芦岛</name>
      <description>huLuDao</description>
    </city>
  </province>
</china>
 */
	@Test
	public void fun4() {
		List<Province> proList = getProinvceList();
		XStream xstream = new XStream();
		xstream.alias("china", List.class);//给List类型指定别名为china
		xstream.alias("province", Province.class);//给Province指定别名为province
		xstream.alias("city", City.class);//给City类型指定别名为city
		xstream.useAttributeFor(Province.class, "name");//把Province类型的name属性，生成<province>元素的属性
		
		
		/*
		 * 去除<cities>这样的Collection类型的属性
		 * 去除Provice类的名为cities的List类型的属性！
		 */
		xstream.addImplicitCollection(Province.class, "cities");
		
		
		String s = xstream.toXML(proList);
		System.out.println(s);		
	}
	
	/**
	 * 去除不想要的javabean属性
	 * 就是让某引起javabean属性，不生成对应的xml元素！
	 */
/*
<china>
  <province name="北京">
    <city>
      <name>东城区</name>
    </city>
    <city>
      <name>昌平区</name>
    </city>
  </province>
  <province name="辽宁">
    <city>
      <name>沈阳</name>
    </city>
    <city>
      <name>葫芦岛</name>
    </city>
  </province>
</china>
 */
	@Test
	public void fun5() {
		List<Province> proList = getProinvceList();
		XStream xstream = new XStream();
		xstream.alias("china", List.class);//给List类型指定别名为china
		xstream.alias("province", Province.class);//给Province指定别名为province
		xstream.alias("city", City.class);//给City类型指定别名为city
		xstream.useAttributeFor(Province.class, "name");//把Province类型的name属性，生成<province>元素的属性
		xstream.addImplicitCollection(Province.class, "cities");//去除Provice类的名为cities的List类型的属性！
		
		
		/*
		 * 让City类的，名为description属性不生成对应的xml元素
		 */
		xstream.omitField(City.class, "description");
		
		
		String s = xstream.toXML(proList);
		System.out.println(s);		
	}
}
