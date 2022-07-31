package com.example.captureimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button capture;
    ImageView Image;

    final static  int capture_req_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capture = findViewById(R.id.main_btn_capture);
        Image = findViewById(R.id.main_imageView);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(intent,capture_req_code);


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == capture_req_code && resultCode == RESULT_OK){
            Bitmap b_image = (Bitmap) data.getExtras().get("data");
            Image.setImageBitmap(b_image);
        } else {
            Toast.makeText(getBaseContext(),"Capture Images Cancelled!",Toast.LENGTH_SHORT).show();
        }
    }
}