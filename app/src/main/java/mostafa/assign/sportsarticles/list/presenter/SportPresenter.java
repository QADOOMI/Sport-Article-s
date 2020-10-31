package mostafa.assign.sportsarticles.list.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import mostafa.assign.sportsarticles.controller.SportArticlesView;
import mostafa.assign.sportsarticles.list.model.ArticleResponse;
import mostafa.assign.sportsarticles.news.NewsApi;
import mostafa.assign.sportsarticles.news.RetrofitClient;

public class SportPresenter {

    private SportArticlesView view;
    private NewsApi newsApi;

    private static final String TAG = "SportPresenter";

    public SportPresenter(@NonNull Context context, @NonNull SportArticlesView view) {
        this.view = view;
        this.newsApi = RetrofitClient.getRetrofit().create(NewsApi.class);
    }

    public void getSportArticles(@NonNull String country, @NonNull String category) {
        view.onSubscribe(newsApi.getSportArticles(country, category, NewsApi.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((articleResponses, throwable) -> {
                    Log.e(TAG, "getSportArticles: " + articleResponses.getArticles().toString());
                    if (throwable == null
                            && articleResponses.getStatus().equals("ok")) {
                        view.onSportArticlesFetched(articleResponses.getArticles());
                        return;
                    }

                    view.onError(throwable);

                    String errorMsg;

                    if (articleResponses.getStatus().equals("apiKeyExhausted")) {
                        errorMsg = "Your API key has no more requests available.";

                    } else if (articleResponses.getStatus().equals("apiKeyDisabled")) {
                        errorMsg = "You have requested too many sources in a single request. Try splitting the request into 2 smaller requests.";

                    } else if (articleResponses.getStatus().equals("sourcesTooMany")) {
                        errorMsg = "Your API key has no more requests available.";

                    } else if (articleResponses.getStatus().equals("rateLimited")) {
                        errorMsg = "You have been rate limited. Back off for a while before trying the request again.";

                    } else if (articleResponses.getStatus().equals("parameterInvalid")) {
                        errorMsg = "You've included a parameter in your request which is currently not supported. Check the message property for more details.";

                    } else if (articleResponses.getStatus().equals("parametersMissing")) {
                        errorMsg = "Required parameters are missing from the request and it cannot be completed. Check the message property for more details.";

                    } else if (articleResponses.getStatus().equals("apiKeyMissing")) {
                        errorMsg = "Your API key is missing from the request. Append it to the request with one of these methods.";

                    } else if (articleResponses.getStatus().equals("apiKeyInvalid")) {
                        errorMsg = "Your API key hasn't been entered correctly. Double check it and try again.";

                    } else if (articleResponses.getStatus().equals("sourceDoesNotExist")) {
                        errorMsg = "You have requested a source which does not exist.";

                    } else {
                        errorMsg = "This shouldn't happen, and if it does then it's our fault, not yours. Try the request again shortly.";

                    }

                    view.noArticlesAvailable(errorMsg);

                }));
    }

}
