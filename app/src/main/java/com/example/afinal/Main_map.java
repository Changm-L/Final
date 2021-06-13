package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main_map extends AppCompatActivity implements OnMapReadyCallback {
    HashMap<String, LatLng> list = new HashMap<>();
    ArrayList<LatLng> locationList = new ArrayList<>();
    Button search, reset;
    TextView tv;
    EditText edit;
    String name = "";
    GoogleMap myMap;
    double lat = 0, lon = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        search = (Button) findViewById(R.id.search);
        reset = (Button)findViewById(R.id.reset);
        tv = (TextView) findViewById(R.id.textView);
        edit = (EditText) findViewById(R.id.editTextTextPersonName);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://openapi.seoul.go.kr:8088/456d44417463686139346966477247/xml/SeoulPublicLibraryInfo/1/500/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getLatLng(response);
                BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.lbrymarker);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
                for (LatLng location : locationList
                ) {
                    myMap.addMarker(new MarkerOptions()
                            .position(location)
                            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))

                            .title("도서관"));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.lbrymarker);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(bitmap, 150, 150, false);

                myMap.clear();

                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(list.get(edit.getText().toString()), 14));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(list.get(edit.getText().toString()))
                        .title(edit.getText().toString())
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                myMap.addMarker(markerOptions);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMap.clear();
                BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.lbrymarker);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
                for (LatLng location : locationList
                ) {
                    myMap.addMarker(new MarkerOptions()
                            .position(location)
                            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                            .title("도서관"));

                }
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        UiSettings uiSettings = googleMap.getUiSettings();
        myMap = googleMap;
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.56644, 126.97792),14));
        uiSettings.setZoomControlsEnabled(true);


    }


    public void getLatLng(String data) {
        try {
            Log.d("===========", data);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(data));

            int eventType = xpp.getEventType();
            boolean latFlag = false, lanFlag = false, nameFlag = false;

            int count = 0;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                int i = 0;
                Log.d("와일 시작>>>>>>>", data);
                if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("LBRRY_NAME")) nameFlag = true;
                    if (xpp.getName().equals("XCNTS")) latFlag = true;
                    if (xpp.getName().equals("YDNTS")) lanFlag = true;
                } else if (eventType == XmlPullParser.TEXT) {
                    if (latFlag) {
                        lat = Double.parseDouble(xpp.getText());
                        Log.d("lat===== ", xpp.getText());
                        latFlag = false;
                        count++;
                    } else if (lanFlag) {
                        lon = Double.parseDouble(xpp.getText());
                        Log.d("long===== ", xpp.getText());
                        lanFlag = false;
                        count++;
                    } else if (nameFlag) {
                        name = xpp.getText();
                        Log.d("name===== ", xpp.getText());
                        nameFlag = false;
                        count++;

                    }
                }
                if (count == 3) {
                    LatLng latLng = new LatLng(lat, lon);

                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                    list.put(name, latLng);
                    locationList.add(latLng);
                    count = 0;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {

        }
        Log.i("passing", "파싱완료");

    }
}