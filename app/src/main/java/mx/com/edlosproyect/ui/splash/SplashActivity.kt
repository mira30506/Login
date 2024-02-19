package mx.com.edlosproyect.ui.splash

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import mx.com.edlosproyect.R
import mx.com.edlosproyect.ui.base.BaseActivity


@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
    }
}