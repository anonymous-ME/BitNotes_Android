package in.bitnotes.affan.bitnotes;

/**
 * Created by affan on 28/8/16.
 */
public class FeedItem {
    private String title;
    private String id;
    private String views;
    private String thumbnail;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }

    public void setViews(String views){
        this.views = views;
    }
    public String getViews(){
        return this.views;
    }

    public void setUrl(String url){this.url = url;}

    public String getUrl(){return this.url;}
}
