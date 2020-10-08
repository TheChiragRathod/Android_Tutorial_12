package com.chirag.tutorial12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    JSONArray usersJSONArray;
    ProgressDialog dialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Users List");
        dialog = new ProgressDialog(this);

        recyclerView=(RecyclerView)findViewById(R.id.myRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setVollyAPI();
    }




    public void setVollyAPI()
    {

        //Volley Library Code Start Here........
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                MyUtil.USER_URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    usersJSONArray=response;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //RecyclerView Adapter Class...
                MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(MainActivity.this,usersJSONArray);
                recyclerView.setAdapter(adapter);
                if(dialog.isShowing())
                    dialog.dismiss();

                    }
                },

                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Somthing went Wrong...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setMessage("Fetching Data");
        dialog.show();
        requestQueue.add(jsonArrayRequest);
        //Volley Library Code Finished Here........

    }
}