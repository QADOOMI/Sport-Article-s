package mostafa.assign.sportsarticles.list.views;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import mostafa.assign.sportsarticles.R;
import mostafa.assign.sportsarticles.controller.SportArticlesView;
import mostafa.assign.sportsarticles.databinding.ActivityArticlesListBinding;
import mostafa.assign.sportsarticles.list.model.Article;
import mostafa.assign.sportsarticles.list.presenter.SportPresenter;

public class ArticlesListActivity extends AppCompatActivity implements SportArticlesView {

    private RecyclerView articles;
    private Group noArticlesGroup;
    private ContentLoadingProgressBar loadingBar;

    private ActivityArticlesListBinding binding;
    private ArticlesListAdapter adapter;
    private CompositeDisposable subscribers;
    private SportPresenter presenter;

    private static final String TAG = "ArticlesListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticlesListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        subscribers = new CompositeDisposable();

        noArticlesGroup = binding.noArticlesGroup;
        loadingBar = binding.loadingArticlesBar;

        articles = binding.articlesList;
        articles.setHasFixedSize(false);
        articles.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        articles.setNestedScrollingEnabled(false);

        adapter = new ArticlesListAdapter(new ArrayList<>(), this);

        articles.post(() -> articles.setAdapter(adapter));

        presenter = new SportPresenter(getApplicationContext(), this);
        loadingBar.setVisibility(View.VISIBLE);
        loadingBar.show();
        presenter.getSportArticles("gb", "sports");

    }

    @Override
    public void onSportArticlesFetched(List<Article> articleList) {
        runOnUiThread(() -> {
            if (noArticlesGroup.getVisibility() == View.VISIBLE)
                noArticlesGroup.setVisibility(View.GONE);

            loadingBar.setVisibility(View.GONE);
            adapter.addArticles(articleList);
        });
    }

    @Override
    public void noArticlesAvailable(String errorMsg) {
        runOnUiThread(() -> {
            loadingBar.setVisibility(View.GONE);

            noArticlesGroup.setVisibility(View.VISIBLE);
            Snackbar.make(findViewById(android.R.id.content), errorMsg, Snackbar.LENGTH_LONG)
                    .setAction(R.string.refresh_articles, v ->
                            presenter.getSportArticles("gb", "sports")
                    ).setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.dark_white)))
                    .setTextColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.design_default_color_surface)))
                    .setActionTextColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)))
                    .show();
        });
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG, "onError: ", throwable);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        subscribers.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscribers.clear();
        binding = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscribers.clear();
    }


}