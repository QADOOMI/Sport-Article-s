package mostafa.assign.sportsarticles.list.views;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import mostafa.assign.sportsarticles.databinding.ArticleListItemLayoutBinding;
import mostafa.assign.sportsarticles.details.views.ArticleDetailsActivity;
import mostafa.assign.sportsarticles.list.model.Article;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticleHolder> {

    private List<Article> articles;
    private Activity activity;

    public static final String ARTICLE_KEY = "ARTK";

    public ArticlesListAdapter(List<Article> articles, Activity activity) {
        this.articles = articles;
        this.activity = activity;
    }

    @NonNull
    @Override

    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArticleListItemLayoutBinding binding = ArticleListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new ArticleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        Article article = articles.get(holder.getAdapterPosition());

        holder.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(activity, ArticleDetailsActivity.class);
            intent.putExtra(ARTICLE_KEY, article);
            activity.startActivity(intent);
        });

        Glide.with(holder.getImage().getContext())
                .asBitmap()
                .centerInside()
                .load(article.getUrlToImage())
                .into(holder.getImage());

        holder.getTitle().setText(article.getTitle());
        holder.getAuthor().setText(article.getAuthor());

        if (holder.getAdapterPosition() == articles.size() - 1)
            holder.getDivider().setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void addArticles(@NonNull List<Article> articleList) {
        if (articles.size() > 0)
            articleList.clear();

        articles.addAll(articleList);
        notifyDataSetChanged();
    }
}
