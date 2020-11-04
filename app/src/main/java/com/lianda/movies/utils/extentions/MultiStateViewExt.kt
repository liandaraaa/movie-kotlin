package com.lianda.movies.utils.extentions

import com.lianda.movies.utils.custom.CustomMultiStateView
import kotlinx.android.synthetic.main.layout_dialog.view.*


fun CustomMultiStateView.showLoadingView(){
    this.viewState = CustomMultiStateView.ViewState.LOADING
}

fun CustomMultiStateView.showEmptyView(icon:Int? = null, title:String? = null, message:String? = null, action:String? = null, actionListener:(()->Unit)? = null){
    this.viewState = CustomMultiStateView.ViewState.EMPTY

    this.getView(CustomMultiStateView.ViewState.EMPTY)?.apply{
        if (icon != null) {
            imgIcon.setImageResource(icon)
        }
        if (title != null) {
            tvTitle.text = title
        }
        if (message != null) {
            tvMessage.text = message
        }
        if (action != null) {
            btnAction.text = action
            btnAction.visible()
        }else{
            btnAction.gone()
        }

        btnAction.onSingleClickListener { actionListener?.invoke() }
    }
}

fun CustomMultiStateView.showErrorView(icon:Int? = null, title:String? = null, message:String? = null, action:String? = null, actionListener:(()->Unit)? = null){
    this.viewState = CustomMultiStateView.ViewState.ERROR

    this.getView(CustomMultiStateView.ViewState.ERROR)?.apply{
        if (icon != null) {
            imgIcon.setImageResource(icon)
        }
        if (title != null) {
            tvTitle.text = title
        }
        if (message != null) {
            tvMessage.text = message
        }
        if (action != null) {
            btnAction.text = action
            btnAction.visible()
            btnAction.onSingleClickListener { actionListener?.invoke() }
        }
    }
}

fun CustomMultiStateView.showContentView(){
    this.viewState = CustomMultiStateView.ViewState.CONTENT
}