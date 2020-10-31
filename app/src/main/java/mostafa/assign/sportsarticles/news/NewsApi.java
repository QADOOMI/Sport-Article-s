package mostafa.assign.sportsarticles.news;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import mostafa.assign.sportsarticles.list.model.ArticleResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    String API_KEY = "a679612d8beb4e70b7c3c46c2c0cffa2";

    @NonNull
    @GET("top-headlines")
    Single<ArticleResponse> getSportArticles(
            @Query("country") @NonNull String country
            , @Query("category") @NonNull String category
            , @Query("apiKey") String apiKey
    );
}
