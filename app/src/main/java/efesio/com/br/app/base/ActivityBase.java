package efesio.com.br.app.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by otavi on 15/05/2017.
 */

public abstract class ActivityBase extends AppCompatActivity {

    public void open(Class<? extends Activity> activity){
        open(activity, (Intent) null);
    }

    public void open(Class<? extends Activity> activity, Bundle bundle){
        Intent intent = new Intent(this, activity);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    public void open(Class<? extends Activity> activity, Intent extras){
        Intent intent = new Intent(this, activity);
        if (extras != null)
            intent.putExtras(extras);
        startActivity(intent);
    }

    public void addFragment(int viewId, Fragment f, String tag){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(viewId, f);
        ft.commit();
    }

    public void replaceFragment(int viewId, Fragment f, String tag){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(viewId, f);
        ft.addToBackStack(tag+"-bs");
        ft.commit();
    }

    public void showFragment(String tag){
        Fragment prev = getSupportFragmentManager().findFragmentByTag(tag);
        if(prev != null) {
            prev.getView().setVisibility(View.VISIBLE);
        }
    }

//    public void hideFragment(String tag){
//        Fragment prev = getSupportFragmentManager().findFragmentByTag(tag);
//        if(prev != null) {
//            prev.getView().setVisibility(View.GONE);
//        }
//    }

//    public void setToolbar(Toolbar toolbar) {
//        setToolbar(toolbar, getString(R.string.app_name));
//    }

//    public void setToolbar(Toolbar toolbar, String title) {
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setTitle(title);
//            toolbar.setTitle(title);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
//        }
//    }

    public final void loading(boolean loading){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(!loading) {
            DialogFragment prev = (DialogFragment)getSupportFragmentManager().findFragmentByTag("loading_fragment");
            if (prev != null) {
                prev.dismiss();
                ft.remove(prev);
            }
        }else{
            ft.addToBackStack(null);
            LoadingFragment.newInstance().show(ft, "loading_fragment");
        }
    }

    public void alert(String message) {
        alert("Atenção", message);
    }

    public void alert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void alert(String title, String message, DialogInterface.OnClickListener onClickOk) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", onClickOk);
        builder.create().show();
    }

    public void alert(String title, String message, DialogInterface.OnClickListener onClickOk, DialogInterface.OnClickListener onClickCancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", onClickOk);
        builder.setNegativeButton("Cancelar", onClickCancel);
        builder.create().show();
    }


}
