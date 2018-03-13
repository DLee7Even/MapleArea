package com.example.skipmaple.logintest;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SkipMaple on 2018/2/10.
 */

public class User extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout mDrawerLayout;

    private Button mReturnButton;
    private RelativeLayout mLayout;

    private TextView topBar;
    private TextView tabControl;
    private TextView tabHome;
    private TextView tabPersonal;

    private FrameLayout ly_content;

    private FirstFragment f1, f2, f3;
    private FragmentManager fragmentManager;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        //设置侧滑菜单点击按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //处理侧滑菜单的点击事件
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                        Toast.makeText(User.this, "you clicked call item",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                    case R.id.nav_friends:
                        Toast.makeText(User.this, "you clicked friends item",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                    case R.id.nav_location:
                        Toast.makeText(User.this, "you clicked location item",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                    case R.id.nav_mail:
                        Toast.makeText(User.this, "you clicked mail item",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                    case R.id.nav_task:
                        Toast.makeText(User.this, "you clicked task item",
                                Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                }

                return true;
            }
        });

        bindView();         //UI组件初始化与事件绑定
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        mLayout = (RelativeLayout) findViewById(R.id.rbk_rlyt);
        mReturnButton = (Button) findViewById(R.id.returnback);

        topBar = (TextView)this.findViewById(R.id.txt_top);
        tabControl = (TextView)this.findViewById(R.id.txt_control);
        tabHome = (TextView)this.findViewById(R.id.txt_home);
        tabPersonal = (TextView)this.findViewById(R.id.txt_personal);

        ly_content = (FrameLayout) this.findViewById(R.id.fragment_container);

        tabControl.setOnClickListener(this);
        tabHome.setOnClickListener(this);
        tabPersonal.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    public void selected() {
        tabControl.setSelected(false);
        tabHome.setSelected(false);
        tabPersonal.setSelected(false);
    }

    //隐藏所有的Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
        if (f3 != null) {
            transaction.hide(f3);
        }
    }

    public void back_to_login(View view) {
        Intent intent_Login = new Intent(User.this, Login.class);
        intent_Login.putExtra("autoLoginFlag", true);
        startActivity(intent_Login);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.user_item1:
                Toast.makeText(this, "you clicked item1",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_item2:
                Toast.makeText(this, "you clicked item2",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_item3:
                Toast.makeText(this, "you clicked item3",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (view.getId()) {
            case R.id.txt_control:
                selected();
                tabControl.setSelected(true);
                if (f1 == null) {
                    f1 = new FirstFragment("控制台界面");
                    transaction.add(R.id.fragment_container, f1);
                } else {
                    transaction.show(f1);
                }
                break;

            case R.id.txt_home:
                selected();
                tabHome.setSelected(true);
                if (f2 == null) {
                    f2 = new FirstFragment("主界面");
                    transaction.add(R.id.fragment_container, f2);
                } else {
                    transaction.show(f2);
                }
                break;

            case R.id.txt_personal:
                selected();
                tabPersonal.setSelected(true);
                if (f3 == null) {
                    f3 = new FirstFragment("个人中心界面");
                    //mLayout.setVisibility(View.VISIBLE);
                    transaction.add(R.id.fragment_container, f3);
                } else {
                    transaction.show(f3);
                }
                break;
        }

        transaction.commit();
    }
}
