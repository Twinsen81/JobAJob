package jobajob.feature.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.login.di.LoginFeatureComponent
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

internal class LoginActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginFeatureComponent.get().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        btnLogin.text = viewModel.getButtonLabel()
        btnLogin.setOnClickListener { finish() }
    }
}
