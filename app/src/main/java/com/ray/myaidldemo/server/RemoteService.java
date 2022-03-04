package com.ray.myaidldemo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;


import com.ray.myaidldemo.IPersonManager;
import com.ray.myaidldemo.Person;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {

    private static final String TAG="RemoteService";
    private List<Person> mPersons;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"绑定service成功");
        mPersons =new ArrayList<Person>();
        mPersons.add(new Person(0,"Ray","30"));
        return new RemoteBinder();
    }

    private class RemoteBinder extends IPersonManager.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Person> getPersons() throws RemoteException {
            return mPersons;
        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            person.setmUserName("zxy");
            mPersons.add(person);
        }

    }
}
