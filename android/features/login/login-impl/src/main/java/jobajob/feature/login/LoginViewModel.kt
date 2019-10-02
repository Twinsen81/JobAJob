package jobajob.feature.login

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(useCase: MyUseCase): ViewModel() {

    fun getButtonLabel() = "Login here"
}