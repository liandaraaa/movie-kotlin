package com.lianda.movies.utils.extentions

import android.widget.Toast
import com.lianda.movies.base.BaseActivity

fun BaseActivity.showToast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}