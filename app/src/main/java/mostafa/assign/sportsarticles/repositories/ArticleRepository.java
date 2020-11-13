package mostafa.assign.sportsarticles.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mostafa.assign.sportsarticles.list.model.Article;
import mostafa.assign.sportsarticles.list.model.ArticleResponse;
import mostafa.assign.sportsarticles.news.NewsApi;
import mostafa.assign.sportsarticles.news.RetrofitClient;

public class ArticleRepository {

    private static final String TAG = "ArticleRepository";

    private ArticleRepository() {
    }

    public interface INIT {
        ArticleRepository INSTANCE = new ArticleRepository();
    }

    public LiveData<ArticleResponse> getSportArticles(@NonNull String country, @NonNull String category) {
        return LiveDataReactiveStreams.fromPublisher(
                RetrofitClient.getRetrofit()
                        .create(NewsApi.class)
                        .getSportArticles(country, category, NewsApi.API_KEY)
                        .toFlowable()
                        .subscribeOn(Schedulers.io())
        );
    }
}
