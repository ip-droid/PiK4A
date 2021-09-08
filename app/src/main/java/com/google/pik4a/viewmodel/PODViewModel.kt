package com.google.pik4a.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.pik4a.BuildConfig
import com.google.pik4a.R
import com.google.pik4a.repository.PODRetrofitImpl
import com.google.pik4a.repository.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PODViewModel(private val liveDataToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
                   private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()
): ViewModel() {
    fun getLiveData():LiveData<PictureOfTheDayData>{
        return liveDataToObserve
    }

    fun sendServerRequest(){
        liveDataToObserve.postValue(PictureOfTheDayData.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            //
        }else{
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(
                object : Callback<PODServerResponseData> {
                    override fun onResponse(
                        call: Call<PODServerResponseData>,
                        response: Response<PODServerResponseData>
                    ) {
                        if(response.isSuccessful && response.body()!=null){
                            liveDataToObserve.postValue(PictureOfTheDayData.Success(response.body() as PODServerResponseData))
                        }else{
                            R.drawable.ic_baseline_error_24
                        }
                    }

                    override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                        R.drawable.ic_baseline_error_24
                    }

                }
            )
        }
    }
}