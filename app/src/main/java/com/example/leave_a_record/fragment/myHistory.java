package com.example.leave_a_record.fragment;

import android.os.Bundle;
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

        int img[] = {
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background
        };

        // 커스텀 아답타 생성
        HistoryListAdapter adapter = new HistoryListAdapter (
                getContext(),
                R.layout.history_list_item,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터

        GridView gv = view.findViewById(R.id.gridView);
        ViewCompat.setNestedScrollingEnabled(gv,true);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        return view;
    }
}
