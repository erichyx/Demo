package cn.eric.myapplication;

import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.View;

import cn.eric.basiclib.utils.ViewModelUtils;
import cn.eric.myapplication.databinding.ActivityMainBinding;
import cn.eric.myapplication.uicontroller.BaseActivity;
import cn.eric.myapplication.utils.InjectorUtils;
import cn.eric.myapplication.utils.ResultUtil;
import cn.eric.myapplication.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        ViewModelProvider.Factory viewModelFactory = InjectorUtils.provideMainViewModelFactory();
        return ViewModelUtils.provideViewModel(this, viewModelFactory, MainViewModel.class);
    }

    @Override
    protected int getViewModelId() {
        return 0;
    }

    @Override
    protected void subscribeUi() {
        mViewModel.getAdResult().observe(this, response -> {
            ResultUtil.showResult(this, response, data -> {
                Log.d("dolphin", data.toString());
            });
        });
    }

    public void onRequestClick(View view) {
        mViewModel.fetchAd();
    }

}
