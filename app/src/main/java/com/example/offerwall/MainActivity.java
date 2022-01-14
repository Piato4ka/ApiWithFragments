package com.example.offerwall;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    protected final static String BASE_URL = "https://demo3005513.mockable.io/";
    private Pojo pojo;
    private Retrofit retrofit;
    private  List<Datum> dataList;
    private final Fragment1 frag1=  new Fragment1();
    private final Fragment2 frag2 = new Fragment2();
    private final Fragment3 frag3 = new Fragment3();
    private FragmentTransaction fTrans;
    private JSONObject objJson;
    private String responseJson;
    private int dataListNumberObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            dataListNumberObj = savedInstanceState.getInt("dataListNumberObj");
        } else {
            dataListNumberObj = 0;
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequests apiRequests = retrofit.create(ApiRequests.class);
        Call<Pojo> pojoCall = apiRequests.getBasicRequest();

        pojoCall.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                if (response.isSuccessful()) {
                    pojo = response.body();
                    dataList = pojo.getData();

                    try {
                        setFragment(dataList.get(dataListNumberObj).getId().toString());
                        dataListNumberObj++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("MyApp", "else");
                }
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Log.i("MyApp", "Network Error :: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("dataListNumberObj", dataListNumberObj);
        super.onSaveInstanceState(savedInstanceState);
    }

    void setFragment(String id) throws JSONException {
        ApiRequests apiRequests = retrofit.create(ApiRequests.class);

        Call<String> call = apiRequests.getNextRequest(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    responseJson = response.body();

                    try {
                        objJson = new JSONObject(responseJson);
                        Bundle args = new Bundle();
                        fTrans = getFragmentManager().beginTransaction();
                        if (objJson.getString("type").equals("text")) {
                            String s = objJson.getString("message");
                            args.putString("key", s);
                            frag1.setArguments(args);
                            fTrans.replace(R.id.frame_container, frag1);
                            fTrans.commit();
                        } else if (objJson.getString("type").equals("webview")) {
                            String s = objJson.getString("url");
                            args.putString("key", s);
                            frag2.setArguments(args);
                            fTrans.replace(R.id.frame_container, frag2);
                            fTrans.commit();
                        } else if (objJson.getString("type").equals("image")) {
                            String s = objJson.getString("url");
                            args.putString("key", s);
                            frag3.setArguments(args);
                            fTrans.replace(R.id.frame_container, frag3);
                            fTrans.commit();
                        } else {
                            objJson.getString("type");// do nothing
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MyApp", "Network Error :: " + t.getLocalizedMessage());
            }
        });
    }

    public void goNext(View view) throws JSONException {
        if (dataListNumberObj >= dataList.size()) {
            dataListNumberObj = 0;
        }
        setFragment(dataList.get(dataListNumberObj).getId().toString());
        dataListNumberObj++;
    }
}