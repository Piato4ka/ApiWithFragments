package com.example.offerwall;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


public class Fragment3 extends Fragment {
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);
        imageView = view.findViewById(R.id.picture);
        String s = getArguments().getString("key");
        Glide.with(view).load(s).into(imageView);
        return view;
    }
}