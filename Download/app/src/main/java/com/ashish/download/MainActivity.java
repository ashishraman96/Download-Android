package com.ashish.download;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    EditText eT;
    ImageView imView;
    String imageUrl="http://ashishraman81.esy.es/";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        eT= (EditText)findViewById(R.id.editText);
        Button bt3= (Button)findViewById(R.id.button);
        bt3.setOnClickListener(getImgListener);
        imView = (ImageView)findViewById(R.id.imageView);
    }

    View.OnClickListener getImgListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View view) {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                downloadFile(imageUrl + eT.getText().toString() + ".jpg");
                Log.i("im url", imageUrl + eT.getText().toString() + ".jpg");
            }
        }

    };


    Bitmap bmImg;
    void downloadFile(String fileUrl){


            try {
                URL myFileUrl= new URL(fileUrl);
            HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            int length = conn.getContentLength();
            int[] bitmapData =new int[length];
            byte[] bitmapData2 =new byte[length];
            InputStream is = conn.getInputStream();

            bmImg = BitmapFactory.decodeStream(is);
            imView.setImageBitmap(bmImg);
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

