package com.example.leave_a_record.DataBase;

public class Data_M {
    private static Data_M data_m=new Data_M();
    private UserData userData;

    public Data_M(){
        userData = null;
    }

    public static Data_M getInstance(){
        return data_m;
    }

    public static Data_M getData_M() {
        return data_m;
    }

    public static void setData_M(Data_M data_M) {
        Data_M.data_m = data_M;
    }

    public UserData getUserData() {
        return userData;
    }
    public void setUserData(UserData userData) {
        this.userData = userData;
    }


}
