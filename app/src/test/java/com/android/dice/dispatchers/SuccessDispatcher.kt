package com.android.dice.dispatchers

import com.android.dice.utils.FileReader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.requestUrl?.queryParameter("offset")) {
            "0" -> MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("success_response.json"))
            "30" -> MockResponse().setResponseCode(200).setBody(FileReader.readStringFromFile("success_empty_response.json"))
            else -> MockResponse().setResponseCode(400)
        }
    }
}
