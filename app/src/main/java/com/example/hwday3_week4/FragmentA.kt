package com.example.hwday3_week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a_layout.*

class FragmentA : Fragment(){

    lateinit var fragementADelegate: FragementADelegate

    fun setDelegate(fragementADelegate: FragementADelegate){
        this.fragementADelegate = fragementADelegate
    }

    interface FragementADelegate{
        fun collectRandomNumber(number: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a_layout,container,false)
    }


    fun displayNumber(number: Int){

        fragmentA_textView.text = number.toString()
    }

}