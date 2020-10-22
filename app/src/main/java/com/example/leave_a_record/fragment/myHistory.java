package com.example.leave_a_record.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.example.leave_a_record.Adapter.HistoryListAdapter;

import com.example.leave_a_record.R;
import com.example.leave_a_record.post_data_image;

import java.util.ArrayList;

/**
 * "내 기록" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */

public class myHistory extends Fragment {



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String pd_datas_receive;
        Intent intent=getActivity().getIntent();
        View view = inflater.inflate(R.layout.item_myhistory, container, false);
        Log.d("success", "myHistory"); //로그찍기
        ArrayList<String> img=new ArrayList<>();
        pd_datas_receive=intent.getStringExtra("first-image");// 커스텀 아답타 생성
        img.add(pd_datas_receive);
        HistoryListAdapter adapter = new HistoryListAdapter(
                getContext(),
                R.layout.item_history_list,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터

        GridView gv = view.findViewById(R.id.gridview);
        ViewCompat.setNestedScrollingEnabled(gv,true);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        return view;
    }
}
