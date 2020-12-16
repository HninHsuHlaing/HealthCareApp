package com.padcx.healthcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.padcx.healthcare.R
import com.padcx.healthcare.mvp.presenter.HomePresenter
import com.padcx.healthcare.mvp.presenter.Impl.HomePresenterImpl
import com.padcx.healthcare.mvp.presenter.Impl.RegisterPresenterImpl
import com.padcx.healthcare.mvp.presenter.RegisterPresenter
import com.padcx.healthcare.mvp.view.HomeView
import com.padcx.healthcare.mvp.view.RegisterView
import com.padcx.shared.activities.BaseActivity
import androidx.navigation.findNavController
import com.padcx.healthcare.fragment.AccountFragment
import com.padcx.healthcare.fragment.ConsultationFragment
import com.padcx.healthcare.fragment.HomeFragment
import com.padcx.healthcare.util.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity : BaseActivity(){
    private lateinit var mPresenter : RegisterPresenter

    private lateinit var mHomePresenter : HomePresenter

//    private val homeFragment = HomeFragment.newInstance("","")
//    private val consultationFragment = ConsultationFragment.newInstance("","")
//    private val accountFragment = AccountFragment.newInstance("","")
//    private val fragmentManager = supportFragmentManager
//    private var activeFragment: Fragment = HomeFragment.newInstance("","")

    companion object{
//        fun newIntent(context:Context) :Intent{
//            return Intent(context,MainActivity::class.java)
//        }
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setupFragmentManager()
//        callFragment(homeFragment)
//        setUpBottomNav()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

       // Log.d("device id", "bearer ${SessionManager.patient_device_id}")
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        navView.setupWithNavController(navController)

    }
//    private fun setupFragmentManager() {
//        fragmentManager.beginTransaction().apply {
//            add(R.id.container,homeFragment,getString(R.string.home_fragment)).hide(homeFragment)
//            add(R.id.container,consultationFragment,getString(R.string.consultation_fragment)).hide(consultationFragment)
//            add(R.id.container,accountFragment,getString(R.string.account_fragment)).hide(accountFragment)
//        }.commit()
//    }
//    private fun setUpBottomNav(){
//        nav_view.setOnNavigationItemSelectedListener(object :
//                BottomNavigationView.OnNavigationItemSelectedListener{
//            override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                when(item.itemId){
//                    R.id.home -> {
//                        callFragment(homeFragment)
//                        return true
//                    }
//                    R.id.message ->{
//                        callFragment(consultationFragment)
//                        return true
//                    }
//                    R.id.account -> {
//                        callFragment(accountFragment)
//                        return true
//                    }
//                }
//                return false
//            }
//        })
//
//    }
//    fun callFragment(fragment: Fragment){
//        fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
//        activeFragment = fragment
//    }


}