package com.portifolio.kquiz.core.providers.impl

import android.content.Context
import androidx.annotation.StringRes
import com.portifolio.kquiz.core.providers.StringProvider

class AndroidStringProvider(
    private val context: Context
): StringProvider {

    override fun getString(@StringRes stringResId: Int): String {

        return context.getString(stringResId)
    }
}