package lk.malandev.idetnewsapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import lk.malandev.idetnewsapp.domain.model.User
import lk.malandev.idetnewsapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth):AuthRepository {
    override suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
      try{
          val result = auth.signInWithEmailAndPassword(email,password).await()
          result.user?.let {
              return Result.success(it)
          }
          return Result.failure(Exception("Authentication failed!"))
      }catch (ex:Exception){
            return Result.failure(ex)
      }
    }

    override suspend fun signUp(email: String, password: String): Result<FirebaseUser> {
        try{
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            result.user?.let {
                return Result.success(it)
            }
            return Result.failure(Exception("Authentication failed!"))
        }catch (ex:Exception){
            return Result.failure(ex)
        }
    }

    override suspend fun signOut() :Result<Unit>{
        try{
            auth.signOut()
            return Result.success(Unit)
        }catch (ex:Exception){
            return Result.failure(ex)
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
       return auth.currentUser;
    }
}