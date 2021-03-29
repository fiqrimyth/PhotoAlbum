package id.fiqridhan.photoalbum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import id.fiqridhan.photoalbum.R;
import id.fiqridhan.photoalbum.listener.OnItemClickListener;
import id.fiqridhan.photoalbum.model.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.CustomViewHolder> {
    private ArrayList<Album> albumList;
    private Context context;
    private OnItemClickListener mListener;

    public AlbumAdapter(ArrayList<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album, null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view, context, albumList);
        return customViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.txtTitle.setText(albumList.get(position).getTitle());
        Glide.with(context)
                .load(albumList.get(position).getUrl())
                .placeholder(R.color.color_text)
                .into(holder.imgAlbum);

        Glide.with(context)
                .load(albumList.get(position).getThumbnailUrl())
                .into(holder.imgProfile);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ArrayList<Album> albumList;
        TextView txtTitle;
        ImageView imgAlbum;
        CircleImageView imgProfile;

        public CustomViewHolder(final View itemView, Context context, ArrayList<Album> albumList) {
            super(itemView);
            this.context = context;
            this.albumList = albumList;
            txtTitle = itemView.findViewById(R.id.tv_title);
            imgAlbum = itemView.findViewById(R.id.img_album);
            imgProfile = itemView.findViewById(R.id.img_profile);

        }
    }
}
