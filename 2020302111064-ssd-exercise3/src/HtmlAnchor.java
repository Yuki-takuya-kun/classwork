public class HtmlAnchor //定义类HtmlAnchor，创建一个链接
{
    private String displayText;

    private String targetLocation;//声明属性

    public HtmlAnchor(String paramString1, String paramString2)
    {
        this.displayText = paramString1;
        this.targetLocation = paramString2;//构造函数并赋值
    }

    public String buildHtml() {
        return "<A href='" + this.targetLocation + "'>" + this.displayText + "</A>";//创建一个链接
    }//声明方法
}
