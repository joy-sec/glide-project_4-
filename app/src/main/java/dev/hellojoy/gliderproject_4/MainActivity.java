package dev.hellojoy.gliderproject_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.opensooq.supernova.gligar.GligarPicker;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {


    private static final int PICKER_REQUEST_CODE = 1001;
    private ImageView imageView;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);







    }

    public void goToCamera(View view) {
        new GligarPicker().requestCode(PICKER_REQUEST_CODE).cameraDirect(true).limit(1).withActivity(this).show();
        Toasty.info(this, "opening camera ", Toast.LENGTH_SHORT, true).show();

    }

    public void fromURL(View view) {
        Glide.with(this).load("https://cdn.maikoapp.com/3d4b/4qhf5/180h.png").into(imageView);
        Toasty.success(this, "loading ", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode){
            case PICKER_REQUEST_CODE : {
                String pathsList[]= data.getExtras().getStringArray(GligarPicker.IMAGES_RESULT); // return list of selected images paths.
                Glide.with(this).load(pathsList[0]).into(imageView);
                break;
            }
        }
    }
}