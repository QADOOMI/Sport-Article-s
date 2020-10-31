package mostafa.assign.sportsarticles.list.views;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import mostafa.assign.sportsarticles.databinding.ArticleListItemLayoutBinding;

public class ArticleHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView image;
    private AppCompatTextView title, author;
    private View divider, root;

    public ArticleHolder(@NonNull ArticleListItemLayoutBinding itemView) {
        super(itemView.getRoot());

        root = itemView.getRoot();
        divider = itemView.divider;
        image = itemView.sportItemImage;
        title = itemView.sportItemTitle;
        author = itemView.sportItemAuthor;
    }

    @NonNull
    public View getRoot() {
        return root;
    }

    @NonNull
    public View getDivider() {
        return divider;
    }

    @NonNull
    public AppCompatImageView getImage() {
        return image;
    }

    @NonNull
    public AppCompatTextView getTitle() {
        return title;
    }

    @NonNull
    public AppCompatTextView getAuthor() {
        return author;
    }
}
