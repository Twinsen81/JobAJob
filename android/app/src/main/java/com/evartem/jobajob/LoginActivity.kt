package com.evartem.jobajob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.evartem.jobajob.di.FeatureInjector
import jobajob.feature.login.di.LoginFeatureApi
import jobajob.feature.login.di.LoginFeatureComponent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginFeature: LoginFeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginFeature = FeatureInjector.loginFeature()

        btnLogin.setOnClickListener { finish() }
    }

    override fun onPause() {
        super.onPause()

        MainActivity.session = loginFeature.getSession()
        LoginFeatureComponent.resetComponent()
    }
}
