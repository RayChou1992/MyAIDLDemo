package com.ray.myaidldemo

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

     private val TAG="AIDLCLIENT"
    private val BIND_SERVICE_ACTION="android.intent.action.RESPOND_AIDL_MESSAGE"

    private  var iPersonManager:IPersonManager?=null

    private lateinit var mConnectButton:Button
    private lateinit var mAcquireButton:Button
    private lateinit var mAddButton:Button
    private lateinit var tv:TextView

    private var mUsername: String? = null
    private var mUserage: String? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mConnectButton=findViewById(R.id.button1)
        mAcquireButton=findViewById(R.id.button2)
        mAddButton=findViewById(R.id.button3)
        tv=findViewById(R.id.tv)

        mConnectButton.setOnClickListener {
            bindService()
        }
        mAcquireButton.setOnClickListener {
            iPersonManager?.let {
                iPersonManager!!.persons?.let {
                    var persons=""
                    for (person in iPersonManager?.persons!!){

                       persons="$persons${person.getmUserName()},${person.getmUserAge()}\n"

                    }
                    tv.text=persons
                }


            }
        }
        mAddButton.setOnClickListener {
            var person=Person(1,"lyh","26")
            iPersonManager?.addPerson(person)
            Log.e(TAG,person.getmUserName())
        }


    }

    private fun bindService() {
        val intent=Intent()
        intent.action = BIND_SERVICE_ACTION
//        intent.component = ComponentName("com.ray.aidlserver", "com.ray.myaidldemo.RemoteService");


        bindService( achieveExplicitFromImplicitIntent(this,intent),serviceConnection, Context.BIND_AUTO_CREATE)



    }
    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            tv.text="连接成功"
            iPersonManager = IPersonManager.Stub.asInterface(iBinder)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            iPersonManager = null
        }
    }

    private fun achieveExplicitFromImplicitIntent(context: Context, implicitIntent: Intent): Intent? {
        // Retrieve all services that can match the given intent
        val pm = context.packageManager
        val resolveInfo = pm.queryIntentServices(implicitIntent, 0)

        // Make sure only one match was found
        if (resolveInfo.size != 1) {
            return null
        }

        // Get component info and create ComponentName
        val serviceInfo = resolveInfo[0]
        val packageName = serviceInfo.serviceInfo.packageName
        val className = serviceInfo.serviceInfo.name
        Log.d(TAG, "packageName = $packageName")
        Log.d(TAG, "className = $className")
        val component = ComponentName(packageName, className)
        val explicitIntent = Intent(implicitIntent)
        explicitIntent.component = component
        return explicitIntent
    }
}