package xiao.testinput;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestFragment extends Fragment {

    //comment
    private PopupWindow commentPopupWindow;
    private int height;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        height = dm.heightPixels;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_frg, container, false);
        initUI(view);
        initCommentPop();
        return view;
    }

    private void initUI(View view) {
        view.findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentPopupWindow != null && !commentPopupWindow.isShowing()) {
                    commentPopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                }
            }
        });
        RelativeLayout viewGroup = view.findViewById(R.id.layout_comment);
        int maxHeight = height * 2 / 3;
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.height = maxHeight;
        viewGroup.setLayoutParams(layoutParams);
        LinearLayout llInput = view.findViewById(R.id.ll_input);
        llInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputPopView();
            }
        });

    }

    @SuppressLint("WrongConstant")
    private void initCommentPop() {
        View popView = LayoutInflater.from(getActivity()).inflate(R.layout.video_comment_pop, null);
        int maxHeight = height * 2 / 3;
        LinearLayout llInput = popView.findViewById(R.id.ll_input);
        llInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputPopView();
            }
        });
        commentPopupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, maxHeight);
        commentPopupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        commentPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        commentPopupWindow.setFocusable(true);
        commentPopupWindow.setOutsideTouchable(true);
        commentPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    @SuppressLint("WrongConstant")
    private void showInputPopView() {
        Activity context = getActivity();
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
        commentPopupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        if (commentPopupWindow.isShowing()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }

}
