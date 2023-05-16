package hy.himanshu.quuizzer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_up.*

import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth= FirebaseAuth.getInstance()

        btnSignup1.setOnClickListener {
            signupUser()
        }
        btnLogin1.setOnClickListener {
            val intent= Intent(this,LoginUpActivity::class.java)
            startActivity(intent)
            finish()
        }



        }


    private fun signupUser()
    {
        val email= emailadd.text.toString()
        val password= etPassword.text.toString()
        val confirmPassword= etPassword1.text.toString()

        if (email.isBlank()  || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this,"Email and Password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }


        if (password != confirmPassword) {

         Toast.makeText(this,"Password and Confirm Password do not match",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful)
                {
                    //code
                    Toast.makeText(this,"Sign Up successfully!",Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Unable to create User",Toast.LENGTH_SHORT).show()
                }
            }
    }



}