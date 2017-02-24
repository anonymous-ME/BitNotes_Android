
package in.bitnotes.affan.bitnotes.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Practical {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("aim")
    @Expose
    private String aim;
    @SerializedName("subject_id")
    @Expose
    private Long subjectId;
    @SerializedName("college_id")
    @Expose
    private Long collegeId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("user_id")
    @Expose
    private Long userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("cached_votes_total")
    @Expose
    private Long cachedVotesTotal;
    @SerializedName("cached_votes_score")
    @Expose
    private Long cachedVotesScore;
    @SerializedName("cached_votes_up")
    @Expose
    private Long cachedVotesUp;
    @SerializedName("cached_votes_down")
    @Expose
    private Long cachedVotesDown;
    @SerializedName("views")
    @Expose
    private Long views;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("pimages")
    @Expose
    private List<Pimage> pimages = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getCachedVotesTotal() {
        return cachedVotesTotal;
    }

    public void setCachedVotesTotal(Long cachedVotesTotal) {
        this.cachedVotesTotal = cachedVotesTotal;
    }

    public Long getCachedVotesScore() {
        return cachedVotesScore;
    }

    public void setCachedVotesScore(Long cachedVotesScore) {
        this.cachedVotesScore = cachedVotesScore;
    }

    public Long getCachedVotesUp() {
        return cachedVotesUp;
    }

    public void setCachedVotesUp(Long cachedVotesUp) {
        this.cachedVotesUp = cachedVotesUp;
    }

    public Long getCachedVotesDown() {
        return cachedVotesDown;
    }

    public void setCachedVotesDown(Long cachedVotesDown) {
        this.cachedVotesDown = cachedVotesDown;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Pimage> getPimages() {
        return pimages;
    }

    public void setPimages(List<Pimage> pimages) {
        this.pimages = pimages;
    }
    
    @Override
    public String toString() {
        return "Practical{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", aim='" + aim + '\'' +
            ", subjectId=" + subjectId +
            ", collegeId=" + collegeId +
            ", description='" + description + '\'' +
            ", userId=" + userId +
            ", createdAt='" + createdAt + '\'' +
            ", updatedAt='" + updatedAt + '\'' +
            ", cachedVotesTotal=" + cachedVotesTotal +
            ", cachedVotesScore=" + cachedVotesScore +
            ", cachedVotesUp=" + cachedVotesUp +
            ", cachedVotesDown=" + cachedVotesDown +
            ", views=" + views +
            ", user=" + user +
            ", pimages=" + pimages +
            '}';
    }
}
