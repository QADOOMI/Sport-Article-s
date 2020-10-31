package mostafa.assign.sportsarticles.list.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResult")
    private int totalResult;

    @SerializedName("articles")
    private List<Article> articles;

    public ArticleResponse(String status, int totalResult, List<Article> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "status='" + status + '\'' +
                ", totalResult=" + totalResult +
                ", articles=" + articles.toString() +
                '}';
    }
}
