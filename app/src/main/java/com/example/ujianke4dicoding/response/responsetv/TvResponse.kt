package com.example.ujianke4dicoding.response.responsetv

import com.google.gson.annotations.SerializedName

data class TvResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItemss>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)