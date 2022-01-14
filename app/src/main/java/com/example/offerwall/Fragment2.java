package com.example.offerwall;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class Fragment2 extends Fragment {

    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);
        webView = view.findViewById(R.id.webView);
        String s = getArguments().getString("key");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(s);
        return view;
    }
}