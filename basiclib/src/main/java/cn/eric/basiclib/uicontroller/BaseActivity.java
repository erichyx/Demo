package cn.eric.basiclib.uicontroller;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by eric on 2019/3/24
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    protected B mViewDataBinding;
    protected VM mViewModel;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewModel = getViewModel();
        if (getViewModelId() > 0) {
            mViewDataBinding.setVariable(getViewModelId(), mViewModel);
        }
        subscribeUi();
    }

    abstract protected @LayoutRes int getLayoutId();
    abstract protected VM getViewModel();
    protected abstract @IdRes int getViewModelId();
    abstract protected void subscribeUi();
}
