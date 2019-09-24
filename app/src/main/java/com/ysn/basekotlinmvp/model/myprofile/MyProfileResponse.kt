package com.ysn.basekotlinmvp.model.myprofile

import com.google.gson.annotations.SerializedName

data class MyProfileResponse(
    val phones: List<Phone>,
    val profile: Profile
)

data class Phone(
    val customers: List<Customer>,
    val phone_number: String
)

data class Customer(
    val customer_id: String,
    val subscriptions: List<Subscription>
)

data class Subscription(
    val address: String,
    val package_id: Int,
    val package_name: String
)

data class Profile(
    @SerializedName("can_add_phone") val isCanAddPhone: Boolean,
    val category: String,
    val fields: List<Field>,
    val label: String
)

data class Field(
    val id: Int,
    val key: String,
    val label: String,
    val type: String,
    val value: String
)