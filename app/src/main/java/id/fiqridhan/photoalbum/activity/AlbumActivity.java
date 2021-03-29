package id.fiqridhan.photoalbum.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.fiqridhan.photoalbum.R;
import id.fiqridhan.photoalbum.adapter.AlbumAdapter;
import id.fiqridhan.photoalbum.model.Album;

public class AlbumActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Activity mActivity = AlbumActivity.this;
        ArrayList<Album> albumList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_user);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(linearLayoutManager);

        AlbumAdapter albumAdapter = new AlbumAdapter(albumList, mActivity);
        recyclerView.setAdapter(albumAdapter);
    }

}
