package com.example.leave_a_record.InterfaceActivity;

import android.R.drawable;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.leave_a_record.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.SquareCap;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnPolylineClickListener,GoogleMap.OnPolygonClickListener {


    private LatLng myLocation;
    public ArrayList<LatLng> loc;


    // 권한 체크 요청 코드 정의
    public static final int REQUEST_CODE_PERMISSIONS = 1000;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;


    // 위치 정보 얻는 객체
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_recommend);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
//        actionBar.setDisplayHomeAsUpEnabled(true);


        // GoogleAPIClient의 인스턴스 생성
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                //select logout item
                break;
            case R.id.account:
                //select account item
                break;
            case android.R.id.home:
                //select back button
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        String[] xy = new String[]{"37.221900","127.18800","37.221804","127.186695","37.220000","127.186666","36.08648","128.3573439"};

        loc=new ArrayList<LatLng>();

        int count = 1;
        for (int i=0;i<xy.length;i++){

            double tmp = Double.parseDouble(xy[i]);
            double tmp2 = Double.parseDouble(xy[++i]);


            LatLng latLng = new LatLng(tmp, tmp2);

            loc.add(latLng);


//            mMap.addMarker(new MarkerOptions().position(latLng).title("Pin"+count).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_purple)));
            count++;


        }


        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc.get(loc.size()-1)));



        // 카메라 줌
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        // 인포 윈도우 클릭시 전화 걸기 -> 뭔가 게시물 쓸때 쓸수있을거 같아서 남겨둠
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0312365043"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }

        });




        //arraylist 다시 배열에 넣는거 아직 않마 까먹ㅇ멋어ㅏ
//        LatLng[] line = {
//                loc.get(0),loc.get(1),loc.get(2)
//        };
        //좌표끼리 선 긋기 좌표가 추가 될때마다 새로운 선을 만들어야 하나.. 아니면 그냥 좌표하나씩 추가해야하나 고민
        //일단 좌표에 들어온 순서로 선이 그어짐 -> 시간별로 추가할수 있도록 해야 함


        //좌표 두개마다 각각의 polyline을 생성해야 각각 화살표로 나올수 있음
        //for문 사용해서 polyline 만들어보기
//        for(int i=0; i<line.length;){
//            Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
//                    .clickable(true)
//                    .add(   line[i], line[++i]
//                    ) .width(10)
//
//                    .geodesic(true));
//
//
//        }





    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 체크 거부 됨", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    //두 좌표간 거리 구함 -> 어디다 쓰지? 아직 안씀
//    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
//        double distance = 0;
//        Location locationA = new Location("A");
//        locationA.setLatitude(LatLng1.latitude);
//        locationA.setLongitude(LatLng1.longitude);
//        Location locationB = new Location("B");
//        locationB.setLatitude(LatLng2.latitude);
//        locationB.setLongitude(LatLng2.longitude);
//        distance = locationA.distanceTo(locationB);
//
//        return distance;
//
//    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    public void onLastLocationButtonClicked(View view) {
        // 권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {


                    // 현재 위치
                    myLocation = new LatLng(location.getLatitude(), location.getLongitude());


                    mMap.addMarker(new MarkerOptions()
                            .position(myLocation)
                            .title("현재위치"));



                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

                    // 카메라 줌
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));



                    //반지름 500m 원 그리기
                    CircleOptions circle = new CircleOptions().center(myLocation) //원점
                            .radius(200)      //반지름 단위 : m
                            .strokeWidth(3f)  //선너비 3f
                            ; //배경색
                    mMap.addCircle(circle);

                    //현재위치와 좌표의 거리 구하기


                    for(int i=0;i<loc.size();i++) {

                        //각각의 x,y좌표 Double 형식으로 변환
                        Double nowLatitude=Double.valueOf(myLocation.latitude);
                        Double nowLongitude=Double.valueOf(myLocation.longitude);
                        Double locLatitude=Double.valueOf(loc.get(i).latitude);
                        Double locLongitude=Double.valueOf(loc.get(i).longitude);


                        if (distance(nowLatitude,nowLongitude,locLatitude,locLongitude,"meter")<200) {
                            mMap.addMarker(new MarkerOptions().position(loc.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_purple)));
                        }
                    }

                }
            }
        });
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}