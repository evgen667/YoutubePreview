package ru.ypw;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText etUrl;
    Button download;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etUrl = findViewById(R.id.url);
        download = findViewById(R.id.dl);
        image = findViewById(R.id.image);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLink = etUrl.getText().toString();
                if (urlLink.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter Url!!1", Toast.LENGTH_SHORT).show();
                } else {
                    LoadImage loadImage = new LoadImage(image);
                }
            }
        });

    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView image) {
            this.imageView = image;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}