package com.portifolio.kquiz.core.providers

import androidx.annotation.StringRes

interface StringProvider {

    fun getString(@StringRes stringResId: Int): String
}