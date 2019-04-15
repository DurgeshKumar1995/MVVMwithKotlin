package com.durgesh.mvvm_pattern.ui.main

import android.os.Bundle
import com.durgesh.mvvm_pattern.R
import com.durgesh.mvvm_pattern.base.BaseActivity
import com.durgesh.mvvm_pattern.ui.list.ListFragment

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
