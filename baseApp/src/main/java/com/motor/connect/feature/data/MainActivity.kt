package com.motor.connect.feature.data

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.feature.area.R
import com.feature.area.databinding.ActivityMainBinding
import com.motor.connect.feature.model.AreaModel
import com.motor.connect.feature.setting.SettingActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var spanCount = 1

    private var arae: AreaModel? = null

    private val viewModel = UserViewModel()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Hawk.init(applicationContext).build()


        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = UserAdapter { areaModel ->

            Toast.makeText(this, "=== Item Click  ====  " + areaModel.getAreaName(),
                    Toast.LENGTH_LONG).show()
            Hawk.put("shre", areaModel)
        }

        arae = Hawk.get("shre")

        recyclerView.adapter = adapter
        binding.viewModel = viewModel

        viewModel.startUpdates()


        val actionHome = findViewById<TextView>(R.id.btn_home)
        actionHome?.setOnClickListener {
            Toast.makeText(this, "=== On Click  ====", Toast.LENGTH_LONG).show()
        }

        val actionAdd = findViewById<TextView>(R.id.btn_add)
        actionAdd?.setOnClickListener {
            Toast.makeText(this, "=== On actionAdd  ====", Toast.LENGTH_LONG).show()
        }

        val actionNotify = findViewById<TextView>(R.id.btn_notify)
        actionNotify?.setOnClickListener {
            Toast.makeText(this, "=== On actionNotify  ====", Toast.LENGTH_LONG).show()
        }

        val actionSetting = findViewById<TextView>(R.id.btn_setting)
        actionSetting?.setOnClickListener {
            Toast.makeText(this, "=== On actionSetting  ====", Toast.LENGTH_LONG).show()
            SettingActivity.show(this)
        }


        //=========== Header Item ======================================

        val gridView = findViewById<ImageView>(R.id.grid_view)
        gridView?.setOnClickListener {

            if (spanCount == 1) {
                spanCount = 2
                mLayoutManager = GridLayoutManager(this, spanCount)
                recyclerView.layoutManager = mLayoutManager
            } else {
                spanCount = 1
                mLayoutManager = GridLayoutManager(this, spanCount)
                recyclerView.layoutManager = mLayoutManager
            }
            Toast.makeText(this, "=== On griview       " + spanCount, Toast.LENGTH_LONG).show()
        }

        val profileView = findViewById<ImageView>(R.id.ic_profile)
        profileView?.setOnClickListener {
            Toast.makeText(this, "=== On profileView  ====", Toast.LENGTH_LONG).show()
        }

    }


    override fun onDestroy() {
        viewModel.stopUpdates()
        super.onDestroy()
    }

}
