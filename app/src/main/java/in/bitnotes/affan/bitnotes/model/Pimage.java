
package in.bitnotes.affan.bitnotes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pimage {

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
