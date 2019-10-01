package com.evartem.jobajob

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.evartem.jobajob.di.AppComponent
import jobajob.feature.login.domain.SessionInfo
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainActivityViewModel


    companion object {
        var session: SessionInfo? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppComponent.get().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        tvSessionInfo.text = viewModel.getStartLabel()

        btnNav2Login.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        session?.also {
            tvSessionInfo.text = session.toString()
        }
    }
}
