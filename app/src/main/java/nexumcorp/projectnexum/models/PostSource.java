package nexumcorp.projectnexum.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by blras on 22/03/2018.
 */

@IgnoreExtraProperties
public class PostSource {

    @SerializedName("_source")
    @Expose
    private Post post;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
