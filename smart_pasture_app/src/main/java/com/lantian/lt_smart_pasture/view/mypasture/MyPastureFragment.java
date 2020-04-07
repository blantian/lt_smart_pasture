package com.lantian.lt_smart_pasture.view.mypasture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lantian.lt_smart_pasture.R;

/**
 * Created by Sherlock·Holmes on 2020-02-24
 */
public class MyPastureFragment extends Fragment {
    public static Fragment newInstance() {
        MyPastureFragment fragment = new MyPastureFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mypasture,null);
        return rootView;
    }
}
