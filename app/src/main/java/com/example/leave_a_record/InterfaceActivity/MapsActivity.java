package com.example.leave_a_record.InterfaceActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.leave_a_record.Adapter.PinCourseListAdapter;


import com.example.leave_a_record.Adapter.MyAdapter;
import com.example.leave_a_record.DataBase.Callback;
import com.example.leave_a_record.DataBase.Database_M;
import com.example.leave_a_record.DataBase.PostData;
import com.example.leave_a_record.PinCourseListItem;
import com.example.leave_a_record.R;
import com.example.leave_a_record.post_data_image;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnPolylineClickListener,GoogleMap.OnPolygonClickListener {



    // 권한 체크 요청 코드 정의
    public static final int REQUEST_CODE_PERMISSIONS = 1000;
    ArrayList<PinCourseListItem> PincourseDataArray = new ArrayList<>();
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
//    PostData postdata;
    // 위치 정보 얻는 객체
    private FusedLocationProviderClient mFusedLocationClient;
//    private PinCourseListAdapter pclAdapter;
    private List<PinCourseListItem> pclListItem;

    //recyclerview/////////////////////////////////
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Database_M m=new Database_M();
    ArrayList<String> myDataset=new ArrayList<>();
    ArrayList<String> timer=new ArrayList<>();
    String data_index;
    int index;
    List<Integer> pin_gps=new ArrayList<>();
    List<Integer> pin_index=new ArrayList<>();
    ArrayList<String> gps_la=new ArrayList<>();
    ArrayList<String> gps_lo=new ArrayList<>();

    //    Log.d("받은데이터 확인하기")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent=getIntent();
        data_index=intent.getStringExtra("index");
        index=Integer.parseInt(data_index);

        m.getPosts_i(index, m.getmAuth().getUid(), new Callback<String>() {
                    @Override
                    public void onCallback(String data) {
                        Log.d("getposts_i실행", "실행중");
                        m.getPostdata(data, m.getmAuth().getUid(), new Callback<PostData>() {
                            @Override
                            public void onCallback(PostData data) {
                                pin_count(data.getPost_pin());
                                Log.d("pin_index 값은 ",Integer.toString(pin_index.size()));
                                if (pin_index.size() == 1) {
                                    myDataset.add(Time_convert(data.getPost_meta_datetime().get(0)));
                                    timer.add("One Course");
                                }
                                if (pin_index.size() == 2) {
                                    myDataset.add(Time_convert(data.getPost_meta_datetime().get(0)));
                                    timer.add(Time_counter(data.getPost_meta_datetime().get(pin_index.get(0)), data.getPost_meta_datetime().get(pin_index.get(1))));
                                }
                                if (pin_index.size() >= 3) {
                                    for (int i = 0; i < pin_index.size()-1; i++) {
                                        myDataset.add(Time_convert(data.getPost_meta_datetime().get(pin_index.get(i))));
                                        timer.add(Time_counter(data.getPost_meta_datetime().get(pin_index.get(i)), data.getPost_meta_datetime().get(pin_index.get(i+1))));
                                    }
                                }
                                Log.d("myDataset",Integer.toString(myDataset.size()));
                                Log.d("timer",Integer.toString(timer.size()));
                                Log.d("coures timer", "코스시간업데이트전");
                                mAdapter = new MyAdapter(myDataset,timer);
                                recyclerView.setAdapter(mAdapter);
                            }
                        });
                    }
                });

        pclListItem=new ArrayList<>();
//        pclListItem.add(new PinCourseListItem("2020"));
//        pclListItem.add(new PinCourseListItem("2020"));
//        pclListItem.add(new PinCourseListItem("2030"));
//        pclListItem.add(new PinCourseListItem("2040"));



        Log.d("현재 진행중인 것은", "Recyclerview. ======================================");
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)




       ///////////////////////////////////////////////////////////////////

        Log.d("success", "tripCourse case:success"); //로그찍기

        Log.d("현재 진행중인 것은", "Recyclerview. ======================================find view id");


        //toolbar
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//뒤로가기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // GoogleAPIClient의 인스턴스 생성
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    //toolbar menu_map
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //버튼을 눌렀을때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        m.getPosts_i(index, m.getmAuth().getUid(), new Callback<String>() {
            @Override
            public void onCallback(String data) {
                Log.d("getposts_i실행", "실행중");
                m.getPostdata(data, m.getmAuth().getUid(), new Callback<PostData>() {
                    @Override
                    public void onCallback(PostData data) {
                        gps_count(data.getPost_pin());
                        Log.d("pin_index 값은 ",Integer.toString(pin_gps.size()));
                        if (pin_gps.size() == 1) {
                            gps_la.add(data.getPost_meta_gps_Latitue().get(0));
                            gps_lo.add(data.getPost_meta_gps_Longitude().get(0));
                            Log.d("gps_1a",data.getPost_meta_gps_Latitue().get(0));
                            Log.d("gps_1o",data.getPost_meta_gps_Longitude().get(0));
                        }
                        if (pin_gps.size() == 2) {
                            gps_la.add(data.getPost_meta_gps_Latitue().get(0));
                            gps_lo.add(data.getPost_meta_gps_Longitude().get(0));
                            Log.d("gps_1a",data.getPost_meta_gps_Latitue().get(0));
                            Log.d("gps_1o",data.getPost_meta_gps_Longitude().get(0));

                            gps_la.add(data.getPost_meta_gps_Latitue().get(data.getPost_meta_gps_Latitue().size()-1));
                            gps_lo.add(data.getPost_meta_gps_Longitude().get(data.getPost_meta_gps_Longitude().size()-1));
                        }
                        if (pin_gps.size() >= 3) {
                            for (int i = 0; i < pin_gps.size()-1; i++) {
                                gps_la.add(data.getPost_meta_gps_Latitue().get(i));
                                gps_lo.add(data.getPost_meta_gps_Longitude().get(i));
                                Log.d("gps_1a",data.getPost_meta_gps_Latitue().get(i));
                                Log.d("gps_1o",data.getPost_meta_gps_Longitude().get(i));
                            }
                            gps_la.add(data.getPost_meta_gps_Latitue().get(data.getPost_meta_gps_Latitue().size()-1));
                            gps_lo.add(data.getPost_meta_gps_Longitude().get(data.getPost_meta_gps_Longitude().size()-1));

                        } //좌표 값 배열로 넘겨받음

//                        String[] xy = new String[]{"37.221900","127.18800","37.221800","127.186695","37.220000","127.186555"};


                        ArrayList<LatLng> loc=new ArrayList<LatLng>();

                        int count = 1;
                        //배열로 받은 좌표값을 arraylist에 저장
                        for (int i=0;i<gps_lo.size();i++){

                            double tmp = Double.parseDouble(gps_la.get(i));
                            double tmp2 = Double.parseDouble(gps_lo.get(i));


                            LatLng latLng = new LatLng(tmp, tmp2);

                            loc.add(latLng);


                            //핀추가 메소드
                            mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title("Pin"+count)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin3))
                            );
                            count++;

                        }



                        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc.get(loc.size()-1)));


//        // 서울 위치
//        LatLng seoul = new LatLng(37.566535, 126.97796919);
//        mMap.addMarker(new MarkerOptions().position(seoul).title("Marker in Seoul"));
//
//        // 명지대 위치 추가
//        LatLng MJU = new LatLng(37.221804, 127.186695);
//        mMap.addMarker(new MarkerOptions()
//                .position(MJU)
//                .title("명지대"));
//
//        //핀 연결 확인용 좌표 추가
//        LatLng MJU2 = new LatLng(37.220000, 127.186666);
//        mMap.addMarker(new MarkerOptions()
//                .position(MJU2)
//                .title("명지대2"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(MJU2));

                        // 카메라 줌
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

                        // 인포 윈도우 클릭시 전화 걸기 -> 뭔가 게시물 쓸때 쓸수있을거 같아서 남겨둠
//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:0312365043"));
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//            }
//        });

                        //arraylist 다시 배열에 넣어서 polyline 연결 가능하게 함 -> arraylist는 polyline에서 쓸수 없는거 같아서

                        LatLng[] line = new LatLng[loc.size()];
                        for (int i=0;i<loc.size();i++){
                            line[i]=loc.get(i);
                        }

//        LatLng[] line = {
//                loc.get(0),loc.get(1),loc.get(2)
//        };



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

                        //좌표에 들어온 순서로 선이 그어짐
                        //좌표끼리 연결 line 배열에 각각의 좌표값들 저장되어 있음 -> arraylist는 안되는 것 같음
                        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                                .clickable(true)
                                .add(   line
                                ) .width(10)

                                .color(Color.rgb(94,29,102))
                                .geodesic(true));

                        //polyline 디자인
                        polyline1.setEndCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow), 15));
                        polyline1.setStartCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.ic_circle), 15));


                        //추가 메소드
//                        ArrayList<LatLng> loc=new ArrayList<LatLng>();
//                        int count = 1;
//                        for(int i=0;i<gps_la.size();i++){
//                            double tmp = Double.parseDouble(gps_la.get(i));
//                            double tmp2=Double.parseDouble(gps_lo.get(i));
//                            LatLng latLng = new LatLng(tmp, tmp2);
//
//                            loc.add(latLng);
//
//
//                            //핀추가 메소드
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(latLng)
//                                    .title("Pin"+count)
//                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin3))
//                            );
//                            count++;
//                        }
                    }
                });
            }
        });


//        googleMap.setOnPolylineClickListener(this);
//        googleMap.setOnPolygonClickListener(this);



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
//    }

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
                    LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(myLocation)
                            .title("현재 위치"));


                    String latitudeString = String.valueOf(myLocation.latitude);
                    String longitudeString = String.valueOf(myLocation.longitude);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

                    // 카메라 줌
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));


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
    public String Time_convert(String time){
        String result = time.replaceAll("[^0-9]","");

        String year;
        String month;
        String day;
        String date;

        result=result.substring(0, 8);
        year=result.substring(0,4);
        month=result.substring(4,6);
        day=result.substring(6,8);
        date=year+"."+month+"."+day;
        return date;
    }
    // yyyymmddhhmmss
    public String tag_convert(String time){
        String date;
        String temp;
        temp = time.replaceAll("[^0-9]","");
        String yyyy;
        String MM;
        String dd;
        String HH;
        String mm;
        String ss;
        yyyy=temp.substring(0,4);
        MM=temp.substring(4,6);
        dd=temp.substring(6,8);
        HH=temp.substring(8,10);
        mm=temp.substring(10,12);
        ss=temp.substring(12,14);
        date=yyyy+"."+MM+"."+dd+"."+HH+"."+mm+"."+ss;

        return date;
    }
    public String Time_counter(String tag1,String tag2){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        String tag_start=tag_convert(tag1);
        String tag_end=tag_convert(tag2);
        Log.d("tag1",tag_start);
        Log.d("tag2",tag_end);
        Date d1;
        Date d2;
        try{
            d1=dateFormat.parse(tag_start);
            d2=dateFormat.parse(tag_end);
            Log.d("변경된 시간값",Long.toString(d1.getTime()));
            Log.d("변경된 시간값",Long.toString(d2.getTime()));
        }catch(ParseException e){
            return null;
        }
        long diff=d2.getTime()-d1.getTime();
        long sec=(diff/1000)%60;
        long min=diff/(60 * 1000);
        long hour=diff/(60 * 60 * 1000);
        long day=diff/(24*60 * 60 * 1000);

        String result=" ";
        if(day!=0){
            result=result+day +"일";
        }
        if(hour!=0){
            result=result+hour+"시간";
        }
        if(min!=0){
            result=result+min+"분";
        }
        if(sec!=0){
            result=result+sec+"초";
        }
        Log.d("계산한 코스시간은",result);
        return result;
    }
    public void pin_count(List<String> pin){
        int i=0;
        int j=1;
        pin_index.add(0);
        while(j<pin.size()){
            if(pin.get(i).equals(pin.get(j))){
                j++;
            }
            else if(!pin.get(i).equals(pin.get(j))){
                i=j;
                pin_index.add(j);
                j++;
            }
        }
    }
    public void gps_count(List<String> pin){
        int i=0;
        int j=1;
        pin_gps.add(0);
        while(j<pin.size()){
            if(pin.get(i).equals(pin.get(j))){
                j++;
            }
            else if(!pin.get(i).equals(pin.get(j))){
                i=j;
                pin_gps.add(j);
                Log.d("pin_gps",Integer.toString(pin_gps.size()));
                j++;
            }
        }
    }
//public void onBackPressed() {
//        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
//        }
}

