package com.android.icefire.zhihudailydagger.mvp.base.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.icefire.zhihudailydagger.R;
import com.android.icefire.zhihudailydagger.support.swipeback.SwipeBackLayout;
import com.android.icefire.zhihudailydagger.support.widget.FlatButton;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * 基类继承了SwipeBack类
 * Created by yangchj on 2016/7/7 0007.
 * email:yangchj@neusoft.com
 */
public abstract class BaseSwipeBackMvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends MvpSwipeBackActivity<V, P> {

    protected SwipeBackLayout mSwipeBackLayout;
    protected View emptyLayout;
    protected FlatButton layoutReload;
    protected Toolbar mToolbar;
    protected TextView mToolBarTitle;

    protected Context mContext;
    private boolean canBack=true;

    public void setCanBack(boolean canBack) {
        this.canBack = canBack;
    }

    /**
     * 返回按键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            scrollToFinishActivity();
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar();
        mContext=this;
        if(getLayoutId()==0){
            super.setContentView(R.layout.activity_base);
        }else{
            super.setContentView(getLayoutId());
        }
        mSwipeBackLayout=getSwipeBackLayout();
        afterCreate(savedInstanceState);
        requestData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        RelativeLayout rootLayout= (RelativeLayout) findViewById(R.id.root_view);
        emptyLayout=findViewById(R.id.layoutEmpty);
        layoutReload= (FlatButton) emptyLayout.findViewById(R.id.layoutReload);
        emptyLayout.setVisibility(View.GONE);
        if(rootLayout==null) return;
        rootLayout.addView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolBar();
    }

    private void initToolBar(){
        mToolbar= (Toolbar) findViewById(R.id.toolBar);
        if(mToolbar!=null){
            setSupportActionBar(mToolbar);
            mToolBarTitle= (TextView) mToolbar.findViewById(R.id.toolBarTitle);
            if(mToolBarTitle==null){
                mToolBarTitle= (TextView) findViewById(R.id.toolBarTitle);
            }
            if(mToolBarTitle!=null&&getSupportActionBar()!=null){
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            if(canBack){
                mToolbar.setNavigationIcon(R.drawable.ic_back);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if(mToolBarTitle!=null){
            mToolBarTitle.setText(title);
        }
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    private void initSystemBar(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager tintManager=new SystemBarTintManager(this);
            //enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            //enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            // set a custom tint color for all system bars
            tintManager.setTintColor(R.color.colorPrimary);
            tintManager.setStatusBarTintColor(R.color.colorPrimaryDark);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on){
        Window window=getWindow();
        WindowManager.LayoutParams winParams=window.getAttributes();
        final int bits=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(on){
            winParams.flags|=bits;
        }else{
            winParams.flags&=~bits;
        }
        window.setAttributes(winParams);
    }

    protected abstract int getLayoutId();
    protected abstract void afterCreate(Bundle savedInstanceState);
    protected abstract void requestData();

    protected void readyGo(Class<?> clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz,Bundle bundle){
        Intent intent=new Intent(this,clazz);
        if(null!=bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoThenKill(Class<?> clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    protected void readyGoThenKill(Class<?> clazz,Bundle bundle){
        Intent intent=new Intent(this,clazz);
        if(null!=bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * 带有返回结果
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 带有参数并返回结果
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected ProgressDialog mDialog;

    protected void showLoadingDialog(String title,String msg){
        if(mDialog!=null){
            mDialog.dismiss();
            mDialog=null;
        }
        mDialog=ProgressDialog.show(mContext,title,msg,true,true);
    }

    protected void showLoadingDialog(String msg){
        if(mDialog!=null){
            mDialog.dismiss();
            mDialog=null;
        }
        mDialog=ProgressDialog.show(mContext,"",msg,true,true);
    }

    protected void dismissDialog(){
        if(mDialog!=null){
            mDialog.dismiss();
            mDialog=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }


}
