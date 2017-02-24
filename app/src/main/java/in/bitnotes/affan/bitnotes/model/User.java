
package in.bitnotes.affan.bitnotes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("phname")
    @Expose
    private String phname;

    public String getPhname() {
        return phname;
    }

    public void setPhname(String phname) {
        this.phname = phname;
    }

}
