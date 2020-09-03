package com.gendra.appgendraexamen.ui;

import java.util.List;

public interface MainContract {
    interface View{
        void showSnackBar(String message);
        //void buildPolygons(List<List<List<Double>>> coordinates);
    }
    interface Presenter{
        void initFragment();
    }
}
