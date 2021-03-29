package id.fiqridhan.photoalbum.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import id.fiqridhan.photoalbum.MainActivity;
import id.fiqridhan.photoalbum.R;
import id.fiqridhan.photoalbum.pref.AppPreference;
import id.fiqridhan.photoalbum.pref.PrefKey;
import id.fiqridhan.photoalbum.utils.ActivityUtils;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        mActivity = LoginActivity.this;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPreference.getInstance(mActivity).setBoolean(PrefKey.IS_LOGIN, true);
                ActivityUtils.getInstance().invokeActivity(mActivity, MainActivity.class, true);
            }
        });
    }
}
