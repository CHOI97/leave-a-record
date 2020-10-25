//package com.example.leave_a_record.Adapter;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.leave_a_record.CourseListItem;
//import com.example.leave_a_record.PinCourseListItem;
//import com.example.leave_a_record.R;
//
//import com.example.leave_a_record.image_edit_data;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by USER on 2018-06-25.
// */
//
//public class PinCourseListAdapter extends RecyclerView.Adapter<PinCourseListAdapter.MyViewHolder> {
//    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
//    Context mContext;
//    List<PinCourseListItem> listViewItemList = new ArrayList<PinCourseListItem>() ;
//
////    List<image_edit_data> imageditdataList;
//    public PinCourseListAdapter(Context mContext, List<PinCourseListItem> listViewItemList) {
//        this.mContext=mContext;
//        this.listViewItemList=listViewItemList;
//    }
//
//
//    @Override
//    public PinCourseListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.page_edit,parent,false);
//        MyViewHolder vh =new MyViewHolder(view);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PinCourseListAdapter.MyViewHolder holder, int position) {
//        PinCourseListItem listViewItem =listViewItemList.get(position);
//        Log.d("date_time item : ",listViewItem.getDate_time());
//        holder.date_time.setText(listViewItem.getDate_time());
//        Log.d("date_time : ","listViewItem.getDate_time()===============================");
//
////        holder.edit_iv.setImageURI(imageditdata.getUri()); //test를위해
////        holder.edit_date_time.setText(imageditdata.getDate_time());
//
//        ////////test code
////        holder.edit_iv.setImageResource(user.getEdit_iv());
//        ////////
////        holder.edit_Text_date.setText(user.getEdit_Text_date());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        Log.d("list view Item siez ", Integer.toString(listViewItemList.size()));
//        return listViewItemList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        //        //        TextInputEditText edit_title;
//////        RadioButton p1,p2,p3,p4,p5;
////         CardView cardview;
//         private TextView date_time;
////        TextView couse_time;
////        TextView place1, place2;
////        TextView start,finish;
////        TextView viewline;
////         ImageView airplane;
////        //        TextInputEditText Text_content;
//////        Button save;
////        public MyViewHolder(@NonNull View itemView) {
////            super(itemView);
////
////            cardview= itemView.findViewById(R.id.cardview);
////            date_time= itemView.findViewById(R.id.TextTripData);
////            couse_time= itemView.findViewById(R.id.TextTripTime);
////            place1=itemView.findViewById(R.id.detailStart);
////            place2=itemView.findViewById(R.id.detailFinish);
////            start=itemView.findViewById(R.id.textStart);
////            finish=itemView.findViewById(R.id.textFinish);
////            viewline=itemView.findViewById(R.id.viewLine);
////            airplane=itemView.findViewById(R.id.airplane);
////
////
//////            p1 =  itemView.findViewById(R.id.edit_Pin1);
//////            p2= itemView.findViewById(R.id.edit_Pin2);
//////            p3=itemView.findViewById(R.id.edit_Pin3);
//////            p4=itemView.findViewById(R.id.edit_Pin4);
//////            p5=itemView.findViewById(R.id.edit_Pin5);
////
////
////
////
////
////        }
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.date_time=(TextView)itemView.findViewById(R.id.TextTripData);
//
////            p1 =  itemView.findViewById(R.id.edit_Pin1);
////            p2= itemView.findViewById(R.id.edit_Pin2);
////            p3=itemView.findViewById(R.id.edit_Pin3);
////            p4=itemView.findViewById(R.id.edit_Pin4);
////            p5=itemView.findViewById(R.id.edit_Pin5);
////            edit_iv= itemView.findViewById(R.id.edit_iv);
////            edit_date_time=itemView.findViewById(R.id.edit_date_time);
////            edit_content=itemView.findViewById(R.id.edit_content);
//
//
//        }
//    }
//}
//    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
////
////    public int getCount() {
////        return listViewItemList.size() ;
////    }
////    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
////
////    public View getView(int position, View convertView, ViewGroup parent) {
////        final int pos = position;
////        final Context context = parent.getContext();
////
////        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
////        if (convertView == null) {
////            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////            convertView = inflater.inflate(R.layout.item_cardview, parent, false);
////        }
//////        private CardView cardview;
//////        private String date_time;
//////        private String couse_time;
//////        private String place1, place2;
//////        private String start,finish;
//////        private View viewline;
//////        private ImageView airplane;
////        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
////        CardView cardView = (CardView) convertView.findViewById(R.id.cardview);
////        TextView date_time = (TextView) convertView.findViewById(R.id.TextTripData);
////        TextView course_time = (TextView) convertView.findViewById(R.id.TextTripTime);
////        TextView place1 = (TextView) convertView.findViewById(R.id.detailStart);
////        TextView place2 = (TextView) convertView.findViewById(R.id.detailFinish);
////        TextView start = (TextView) convertView.findViewById(R.id.textStart);
////        TextView finish = (TextView) convertView.findViewById(R.id.textFinish);
////        View viewline = (View) convertView.findViewById(R.id.viewLine);
////        ImageView airplane = (ImageView) convertView.findViewById(R.id.airplane);
////
////        // 예시
//////        TextView courseTitle = (TextView) convertView.findViewById(R.id.course_txt_title) ;
//////        TextView courseDate = (TextView) convertView.findViewById(R.id.course_txt_date) ;
//////        AppCompatImageView courseIcon = (AppCompatImageView) convertView.findViewById(R.id.course_img_icon
//////        ) ;
//////
////        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
////        PinCourseListItem listViewItem = listViewItemList.get(position);
////
////        // 아이템 내 각 위젯에 데이터 반영
//////        cardView(listViewItem.getCardview());
////        date_time.setText(listViewItem.getDate_time());
////        course_time.setText(listViewItem.getCouse_time());
////        place1.setText(listViewItem.getPlace1());
////        place2.setText(listViewItem.getPlace2());
////        start.setText(listViewItem.getStart());
////        finish.setText(listViewItem.getFinish());
//////        viewline.setViewline(listViewItem.getViewline());
////        airplane.setImageResource(listViewItem.getAirplane());
////
//////        courseTitle.setText(listViewItem.getTitle());
//////        courseDate.setText(listViewItem.getDate());
//////        courseIcon.setImageResource(listViewItem.getIcon());
////
//////        horizontal_graph.setLayoutParams(params);
////
////        return convertView;
////    }
////
////    public PinCourseListAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
////        View view= LayoutInflater.from(mContext).inflate(R.layout.item_cardview,parent,false);
////
////        return new PinCourseListAdapter.MyViewHolder(view);
////    }
//////    public USERAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//////        View view= LayoutInflater.from(mContext).inflate(R.layout.page_edit,parent,false);
//////
//////        return new USERAdapter.UserViewHolder(view);
//////    }
////    @Override
//////    public void onBindViewHolder(@NonNull USERAdapter.UserViewHolder holder, int position) {
//////        image_edit_data imageditdata = imageditdataList.get(position);
//////        holder.edit_iv.setImageURI(imageditdata.getUri()); //test를위해
//////        holder.edit_date_time.setText(imageditdata.getDate_time());
//////
//////        ////////test code
////////        holder.edit_iv.setImageResource(user.getEdit_iv());
//////        ////////
////////        holder.edit_Text_date.setText(user.getEdit_Text_date());
//////
//////    }
////    public void onBindViewHolder(@NonNull PinCourseListAdapter.MyViewHolder holder, int position) {
////        holder.date_time.setText("pin");
////    }
////
////
////    public void clear(){
////        listViewItemList.clear();
////    }
////
////    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
////
////    public long getItemId(int position) {
////        return position ;
////    }
////
////    @Override
////    public int getItemCount() {
////        return 0;
////    }
////
////    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
////
////    public Object getItem(int position) {
////        return listViewItemList.get(position) ;
////    }
////
////    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
//////    public void addItem(CardView cardview,String date_time,String couse_time,String place1,String place2,String start,String finish,View viewline,ImageView airplane) {
////    public void addItem(String date_time,String couse_time,String place1,String place2,String start,String finish,int airplane) {
////        PinCourseListItem item = new PinCourseListItem();
//////        item.setCardview(cardview);
////        item.setDate_time(date_time);
////        item.setCouse_time(couse_time);
////        item.setPlace1(place1);
////        item.setPlace2(place2);
////        item.setStart(start);
////        item.setFinish(finish);
//////        item.setViewline(viewline);
////        item.setAirplane(airplane);
////
////
////        listViewItemList.add(item);
////    }
////
////    public class MyViewHolder extends RecyclerView.ViewHolder {
////        //        TextInputEditText edit_title;
//////        RadioButton p1,p2,p3,p4,p5;
////         CardView cardview;
////         TextView date_time;
////        TextView couse_time;
////        TextView place1, place2;
////        TextView start,finish;
////        TextView viewline;
////         ImageView airplane;
////        //        TextInputEditText Text_content;
//////        Button save;
////        public MyViewHolder(@NonNull View itemView) {
////            super(itemView);
////
////            cardview= itemView.findViewById(R.id.cardview);
////            date_time= itemView.findViewById(R.id.TextTripData);
////            couse_time= itemView.findViewById(R.id.TextTripTime);
////            place1=itemView.findViewById(R.id.detailStart);
////            place2=itemView.findViewById(R.id.detailFinish);
////            start=itemView.findViewById(R.id.textStart);
////            finish=itemView.findViewById(R.id.textFinish);
////            viewline=itemView.findViewById(R.id.viewLine);
////            airplane=itemView.findViewById(R.id.airplane);
////
////
//////            p1 =  itemView.findViewById(R.id.edit_Pin1);
//////            p2= itemView.findViewById(R.id.edit_Pin2);
//////            p3=itemView.findViewById(R.id.edit_Pin3);
//////            p4=itemView.findViewById(R.id.edit_Pin4);
//////            p5=itemView.findViewById(R.id.edit_Pin5);
////
////
////
////
////
////        }
////    }
////}
