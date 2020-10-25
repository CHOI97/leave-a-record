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
//
//
//
//}
////    final StorageReference ref = storageRef.child("images/mountains.jpg");
////uploadTask = ref.putFile(file);
////
////        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
////@Override
////public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
////        if (!task.isSuccessful()) {
////        throw task.getException();
////        }
////
////        // Continue with the task to get the download URL
////        return ref.getDownloadUrl();
////        }
////        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
////@Override
////public void onComplete(@NonNull Task<Uri> task) {
////        if (task.isSuccessful()) {
////        Uri downloadUri = task.getResult();
////        } else {
////        // Handle failures
////        // ...
////        }
////        }
////        });
