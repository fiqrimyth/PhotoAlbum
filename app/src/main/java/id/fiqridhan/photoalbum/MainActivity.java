package id.fiqridhan.photoalbum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.fiqridhan.photoalbum.activity.AlbumActivity;
import id.fiqridhan.photoalbum.activity.BaseActivity;
import id.fiqridhan.photoalbum.adapter.AlbumAdapter;
import id.fiqridhan.photoalbum.adapter.UserAdapter;
import id.fiqridhan.photoalbum.api.ApiInterface;
import id.fiqridhan.photoalbum.api.RetrofitClient;
import id.fiqridhan.photoalbum.listener.OnItemClickListener;
import id.fiqridhan.photoalbum.model.Album;
import id.fiqridhan.photoalbum.model.User;
import id.fiqridhan.photoalbum.pref.PrefKey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private ArrayList<Album> albumList;
    private ArrayList<User> userList;
    private AlbumAdapter albumAdapter;
    private UserAdapter userAdapter;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_user);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(linearLayoutManager);

        mActivity = MainActivity.this;
        albumList = new ArrayList<>();
        userList = new ArrayList<>();

        albumAdapter = new AlbumAdapter(albumList, mActivity);
        userAdapter = new UserAdapter(userList, mActivity);
        recyclerView.setAdapter(albumAdapter);

        initLoader();
        getDataFromServer();
        initListener();
    }

    public void getDataFromServer() {
        ApiInterface api = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<Album>> call = api.getAlbum(1);

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (!albumList.isEmpty()) {
                    albumList.clear();
                }
                albumList.addAll(response.body());
                hideLoader();
                if (albumList.isEmpty()) {
                    showEmptyView();
                }
                albumAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                if (albumList.isEmpty()) {
                    showEmptyView();
                }
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener() {
        userAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_title:
                    case R.id.m_layout:

                    default:
                        sendDataViaIntent(position);
                        break;
                }
            }
        });
    }

    private void sendDataViaIntent(int position) {
        Intent intent = new Intent(mActivity, AlbumActivity.class);
        intent.putExtra(PrefKey.APP_PREFERENCE, albumList.get(position).getId());
        startActivity(intent);
    }
}