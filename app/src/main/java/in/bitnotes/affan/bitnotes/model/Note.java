package in.bitnotes.affan.bitnotes.model;

/**
 * Created by betterclever on 2/23/2017.
 */

    
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {
    
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
    @SerializedName("subject_id")
    @Expose
    private Integer subjectId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("cached_votes_total")
    @Expose
    private Integer cachedVotesTotal;
    @SerializedName("cached_votes_score")
    @Expose
    private Integer cachedVotesScore;
    @SerializedName("cached_votes_up")
    @Expose
    private Integer cachedVotesUp;
    @SerializedName("cached_votes_down")
    @Expose
    private Integer cachedVotesDown;
    @SerializedName("views")
    @Expose
    private Integer views;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getCollegeId() {
        return collegeId;
    }
    
    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }
    
    public Integer getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Integer getCachedVotesTotal() {
        return cachedVotesTotal;
    }
    
    public void setCachedVotesTotal(Integer cachedVotesTotal) {
        this.cachedVotesTotal = cachedVotesTotal;
    }
    
    public Integer getCachedVotesScore() {
        return cachedVotesScore;
    }
    
    public void setCachedVotesScore(Integer cachedVotesScore) {
        this.cachedVotesScore = cachedVotesScore;
    }
    
    public Integer getCachedVotesUp() {
        return cachedVotesUp;
    }
    
    public void setCachedVotesUp(Integer cachedVotesUp) {
        this.cachedVotesUp = cachedVotesUp;
    }
    
    public Integer getCachedVotesDown() {
        return cachedVotesDown;
    }
    
    public void setCachedVotesDown(Integer cachedVotesDown) {
        this.cachedVotesDown = cachedVotesDown;
    }
    
    public Integer getViews() {
        return views;
    }
    
    public void setViews(Integer views) {
        this.views = views;
    }
    
}
