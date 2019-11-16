package com.example.hwday3_week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragement_b_layout.*

class FragmentB : Fragment(){

    lateinit var fragmentBDelegate: FragmentBDelegate

    interface FragmentBDelegate{
        fun collectRandomNumberB(number: Int)
    }

    fun setDelegate(fragmentBDelegate: FragmentBDelegate){
        this.fragmentBDelegate = fragmentBDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragement_b_layout,container,false)
    }

   fun displayNumberB(number: Int){
       fragmentB_textView.text = number.toString()
   }

}