initialWorldFish.html    给Servlet发送Start simulation的信息,Servlet为MySimulation
MySimulation.html   给Servlet发送RedisplayForm的信息,Servlet为SimulationServlet

Catfish.java  使鱼随机游动，获取栏数和鱼的图片，定义与鱼有关的属性
HtmlAnchor.java 创建一个链接
HtmlImage.java 引用图片
HtmlTable.java 创建表单
Simulation.java 模拟鱼的游动
SimulationServlet.java 负责整合其它类并且响应网页的请求
SimulationView.java 负责创建网页界面
LivingBeing.java 处理鱼的相关属性
MySimulation.java 创建一个和initialWorldFish.html类似效果的界面