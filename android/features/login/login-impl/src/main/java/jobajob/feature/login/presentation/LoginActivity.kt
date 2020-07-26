package jobajob.feature.login.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.login.R

@AndroidEntryPoint
internal class LoginActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_USER_NAME = "EXTRA_USER_NAME"

        fun createIntent(context: Context, userName: String? = null) : Intent =
            Intent(context, LoginActivity::class.java).apply {
                userName?.let{ putExtra(EXTRA_USER_NAME, it)}
            }
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)


    }
}
