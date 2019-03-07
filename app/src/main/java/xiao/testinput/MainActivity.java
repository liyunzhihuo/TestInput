package xiao.testinput;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    //comment
    private PopupWindow commentPopupWindow;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        height = dm.heightPixels;
        initUI();
        initCommentPop();
    }

    private void initUI() {
        findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentPopupWindow != null && !commentPopupWindow.isShowing()) {
                    commentPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                }
            }
        });
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

    private void initCommentPop() {
        View popView = LayoutInflater.from(MainActivity.this).inflate(R.layout.video_comment_pop, null);
        int maxHeight = height * 2 / 3;
        LinearLayout llInput = popView.findViewById(R.id.ll_input);
        llInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputPopView();
            }
        });
        commentPopupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, maxHeight);
        commentPopupWindow.setFocusable(true);
        commentPopupWindow.setOutsideTouchable(true);
        commentPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    @SuppressLint("WrongConstant")
    private void showInputPopView() {
        Activity context = this;
        if (context == null) {
            return;
        }
        View popView = LayoutInflater.from(context).inflate(R.layout.video_comment_input_pop, null);
        EditText etMessage = popView.findViewById(R.id.et_message);
        PopupWindow commentPopupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        commentPopupWindow.setFocusable(true);
        commentPopupWindow.setOutsideTouchable(true);
        commentPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        commentPopupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        commentPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        commentPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        if (commentPopupWindow.isShowing()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }


}



