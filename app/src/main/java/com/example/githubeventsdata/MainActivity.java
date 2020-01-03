package com.example.githubeventsdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.githubeventsdata.adapter.UserAdapater;
import com.example.githubeventsdata.modal.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity
{
    private static final String URL="https://api.github.com/events";
    RecyclerView userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(this));

        callAPI();
    }

    private void callAPI()
    {
        try {
            StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    User[] users = gson.fromJson(response, User[].class);
                    userList.setAdapter(new UserAdapater(MainActivity.this,users));

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    System.out.println(error);

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
