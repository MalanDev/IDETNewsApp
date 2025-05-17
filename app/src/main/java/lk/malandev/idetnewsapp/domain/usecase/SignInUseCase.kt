package lk.malandev.idetnewsapp.domain.usecase

import com.google.firebase.auth.FirebaseUser
import lk.malandev.idetnewsapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email:String, password: String):Result<FirebaseUser>{
        if(email.isBlank() || password.isBlank()){
            return Result.failure(Exception("Email and password cannot be empty!"))
        }

        return authRepository.signIn(email,password)
    }
}