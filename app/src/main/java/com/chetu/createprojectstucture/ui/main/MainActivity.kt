package com.chetu.createprojectstucture.ui.main

import android.os.Bundle
import com.chetu.createprojectstucture.R
import com.chetu.createprojectstucture.base.BaseActivity
import com.chetu.createprojectstucture.ui.list.ListFragment

class MainActivity : BaseActivity() {

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(R.id.screenContainer, ListFragment()).commit()

    }
}
