package com.necdetzr.loodoscrypto.data.local
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class FirebaseAuthManager(
    private val firebaseAuth:FirebaseAuth =FirebaseAuth.getInstance()
) {
    //Complex Auth Functions
    suspend fun login(email:String,password:String):Result<Unit>{
        return try {
            firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Result.success(Unit)

        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)

        }
    }
    suspend fun register(name:String,surname:String,email:String,password:String):Result<Unit>{
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            val user = result.user
            val fullname = "$name $surname"
            val profileUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(fullname)
                .build()
            user?.updateProfile(profileUpdate)?.await()
            Result.success(Unit)
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }







    //Basic Auth Functions
    fun isUserLoggedIn():Boolean = firebaseAuth.currentUser != null
    fun getCurrentUserId():String? = firebaseAuth.currentUser?.uid
    fun signOut() = firebaseAuth.signOut()

}