package mx.com.edlosproyect.sys.utils

import mx.com.edlosproyect.data.local.model.ResultsModel

interface OnClickResult {
    fun setOnClickListener(result: ResultsModel)
}