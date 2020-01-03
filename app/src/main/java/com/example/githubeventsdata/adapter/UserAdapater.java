package com.example.githubeventsdata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.githubeventsdata.R;
import com.example.githubeventsdata.modal.User;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapater extends RecyclerView.Adapter<UserAdapater.UserViewHolder>
{
    Context context;
    User[] data;

    public UserAdapater(Context context, User[] users)
    {
        this.context = context;
        this.data = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_user_layout,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position)
    {
        try {
            User user = data[position];
            final String display_login = user.getActor().getDisplayLogin();
            String avatar_url = user.getActor().getAvatarUrl();

            System.out.println("display_login "+display_login+" avatar_url "+avatar_url);
            holder.txtUser.setText(display_login);

            //Glide.with(holder.imgUser.getContext()).load(avatar_url).into(holder.imgUser);

            Picasso.with(holder.imgUser.getContext()).load(avatar_url).into(holder.imgUser);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, display_login, Toast.LENGTH_SHORT).show();
                }
            });

            /*holder.imgUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, display_login, Toast.LENGTH_SHORT).show();
                }
            });*/
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView imgUser;
        TextView txtUser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=itemView.findViewById(R.id.imgUser);
            txtUser=itemView.findViewById(R.id.txtUser);        }
    }

}
