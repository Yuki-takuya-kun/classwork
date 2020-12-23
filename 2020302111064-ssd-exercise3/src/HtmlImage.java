public class HtmlImage //创建一个类HtmlImage,插入图片
{
    private String imageName;

    private String alternateText;

    public HtmlImage(String paramString1, String paramString2) {
        this.imageName = paramString1;
        this.alternateText = paramString2;
    }

    public String buildHtml() {
        return "<IMG src='resource/img" + this.imageName + ".gif' alt='" + this.alternateText + "'>";//显示图片
    }
}
