package mx.com.edlosproyect.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.com.edlosproyect.sys.utils.Resource
import mx.com.edlosproyect.sys.utils.Status
import mx.com.edlosproyect.R
import mx.com.edlosproyect.databinding.FragmentFirstBinding
import mx.com.edlosproyect.sys.utils.ErrorMessage
import mx.com.edlosproyect.ui.base.BaseActivity
import mx.com.edlosproyect.ui.facts.FactsActivity


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModels()
    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
        onTextChanged()

    }

    private fun initListeners() {
        binding.btnLoginWithGoogle.setOnClickListener {
            (activity as LoginActivity).signInGoogle()
        }
        binding.btnLogin.setOnClickListener {
            if (verificationEmailAndPassword())
                loginViewModel.getLogin(
                    binding.tilEmail.editText?.text.toString(),
                    binding.tilPassword.editText?.text.toString()
                )

        }
    }

    private fun initObservers() {
        loginViewModel.loginLiveData.observe(viewLifecycleOwner, this::getLogin)
    }

    private fun getLogin(response: Resource<Boolean>) {
        when (response.status) {
            Status.SUCCESS -> {
                (activity as BaseActivity).dismissLoader()
                Toast.makeText(context,context?.getString(R.string.Login_success),Toast.LENGTH_SHORT).show()
                (activity as BaseActivity).finish()
                val intent= Intent(context, FactsActivity::class.java)
                startActivity(intent)
                (activity as BaseActivity).finish()
            }

            Status.LOADING -> {
                (activity as BaseActivity).showLoader()
            }

            Status.ERROR -> {
                (activity as BaseActivity).dismissLoader()
                (activity as BaseActivity).showAlert(
                    response.message!!.message,
                    response.message!!.code
                ) {}
            }
        }
    }

    private fun onTextChanged() {
        binding.tilEmail.editText?.doOnTextChanged { text, _, _, _ ->
            when {
                text.isNullOrEmpty() -> {
                    binding.tilEmail.error = ErrorMessage.EMAIL_IS_EMPTY.s
                }

                !Patterns.EMAIL_ADDRESS.matcher(text).matches() -> {
                    binding.tilEmail.error = ErrorMessage.INVALID_EMAIL.s
                }

                else -> {
                    binding.tilEmail.error = ""
                }
            }
        }
        binding.tilPassword.editText?.doOnTextChanged { text, _, _, _ ->
            when {
                text.isNullOrEmpty() -> {
                    binding.tilPassword.error = ErrorMessage.PASSWORD_IS_EMPTY.s
                }

                text.length < 8 -> {
                    binding.tilPassword.error = ErrorMessage.CHARACTERS_PASSWORD.s
                }

                else -> {
                    binding.tilPassword.error = ""
                }
            }
        }
    }

    private fun verificationEmailAndPassword(): Boolean {
        val email = binding.tilEmail.editText?.text.toString()
        val password = binding.tilPassword.editText?.text.toString()
        var valid = true
        when {
            email.isNullOrEmpty() -> {
                binding.tilEmail.error = ErrorMessage.EMAIL_IS_EMPTY.s
                valid = false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tilEmail.error = ErrorMessage.INVALID_EMAIL.s
                valid = false
            }

            password.isNullOrEmpty() -> {
                binding.tilPassword.error = ErrorMessage.PASSWORD_IS_EMPTY.s
                valid = false
            }

            password.length < 8 ->{
                binding.tilPassword.error = ErrorMessage.CHARACTERS_PASSWORD.s
            }
        }
        return valid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}