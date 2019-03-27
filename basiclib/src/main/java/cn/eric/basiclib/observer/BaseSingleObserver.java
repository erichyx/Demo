package cn.eric.basiclib.observer;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by eric on 2018/1/7.
 */

public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

}
