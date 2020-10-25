package com.example.leave_a_record.fragment;

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

import com.example.leave_a_record.HistoryListAdapter;

import com.example.leave_a_record.R;

/**
 * "내 기록" 탭을 눌렀을때 나오는 화면에 대한 java 코드
 */
public class myHistory extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myhistory, container, false);
        Log.d("success", "myHistory"); //로그찍기
        int img[] = {
                R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_1,
                R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_1,
                R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_1,
                R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_1,
                R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_1,
                R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_1
        };

        // 커스텀 아답타 생성
        HistoryListAdapter adapter = new HistoryListAdapter(
                getContext(),
                R.layout.history_list_item,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터

        GridView gv = view.findViewById(R.id.gridview);
        ViewCompat.setNestedScrollingEnabled(gv,true);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        return view;
    }
}
