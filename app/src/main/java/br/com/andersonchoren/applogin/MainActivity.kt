package br.com.andersonchoren.applogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import br.com.andersonchoren.applogin.controller.UserController
import br.com.andersonchoren.applogin.databinding.ActivityMainBinding
import br.com.andersonchoren.applogin.model.User
import br.com.andersonchoren.applogin.repository.UserRepository
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnter.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                val repository = UserController(this)
                val user = User(email = email, password = password)
                val result = repository.findOne(user)
                if(result != null){
                    showSnackbar(view = it, message = getString(R.string.label_welcome), context = this, color = R.color.green)
                }else{
                    showSnackbar(view = it, message = getString(R.string.label_invalid_informations), context = this,R.color.pink_700)
                }
            }else{
                showSnackbar(view = it, message = getString(R.string.label_empty_fields), context = this,R.color.pink_700)
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun showSnackbar(
        view:View,
        message:String,
        context: Context,
        color: Int
    ){
        val snackbar = Snackbar.make(this,view,message,Snackbar.LENGTH_INDEFINITE)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context,color))
        snackbar.setAction("OK"){
            snackbar.dismiss()
        }
        snackbar.show()
    }
}