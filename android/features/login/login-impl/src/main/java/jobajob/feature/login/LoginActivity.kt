package jobajob.feature.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.login.di.LoginFeatureComponent
import jobajob.library.navigation.Target
import jobajob.library.navigation.TargetKey
import jobajob.library.navigation.TargetResolver
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    companion object : TargetResolver<TargetKey.Login> {
        private const val EXTRA_LOGIN_EMAIL_ID = "EXTRA_LOGIN_EMAIL_ID"
        override fun getTarget(context: Context, key: TargetKey.Login): Target {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(EXTRA_LOGIN_EMAIL_ID, key.email)
            return Target.IntentTarget(intent)
        }
    }

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
