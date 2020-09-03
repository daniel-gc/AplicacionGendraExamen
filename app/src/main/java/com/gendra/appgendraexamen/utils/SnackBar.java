package com.gendra.appgendraexamen.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gendra.appgendraexamen.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBar {

    public static void showSnackBar(String msg, View rootView, Context context) {
        Snackbar snack;
        View snackView;
        TextView tv;

        try {
            snack = Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG);
            snackView = snack.getView();
            tv = snackView.findViewById(com.google.android.material.R.id.snackbar_text);

            if (Build.VERSION.SDK_INT >= 23) {
                tv.setTextColor(context.getColor(R.color.base_white));
            } else {
                tv.setTextColor(context.getResources().getColor(R.color.base_white));
            }
            snack.show();
        } catch (Resources.NotFoundException e) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
