package mx.com.edlosproyect.ui.base

import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import mx.com.edlosproyect.R
import mx.com.edlosproyect.databinding.InfoDialogBinding

class InfoDialog : DialogFragment() {
    var txtMessageAlert: String? = null
    var codeMessage: String? = null
    var actionFuntion :(()-> Unit)?=null
    var listener: OnClickListener? = null
    private lateinit var binding: InfoDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(0) as Drawable)
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        binding = InfoDialogBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setInitMessage()
    }

    private fun setInitMessage() {
        binding.txtMessage.text = txtMessageAlert
        binding.errorCode.text =  codeMessage
    }

    private fun setListeners() {
        binding.btnAccept.setOnClickListener {
            listener?.onClick(DialogInterface.BUTTON_POSITIVE)
            actionFuntion?.invoke()
            dismiss()
        }
    }

    interface OnClickListener {
        fun onClick(which: Int)
    }
}