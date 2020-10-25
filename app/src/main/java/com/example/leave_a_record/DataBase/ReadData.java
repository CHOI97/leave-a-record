//package com.example.leave_a_record.DataBase;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//
//import org.w3c.dom.Comment;
//
//public class ReadData {
//    ChildEventListener childEventListener = new ChildEventListener() {
//        @Override
//        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
//            Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
//
//            // A new comment has been added, add it to the displayed list
//            Comment comment = dataSnapshot.getValue(Comment.class);
//
//            // ...
//        }
//
//        @Override
//        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
//            Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
//
//            // A comment has changed, use the key to determine if we are displaying this
//            // comment and if so displayed the changed comment.
//            Comment newComment = dataSnapshot.getValue(Comment.class);
//            String commentKey = dataSnapshot.getKey();
//
//            // ...
//        }
//
//        @Override
//        public void onChildRemoved(DataSnapshot dataSnapshot) {
//            Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
//
//            // A comment has changed, use the key to determine if we are displaying this
//            // comment and if so remove it.
//            String commentKey = dataSnapshot.getKey();
//
//            // ...
//        }
//
//        @Override
//        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
//            Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
//
//            // A comment has changed position, use the key to determine if we are
//            // displaying this comment and if so move it.
//            Comment movedComment = dataSnapshot.getValue(Comment.class);
//            String commentKey = dataSnapshot.getKey();
//
//            // ...
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            Log.w(TAG, "postComments:onCancelled", databaseError.toException());
//            Toast.makeText(mContext, "Failed to load comments.",
//                    Toast.LENGTH_SHORT).show();
//        }
//    };
//    ref.addChildEventListener(childEventListener);
//
//}
