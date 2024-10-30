package com.example.endangeredanimals.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Animal (
    @StringRes val name: Int,
    @StringRes val descreption: Int,
    @DrawableRes val picture: Int,
    val link: String
)