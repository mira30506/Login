package mx.com.edlosproyect.ui.base

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint





@AndroidEntryPoint
open class BaseActivity : AppCompatActivity(){
    private val loader by lazy { LoaderDialog() }
    var isOnLandscape = false
    private var isLoaderVisible = false

    fun showLoader() {
        try {
            val loaderDialog = supportFragmentManager.findFragmentByTag("Loader") as? LoaderDialog
            val isShowing = loader.dialog?.isShowing ?: false
            if (loaderDialog != null && loaderDialog.isAdded || isShowing) {
                return
            }

            if (!isLoaderVisible && !loader.isAdded && !loader.isVisible && !isShowing && !isOnLandscape) {
                val newLoader = LoaderDialog()
                newLoader.show(supportFragmentManager, "Loader")
                supportFragmentManager.executePendingTransactions()
                isLoaderVisible = true
            }
        } catch (e: Exception) {
        }
    }

    fun dismissLoader() {
        val loaderDialog = supportFragmentManager.findFragmentByTag("Loader") as? LoaderDialog
        if (loaderDialog != null && loaderDialog.isAdded) {
            if (loaderDialog.isResumed) {
                loaderDialog.dismiss()
            } else {
                loaderDialog.dismissAllowingStateLoss()

            }
            isLoaderVisible = false
        }
    }

    fun showAlert(error: String, code: String, action: (() -> Unit)) {
        val dialog = InfoDialog().apply {
            txtMessageAlert = error
            codeMessage=code
            actionFuntion=action
        }
        dialog.isCancelable = false
        dialog.show(
            this.supportFragmentManager,
            "AlertDialog"
        )
    }
}