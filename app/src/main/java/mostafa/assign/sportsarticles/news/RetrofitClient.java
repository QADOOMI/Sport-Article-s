package mostafa.assign.sportsarticles.news;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient  {

    private static final String TAG = RetrofitClient.class.getSimpleName();

    private RetrofitClient() {
    }

    @NonNull
    public static Retrofit getRetrofit() {
        return Helper.retrofit;
    }

    private interface Helper {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    public static String getBaseUrl() {
        return "http://newsapi.org/v2/";
    }
}
