package com.hall_event_reservation_system.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.hall_event_reservation_system.R;
import com.hall_event_reservation_system.adapters.user_status_events_adapater;
import com.hall_event_reservation_system.databinding.FragmentEventsBinding;
import com.hall_event_reservation_system.databinding.FragmentTodayBinding;
import com.hall_event_reservation_system.models.users_add_events_model;


public class today extends Fragment {
    private WebView mywebView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
     return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mywebView= (WebView) getView().findViewById(R.id.webview);

        mywebView.setWebViewClient(new WebViewClient());
        mywebView.loadUrl("https://www.google.com/maps/place/Taguig,+Metro+Manila/@14.5135378,121.0654025,13z/data=!3m1!4b1!4m5!3m4!1s0x3397cf4a54fe3001:0x2fae7af0a998d2ad!8m2!3d14.5176184!4d121.0508645");
        WebSettings webSettings=mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }
}