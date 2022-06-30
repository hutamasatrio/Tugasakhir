package com.example.tugasakhirnew.model

import com.google.gson.annotations.SerializedName

data class UserProfile(

	@SerializedName("data")
	val `data`: List<DataProfile>,
	@SerializedName("status")
	val status: Boolean

)



