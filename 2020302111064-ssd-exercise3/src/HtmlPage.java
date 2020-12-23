public class HtmlPage {
    private String pageTitle = "";

    private String pageBody = "";

    private String bgImage = "";

    private String bgColor = "";
    //设置Head标签
    private String getHeader() {
        return "<HTML><HEAD><TITLE>" + this.pageTitle + "</TITLE></HEAD>";
    }

    private String getFooter() {
        return "</HTML>";
    }
    //设置网页标题
    public void setTitle(String paramString) {
        this.pageTitle = paramString;
    }
    //设置图片背景
    public void setBackgroundImage(String paramString) {
        this.bgImage = paramString;
    }
    //设置背景颜色
    public void setBackgroundColor(String paramString) {
        this.bgColor = paramString;
    }

    private String getBody() {
        return "<BODY background='" + this.bgImage + "' bgcolor='" + this.bgColor + "'>" + this.pageBody + "</BODY>";
    }

    private String getDoctype() {
        return "<!DOCTYPE HTML>";
    }
    //设置网页文本
    public void addText(String paramString) {
        this.pageBody += paramString;
    }
    //创建完整网页
    public String buildHtml() {
        return getDoctype() + getHeader() + getBody() + getFooter();
    }
}
