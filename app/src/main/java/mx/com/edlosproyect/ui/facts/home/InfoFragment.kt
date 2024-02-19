package mx.com.edlosproyect.ui.facts.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import mx.com.edlosproyect.databinding.FragmentFactsInfoBinding



@AndroidEntryPoint
class InfoFragment : Fragment() {

    private var _binding: FragmentFactsInfoBinding? = null
    private val databinding get() = _binding!!

    private val viewModel:HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFactsInfoBinding.inflate(inflater, container, false)
        return databinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun init(){
        val Id=arguments?.getString("ID")
        viewModel.getFact(Id!!)
        viewModel.resultLiveData.observe(viewLifecycleOwner){
            databinding.model=it
        }
        databinding.btShare.setOnClickListener { sendData() }
    }



    private fun sendData() {
        var mensaje = "id : " + databinding.model!!.Id +
                " \n datainsert : " + databinding.model!!.dateInsert +
                "\n slug : " + databinding.model!!.slug +
                "\n columns : " + databinding.model!!.columns +
                "\n fact : " + databinding.model!!.fact +
                "\n organization : " + databinding.model!!.organization +
                "\n resource : " + databinding.model!!.resource +
                "\n url : " + databinding.model!!.url +
                "\n operations : " + databinding.model!!.operations +
                "\n dataset : " + databinding.model!!.operations +
                "\n create_at:" + databinding.model!!.createdAt.toString()

        var intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.`package` = "com.whatsapp"
        if (databinding.model != null) {
            intent.putExtra(
                Intent.EXTRA_TEXT, mensaje
            )
        }
        try {
            requireActivity().startActivity(intent)
        } catch (e: Exception) {
            view?.let {
                Snackbar.make(
                    it,
                    "El dispositivo no tiene WhatsApp instalado",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}

