package com.chirag.tutorial12;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{
   Context context;
   JSONArray usersJSONArray;

    public MyRecyclerViewAdapter(Context context, JSONArray usersJSONArray) {
        this.context = context;
        this.usersJSONArray = usersJSONArray;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        JSONObject user= null;
        try {
            user = usersJSONArray.getJSONObject(position);


        holder.textViewName.setText(user.getString("name"));
        holder.textViewEmail.setText(user.getString("email"));
        holder.textViewNumber.setText(user.getString("phone"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return usersJSONArray.length();
    }





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewName;
        public TextView textViewEmail;
        public TextView textViewNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            textViewName=itemView.findViewById(R.id.lyt_txtName);
            textViewEmail=itemView.findViewById(R.id.lyt_txtEmail);
            textViewNumber=itemView.findViewById(R.id.lyt_txtMoNumber);
        }
        @Override
        public void onClick(View view)
        {
            int position=this.getAdapterPosition();

            try {
                MyUtil.userJSONObj=usersJSONArray.getJSONObject(position);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent =new Intent(context,UserData.class);
            context.startActivity(intent);
        }
    }


}
