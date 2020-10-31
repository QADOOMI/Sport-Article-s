package mostafa.assign.sportsarticles.controller;

import java.util.List;

import io.reactivex.annotations.NonNull;
import mostafa.assign.sportsarticles.list.model.Article;

public interface SportArticlesView extends Controller {
    void onSportArticlesFetched(@NonNull List<Article> articleList);

    void noArticlesAvailable(@NonNull String errorMsg);
}
