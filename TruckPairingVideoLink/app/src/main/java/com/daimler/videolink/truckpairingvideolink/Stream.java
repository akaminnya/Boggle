package com.daimler.videolink.truckpairingvideolink;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;
/**
 * Created by MINPHAN on 4/6/2016.
 */



public class Stream extends Activity{

    ProgressDialog pDialog;
    WebView browser;

    // Insert your Video URL
    String mediaURL = "http://dtna:aenafta@192.168.100.90/axis-cgi/mjpg/video.cgi";
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.stream);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Find your VideoView in your video_main.xml layout


        browser = (WebView) findViewById(R.id.WebView);
        // Execute StreamVideo AsyncTask



        final ProgressDialog pd = ProgressDialog.show(Stream.this, "", "Please wait, while the video stream is loading", true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setLoadWithOverviewMode(true);


        browser.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
                //browser.setInitialScale(300);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        browser.loadUrl(mediaURL);

        url = (EditText) findViewById(R.id.url);
        url.setText(mediaURL);




       final Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener(){
        int pressed = 0;

            @Override
            public void onClick(View v) {
                //first time clicks pause

                if(pressed == 0){
                    browser.onPause();
                    b1.setText("Play");
                    pressed =1;
                }
                else{
                    browser.onResume();
                    b1.setText("Pause");
                    pressed = 0;
                }



            }
        });










        /*
        // Create a progressbar
        pDialog = new ProgressDialog(Stream.this);
        // Set progressbar title
        pDialog.setTitle("Video stream");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();





        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(Stream.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(mediaURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }




        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();

                videoview.start();
            }
        });
*/
    }

    public void start(View view){

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(url.getWindowToken(), 0);

        browser.loadUrl(url.getText().toString());
    }



}
