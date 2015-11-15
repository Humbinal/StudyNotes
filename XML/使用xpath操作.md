使用dom4j支持xpath的操作

1.在dom4j中使用xpath
	* 默认情况下不支持xpath，需要引入jaxen-*.jar包来支持
	* dom4j提供了两个方法，用来支持xpath
		* selectNodes(xpath表达式)
		* selectSingleNode(xpath表达式)
1.1得到所有item的值

		List <Node> list= document.selectNodes("//item");
        //遍历list
		for (Node node : list) {
			String s=node.getText();
			System.out.println(s);
		}



2.可以直接获取到某个元素

	1.第一种形式：
	/AAA/BBB/CCC:表示一层一层的，AAA下面的BBB下面的CCC

	2.第二种形式：
	//BBB:表示和这个名称相同，只要名称是BBB都得到，不管层级关系

	3.第三种形式（使用*号）
	/*：所有元素
