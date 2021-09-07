package com.google.pik4a.viewmodel

import com.google.pik4a.repository.PODServerResponseData

sealed class PictureOfTheDayData {
    data class Success (val serverResponseData: PODServerResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
//    data class Loading(val progress: Int) : PictureOfTheDayData()
    object Loading : PictureOfTheDayData()
}
