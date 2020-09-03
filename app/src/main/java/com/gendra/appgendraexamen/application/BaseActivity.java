package com.gendra.appgendraexamen.application;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gendra.appgendraexamen.dagger.component.ApplicationComponent;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;
import com.gendra.appgendraexamen.utils.IBackHandler;

public abstract class BaseActivity extends AppCompatActivity implements IBackHandler {

    private AppSharePreferencesManager sharedPreferences;
    private BaseFragment baseFragment;
    private PublicSessionManager publicSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        injectDependencies();
    }

    @Override
    protected void onStart() {
        super.onStart();
        publicSessionManager=AppInit.getAppComponent().getPublicMSSessionManager();
        AppInit.getAppComponent().getPublicMSSessionManager().createMSSession();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (baseFragment == null || !baseFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        baseFragment = fragment;
    }

    private void injectDependencies() {
        setUpComponent(AppInit.getAppComponent());
    }

    public abstract void setUpComponent(ApplicationComponent appComponent);
    protected abstract int getLayoutResource();

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void changeFragment(int containerViewId, Fragment fragment, String TAG) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, TAG);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    protected Fragment getFragmentByTag(String tag){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        return fragment;
    }

    protected void changeFragmentNew(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public void finishAffinityVersion(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            finishAffinity();
        }else{
            finish();
        }
    }

    public View getLayoutRootView(int idView){
        View view=getCurrentFocus() == null ? this.findViewById(idView) : getCurrentFocus();
        return view;
    }

}
