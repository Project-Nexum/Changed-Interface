package nexumcorp.projectnexum.utility;

import java.util.Map;

import nexumcorp.projectnexum.models.HitsObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

/**
 * Created by blras on 22/03/2018.
 */

public interface ElasticSearchAPI {

    @GET("_search/")
    Call<HitsObject> search(
            @HeaderMap Map<String,String> headers,
            @Query("default_operator") String operator,  //1st query (prepends '?')
            @Query("q") String query //second query (prepends '&')
    );
}
