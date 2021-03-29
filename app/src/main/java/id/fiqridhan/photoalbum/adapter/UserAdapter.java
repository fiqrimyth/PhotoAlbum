package id.fiqridhan.photoalbum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.fiqridhan.photoalbum.R;
import id.fiqridhan.photoalbum.listener.OnItemClickListener;
import id.fiqridhan.photoalbum.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {
    private ArrayList<User> userList;
    private Context context;
    private OnItemClickListener mListener;

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_item, null);

        CustomViewHolder customViewHolder = new CustomViewHolder(view, context, userList);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.txtTitle.setText(userList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ArrayList<User> userList;
        TextView txtTitle;
        RelativeLayout mLayout;

        public CustomViewHolder(final View itemView, Context context, ArrayList<User> userList) {
            super(itemView);
            this.context = context;
            this.userList = userList;
            txtTitle = itemView.findViewById(R.id.tv_title);
            mLayout = itemView.findViewById(R.id.m_layout);

            txtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClickListener(v, getLayoutPosition());
                }
            });

            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClickListener(v, getLayoutPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClickListener(v, getLayoutPosition());
                }
            });

        }

    }

    public void setItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }
}
