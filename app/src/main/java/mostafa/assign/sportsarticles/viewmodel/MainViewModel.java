package mostafa.assign.sportsarticles.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.annotations.NonNull;
import mostafa.assign.sportsarticles.list.model.Article;
import mostafa.assign.sportsarticles.list.model.ArticleResponse;
import mostafa.assign.sportsarticles.repositories.ArticleRepository;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArticleResponse> articles;
    private MediatorLiveData<List<Article>> articlesStream;

   public void init(){
       if (articlesStream == null)
           articlesStream = new MediatorLiveData<>();

   }

    public LiveData<ArticleResponse> getArticles() {
        return articles;
    }

    public MediatorLiveData<List<Article>> getArticlesStream() {
        return articlesStream;
    }

    public void fetchSportArticles(@NonNull String country, @NonNull String category) {
        final LiveData<ArticleResponse> source = ArticleRepository.INIT
                .INSTANCE
                .getSportArticles(country, category);

        articlesStream.addSource(source, articleResponse -> {
            articlesStream.setValue(articleResponse.getArticles());
            articlesStream.removeSource(source);
        });
    }
}
