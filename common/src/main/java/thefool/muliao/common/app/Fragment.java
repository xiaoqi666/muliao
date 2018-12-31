package thefool.muliao.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName Fragment
 * @Author AiSHan Feng
 * @Date 2018/12/31 19:14
 * @Version 1.0
 * @Description TODO
 */
public abstract class Fragment extends android.support.v4.app.Fragment {

    protected View mRootView;
    protected Unbinder mRootUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            int layId = getContentLayoutId();
            // 初始化当前的跟布局,但是不在创建时就添加到container里面
            mRootView = inflater.inflate(layId, container, false);
            initWidget(mRootView);
        } else {
            if (mRootView.getParent() != null) {
                //把当前rootView从其付控件中移除
                //如果父布局不等于空,将父布局移除
                ((ViewGroup) mRootView.getParent()).removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View创建完成之后初始化数据
        initData();
    }

    /**
     * 初始化相关参数,
     *
     * @param bundle 参数bundle
     * @return 如果参数正确返回true ,错误返回false
     */
    protected void initArgs(Bundle bundle) {

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
    protected void initWidget(View rootView) {
        mRootUnbinder = ButterKnife.bind(this, rootView);

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 返回按键出发时调用(自己实现的)
     *
     * @return 返回true代表我以处理返回逻辑, Activity不用自己finish
     * 返回false代表我没有处理,Activity自己走自己的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }

}
