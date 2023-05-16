package hy.himanshu.quuizzer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_up.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginUpActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_up)
        firebaseAuth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            login()
        }
        btnSignUp.setOnClickListener {
            val intent= Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


        private fun login()
        {
            val email= emailadd1.text.toString()
            val password= etPassword11.text.toString()

            if (email.isBlank()  || password.isBlank() ) {
                Toast.makeText(this,"Email and Password can't be blank",Toast.LENGTH_SHORT).show()
                return
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
            {
                if(it.isSuccessful){
                    Toast.makeText(this,"Login successfully!",Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Unable to login",Toast.LENGTH_SHORT).show()
                }
            }
        }

}

