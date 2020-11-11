package com.bingyan.pixcelcity.main.fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.NormalFragment;

import java.io.InputStream;

public class MapFragment extends NormalFragment {

    MapView mMapView = null;
    Bundle bundle;


    MyLocationStyle myLocationStyle;

    AMap aMap;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main_map;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;

    }

    @Override
    protected void initViews(View mContentView) {
        mMapView = (MapView) mContentView.findViewById(R.id.mapView);
        mMapView.onCreate(bundle);
        aMap = mMapView.getMap();
        aMap.setCustomMapStyle(
                new CustomMapStyleOptions()
                        .setEnable(true)
                        .setStyleData(readFileFromAssets(myContext, null, "style.data"))
                        .setStyleExtraData(readFileFromAssets(myContext, null, "style_extra.data"))
        );
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setMyLocationButtonEnabled(true);

        myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000);

//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.addOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.d("MAPTAG", "location = " + location.toString());
            }
        });


//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_icon));
//        myLocationStyle.anchor(0.5f,1.0f);

        aMap.moveCamera(CameraUpdateFactory.zoomTo(6));


        LatLng latLng = new LatLng(30.516069887367035, 114.40788330019095);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng)
                .draggable(false)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_icon));
        aMap.addMarker(markerOptions);


        LatLng latLng2 = new LatLng(31.516069887367035, 117.40788330019095);
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(latLng2)
                .draggable(false)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_icon));
        aMap.addMarker(markerOptions2);


        LatLng latLng3 = new LatLng(31.016069887367035, 110.40788330019095);
        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(latLng3)
                .draggable(false)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_icon));
        aMap.addMarker(markerOptions3);
    }

    @Override
    public void switchTitle(String title) {

    }

    @Override
    protected void refreshState(boolean isEdit, boolean isShow) {

    }


    public static byte[] readFileFromAssets(Context context, String groupPath, String filename) {
        byte[] buffer = null;
        AssetManager am = context.getAssets();
        try {
            InputStream inputStream = null;
            if (groupPath != null) {
                inputStream = am.open(groupPath + "/" + filename);
            } else {
                inputStream = am.open(filename);
            }

            int length = inputStream.available();
            buffer = new byte[length];
            inputStream.read(buffer);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return buffer;

    }


}
