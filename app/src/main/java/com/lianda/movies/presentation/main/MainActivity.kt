package com.lianda.movies.presentation.main

import com.lianda.movies.R
import com.lianda.movies.base.BaseActivity
import com.lianda.movies.data.preference.AppPreference
import com.lianda.movies.domain.entities.City
import com.lianda.movies.presentation.viewmodel.CityViewModel
import com.lianda.movies.utils.common.ResultState
import com.lianda.movies.utils.constants.PreferenceKeys
import com.lianda.movies.utils.extentions.getDeviceId
import com.lianda.movies.utils.extentions.observe
import com.lianda.movies.utils.extentions.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val cityViewModel:CityViewModel by viewModel()
    private val preference:AppPreference by inject()

    override val layout: Int = R.layout.activity_main

    override fun onPreparation() {
        val deviceId = getDeviceId(this)
        preference.storeString(PreferenceKeys.KEY_DEVICE_ID, deviceId)
    }

    override fun onIntent() {

    }

    override fun onUi() {

    }

    override fun onAction() {

    }

    override fun onObserver() {
//       observe(
//           liveData = cityViewModel.fetchCity(),
//           action = ::manageStateCity
//       )
    }

    private fun manageStateCity(result: ResultState<List<City>>){
        when(result){
            is ResultState.Success ->{
                tvCity.text = result.data.firstOrNull()?.name
            }
            is ResultState.Error ->{
                showToast(message = result.throwable.message?:"Error")
            }
            is ResultState.Failed ->{
                showToast(message = result.message)
            }
            is ResultState.Loading ->{
                showToast(message ="Loading")
            }
        }
    }

}