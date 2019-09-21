package com.evartem.jobajob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jobajob.feature.login.domain.SessionInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var session: SessionInfo? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
