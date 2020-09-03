package com.gendra.appgendraexamen.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gendra.appgendraexamen.R;
import com.gendra.appgendraexamen.application.BaseActivity;
import com.gendra.appgendraexamen.dagger.component.ApplicationComponent;
import com.gendra.appgendraexamen.dagger.modules.RepositoryModule;
import com.gendra.appgendraexamen.databinding.ActivityMainBinding;
import com.gendra.appgendraexamen.ui.mapfragment.MapFragment;
import com.gendra.appgendraexamen.utils.SnackBar;
import com.squareup.picasso.Picasso;

public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String IMGE_LOGO_URI = "https://gendra.mx/images/logo.png";
    private static final int TIME_IN_SPLASH = 2000;

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onResume() {
        super.onResume();

        mActivityMainBinding.imageLogo.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment initialFragment = MapFragment.newInstance();
                addFragment(R.id.container, initialFragment);
            }
        }, TIME_IN_SPLASH);

    }

    @Override
    public void setUpComponent(ApplicationComponent appComponent) {
        appComponent.getPublicMSSessionManager().createMSSession();
        DaggerMainComponent.builder()
                .publicSessionComponent(appComponent.getPublicMSSessionManager().getPublicMSComponent())
                .mainModule(new MainModule(this))
                .repositoryModule(new RepositoryModule())
                .build()
                .inject(this);

        // se iicializan los componentes que deben iniciar en el onCreate
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        mActivityMainBinding.imageLogo.setVisibility(View.VISIBLE);


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void showSnackBar(String message) {
        SnackBar.showSnackBar(message,getLayoutRootView(R.id.containerMainActivity),getBaseContext());
    }

    public void goneImageLogo(){
        mActivityMainBinding.imageLogo.setVisibility(View.GONE);
    }

}