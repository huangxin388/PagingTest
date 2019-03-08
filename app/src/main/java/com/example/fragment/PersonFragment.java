package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.myapplication.R;

public class PersonFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person,container,false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("xxx","PersonFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("xxx","PersonFragment onPause");
    }
}
