package duanvdph37524.fpoly.techstorre.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import duanvdph37524.fpoly.techstorre.LoginActivity;
import duanvdph37524.fpoly.techstorre.R;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ManHinhChao.this, LoginActivity.class));
            }
        },3000);
    }
}