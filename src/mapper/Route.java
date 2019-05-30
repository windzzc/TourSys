package mapper;

public class Route {
    private int route_id;
    private String title;
    private String content;
    private String ctime;
    private int daren_id;

    public int getDaren_id() {
        return daren_id;
    }

    public void setDaren_id(int daren_id) {
        this.daren_id = daren_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
