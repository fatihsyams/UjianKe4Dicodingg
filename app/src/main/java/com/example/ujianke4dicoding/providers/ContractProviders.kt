package com.example.ujianke4dicoding.providers

import android.net.Uri

class ContractProviders {
    companion object {
        val AUTHORITY = "com.example.ujianke4dicoding"
        val CONTENT_PATH = "resultsitem"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
        val ALL_ITEMS = 1
        val ITEMS = 2
    }
}
