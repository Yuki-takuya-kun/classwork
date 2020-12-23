图片放置位置 
	文件夹:resource、web/classes/img
	在代码中引用resource中的图片
	但只有同时放在img中才能正常显示

代码规范:
	{
	}
	每个方法下的下一个语句均进行一次缩进
	不同方法间要有适当的空行
	适当的注释
	调试方法，通过print查看某个方法是否被调用从而发现问题

设计思想
	lookForNeighborFood方法引用了Simulation类中定义的getNeighbors方法，该方法会寻找附近的生物(beings)，
并将其存入Vector容器中，lookForNeighborFood通过for循环逐个查找Vector容器中的对象并用instanceof方法查看其是否
为某个类的对象，最终通过强制转换获得其对象名

	swimIfPossible：该方法在生物饥饿时先判断生物附近是否有食物，有食物时其会游向食物并进食，同时使食物能量
减少或死去，在生物饱腹时，会用随机数随机获得数字，每个数字对应一个方向，从而实现随机游动。