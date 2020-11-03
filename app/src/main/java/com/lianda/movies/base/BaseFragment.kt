package com.lianda.movies.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment :Fragment(){

    abstract val layout:Int

    abstract fun onPreparation()
    abstract fun onIntent()
    abstract fun onUi()
    abstract fun onAction()
    abstract fun onObserver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onPreparation()
        onIntent()
        onUi()
        onAction()
        onObserver()
    }

}