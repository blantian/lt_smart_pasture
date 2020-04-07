package com.lantian.lib_commin_ui.base;

import androidx.fragment.app.Fragment;

import com.lantian.lib_base.utils.ToastUtils;
/**
 * Fragment懒加载
 * Created by Sherlock·Holmes on 2020-03-03
 */
public class BaseFragmen extends Fragment {
    //private int layoutId;

    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

   // public BaseFragmen() {

  //  }

    /**@SuppressLint("ValidFragment")
    public BaseFragmen(int layoutId) {
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(layoutId, container, false);
        //把View的click属性设为true，截断点击时间段扩散 解决点击穿透
        rootView.setClickable(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // 监听到返回按钮点击事件
                    backPrev();
                    return true;
                }
                return false;
            }
        });
    }

    public void backPrev() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.popBackStack();

    }
    **/
}
