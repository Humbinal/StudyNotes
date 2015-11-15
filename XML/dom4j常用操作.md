1.得到document

	SAXReader reader = new SAXReader();
	Document document = reader.read("urls.xml");

document的父接口是Node
如果在document中找不到想用的方法，可以在Node中查找

2.获取根节点
使用getRootElement()方法，返回的是Element
Element也是一个接口，父接口是Node
Element和Node里的常用方法：
getParent():获取父节点
addElement：添加标签

	//获取根节点并打印
	Element root = document.getRootElement();
    System.out.println("Root: " + root.getName());
	