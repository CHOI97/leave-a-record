package com.example.leave_a_record.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.leave_a_record.CourseListItem;
import com.example.leave_a_record.R;

import java.util.ArrayList;

/**
 * Created by USER on 2018-06-25.
 */

public class CourseListAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<CourseListItem> listViewItemList = new ArrayList<CourseListItem>() ;

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }
    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_course_list, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView courseTitle = (TextView) convertView.findViewById(R.id.course_txt_title) ;
        TextView courseDate = (TextView) convertView.findViewById(R.id.course_txt_date) ;
        AppCompatImageView courseIcon = (AppCompatImageView) convertView.findViewById(R.id.course_img_icon
        ) ;
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        CourseListItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        courseTitle.setText(listViewItem.getTitle());
        courseDate.setText(listViewItem.getDate());
        courseIcon.setImageResource(listViewItem.getIcon());
//        horizontal_graph.setLayoutParams(params);

        return convertView;
    }

    public void clear(){
        listViewItemList.clear();
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title, String date, int img) {
        CourseListItem item = new CourseListItem();
        item.setTitle(title);
        item.setDate(date);
        item.setIcon(img);

        listViewItemList.add(item);
    }
}
