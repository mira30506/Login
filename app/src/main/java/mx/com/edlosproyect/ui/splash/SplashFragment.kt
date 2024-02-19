package mx.com.edlosproyect.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.com.edlosproyect.sys.utils.Resource
import mx.com.edlosproyect.sys.utils.Status
import mx.com.edlosproyect.data.local.model.ResultsModel
import mx.com.edlosproyect.databinding.FragmentFirst2Binding
import mx.com.edlosproyect.ui.base.BaseActivity
import mx.com.edlosproyect.ui.login.LoginActivity

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val splashViewModel: SplashViewModel by viewModels()
    private var _binding: FragmentFirst2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirst2Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObservers()

    }

    private fun init() {
        splashViewModel.getFacts()
    }

    private fun initObservers() {
        splashViewModel.petition.observe(viewLifecycleOwner, this::resultsPetition)
    }

    private fun resultsPetition(response: Resource<ArrayList<ResultsModel>>) {
        when (response.status) {
            Status.LOADING -> {

            }

            Status.ERROR -> {
                (activity as BaseActivity).showAlert(
                    response.message!!.message,
                    response.message.code
                ) {
                    (activity as BaseActivity).finish()
                }
            }

            Status.SUCCESS -> {
                response.data?.let { splashViewModel.saveFacts(it) }
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                (activity as BaseActivity).finish()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}