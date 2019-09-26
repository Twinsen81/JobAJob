package com.evartem.jobajob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
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

    private lateinit var loginFeature: LoginFeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginFeature = FeatureInjector.loginFeature()

        DaggerViewModelFactoryComponent.create().inject(this)

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
