package com.evartem.jobajob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.evartem.jobajob.di.AppComponent
import com.evartem.jobajob.di.DaggerViewModelFactoryComponent
import com.evartem.jobajob.di.FeatureInjector
import com.evartem.jobajob.di.ViewModelFactory
import jobajob.feature.login.di.LoginFeatureApi
import jobajob.feature.login.di.LoginFeatureComponent
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var features: FeatureInjector
    private lateinit var loginFeature: LoginFeatureComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DaggerViewModelFactoryComponent.create().inject(this)
        loginFeature = features.loginFeatureComponent()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        btnLogin.text = viewModel.getButtonLabel()
        btnLogin.setOnClickListener { finish() }
    }

    override fun onPause() {
        super.onPause()

        MainActivity.session = loginFeature.getSession()
        LoginFeatureComponent.resetComponent()
    }
}
