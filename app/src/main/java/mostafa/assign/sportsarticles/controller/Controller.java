package mostafa.assign.sportsarticles.controller;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public interface Controller {

    void onError(@NonNull Throwable throwable);

    void onSubscribe(@NonNull Disposable disposable);
}
