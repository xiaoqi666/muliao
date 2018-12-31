package thefool.muliao.common.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @ClassName Activity
 * @Author AiSHan Feng
 * @Date 2018/12/31 19:14
 * @Version 1.0
 * @Description TODO
 */
public abstract class Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面未初始化之前调用的初始化窗口
        initWindows();

        if (initArgs(getIntent().getExtras())) {
            //参数未正常初始化不走下面的业务逻辑
            int layoutId = getContentLayoutId();
            setContentView(layoutId);
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected void initWindows() {

    }


    /**
     * 初始化相关参数,
     *
     * @param bundle 参数bundle
     * @return 如果参数正确返回true ,错误返回false
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }


    /**
     * 得到当前界面的布局资源Id
     *
     * @return
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    @Override
    public boolean onSupportNavigateUp() {
        //当点击界面导航返回时,finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //得到当前Activity下的所有fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        //判断是否为空
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                //判断是否是我们能够处理的fragment类型
                if (fragment instanceof thefool.muliao.common.app.Fragment) {
                    //判断是否拦截返回按钮
                    if (((thefool.muliao.common.app.Fragment) fragment).onBackPressed()) {
                        //如果有就return,不走下面的finish方法
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
