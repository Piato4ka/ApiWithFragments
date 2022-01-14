 package com.example.offerwall;

 import android.app.Fragment;
 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.TextView;

 public class Fragment1 extends Fragment {

    private TextView textView;

     public Fragment1() {
     }

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        textView = view.findViewById(R.id.textView);
        String s = getArguments().getString("key");
        textView.setText(s);
        return view;
    }
}