package mostafa.assign.sportsarticles.details.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

import mostafa.assign.sportsarticles.databinding.ActivityArticleDetailsBinding;
import mostafa.assign.sportsarticles.list.model.Article;

import static mostafa.assign.sportsarticles.list.views.ArticlesListAdapter.ARTICLE_KEY;

public class ArticleDetailsActivity extends AppCompatActivity {

    private ActivityArticleDetailsBinding binding;

    private static final String TAG = "ArticleDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Article article = getIntent().getParcelableExtra(ARTICLE_KEY);

        Glide.with(this)
                .asBitmap()
                .load(article.getUrlToImage())
                .into(binding.articleDetailsImage);

        runOnUiThread(() -> {
            binding.articleDetailsTitle.setText(article.getTitle());
            binding.articleDetailsAuthor.setText(article.getAuthor());
            binding.articleDetailsDesc.setText(article.getDescription());
            binding.articleDetailsPd.setText(article.getPublishedAt()
                    .replace('T', ' ')
                    .replace('Z', ' ')
                    .trim());

        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}