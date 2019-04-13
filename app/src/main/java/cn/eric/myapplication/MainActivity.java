package cn.eric.myapplication;

import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.View;

import cn.eric.basiclib.uicontroller.BaseActivity;
import cn.eric.myapplication.config.ConfigEntity;
import cn.eric.myapplication.config.ConfigManager;
import cn.eric.myapplication.databinding.ActivityMainBinding;
import cn.eric.myapplication.utils.InjectorUtils;
import cn.eric.myapplication.utils.ResultProcessor;
import cn.eric.myapplication.utils.ToastUtil;
import cn.eric.myapplication.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        ViewModelProvider.Factory viewModelFactory = InjectorUtils.provideMainViewModelFactory();
        return provideViewModel(viewModelFactory, MainViewModel.class);
    }

    @Override
    protected int getViewModelId() {
        return 0;
    }

    @Override
    protected void subscribeUi() {
        mViewModel.getEncryptKeyResult().observe(this, response -> {
            ResultProcessor.showResult(this, response, data -> {
                mViewDataBinding.tvResult.setText(data.getAuthKey());
            });
        });

        mViewModel.getScreenAdResult().observe(this, response -> {
            ResultProcessor.showResult(this, response, data -> {
                Log.d("dolphin", data.toString());
                ToastUtil.showToast(this, "请求成功");
            });
        });

        ConfigManager.getInstance().getDataReady().observe(this, isReady -> {
            if (isReady) {
                ToastUtil.showToast(MainActivity.this,"Config数据已就绪");
//                ConfigEntity configEntity = ConfigManager.getInstance().getConfigEntity();
//                Log.d("dolphin", configEntity.toString());
            } else {
                ToastUtil.showToast(MainActivity.this,"Config数据解析失败");
            }
        });
    }

    public void onRequestAuthKeyClick(View view) {
        mViewModel.fetchAuthKey();
    }

    public void onRequestScreenAdClick(View view) {
        mViewModel.fetchScreenAd();
    }

}
