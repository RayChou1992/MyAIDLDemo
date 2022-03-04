package com.ray.myaidldemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private int mId;
    private String mUserName;
    private String mUserAge;

    public Person(int mId, String mUserName, String mUserAge) {
        this.mId = mId;
        this.mUserName = mUserName;
        this.mUserAge = mUserAge;
    }

    public Person() {

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserAge() {
        return mUserAge;
    }

    public void setmUserAge(String mUserAge) {
        this.mUserAge = mUserAge;
    }

    protected Person(Parcel in) {
        mUserName = in.readString();
        mUserAge = in.readString();
        mId=in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUserName);
        parcel.writeString(mUserAge);
        parcel.writeInt(mId);
    }
    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        mUserName = dest.readString();
        mUserAge = dest.readString();
        mId = dest.readInt();
    }
}
