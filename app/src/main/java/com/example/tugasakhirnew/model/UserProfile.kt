package com.example.tugasakhirnew.model

data class UserProfile(
	val data: List<DataItem?>? = null,
	val status: Boolean? = null
)

data class DataItem(
	val facebookAcc: Any? = null,
	val address: Any? = null,
	val domicily: Any? = null,
	val profilePic: Any? = null,
	val workLabel: Any? = null,
	val instagramAcc: Any? = null,
	val adress: Any? = null,
	val language: Any? = null,
	val linkedIn: Any? = null,
	val userId: String? = null,
	val aboutMe: Any? = null,
	val twitterAcc: Any? = null,
	val skill: Any? = null,
	val name: String? = null,
	val email: Any? = null,
	val hobby: Any? = null
)

