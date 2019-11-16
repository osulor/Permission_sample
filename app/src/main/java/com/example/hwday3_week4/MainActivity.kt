package com.example.hwday3_week4

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random



class MainActivity : AppCompatActivity(), FragmentA.FragementADelegate, FragmentB.FragmentBDelegate{

    private val LOCATION_PERMISSION_CODE = 555

    override fun collectRandomNumberB(number: Int) {

        fragmentB.displayNumberB(randomNumber)
    }

    override fun collectRandomNumber(number: Int) {
        fragmentA.displayNumber(randomNumber)
    }

    lateinit var fragmentA: FragmentA
    lateinit var fragmentB: FragmentB
    var randomNumber = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permission has already been granted", Toast.LENGTH_SHORT).show()
        } else{
            requestPermissions(arrayOf("android.permission.ACCESS_FINE_LOCATION"), LOCATION_PERMISSION_CODE)
        }

        fragmentA = FragmentA()
        fragmentB = FragmentB()

        openFragments()
        main_button.setOnClickListener {

            randomNumber = Random.nextInt(0,500)
            collectRandomNumber(randomNumber)
            collectRandomNumberB(randomNumber)

        }

    }

    private fun openFragments(){

        supportFragmentManager.beginTransaction()

            .add(R.id.fragmentA_layout,fragmentA)
            .add(R.id.fragmentB_layout,fragmentB)

            .addToBackStack(fragmentA.tag)
            .addToBackStack(fragmentB.tag)

            .commit()

//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragmentB_layout,fragmentB)
//            .addToBackStack(fragmentB.tag)
//            .commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is FragmentA){
            fragment.setDelegate(this)
        }

        if(fragment is FragmentB){
            fragment.setDelegate(this)
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (LOCATION_PERMISSION_CODE == requestCode){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION){
                Toast.makeText(this,"Permission Granted !", Toast.LENGTH_SHORT).show()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        "android.permission.ACCESS_FINE_LOCATION"
                    )
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                        LOCATION_PERMISSION_CODE
                    )
                } else {
                    Toast.makeText(this,"Permission is needed", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}
