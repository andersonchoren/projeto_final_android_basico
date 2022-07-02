package br.com.andersonchoren.applogin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.com.andersonchoren.applogin.controller.UserController
import br.com.andersonchoren.applogin.databinding.ActivityRegisterBinding
import br.com.andersonchoren.applogin.model.User
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var passwordRepeat: String
    private lateinit var level: String
    private lateinit var gender: String
    private val levels:List<String> = listOf("Administrator","User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spLevel.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,levels)

        binding.spLevel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                level = levels[position]
            }
        }

        binding.btnNewRegister.setOnClickListener { view ->
            email = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            passwordRepeat = binding.edtPasswordRepeat.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && passwordRepeat.isNotEmpty()) {
                if (password == passwordRepeat) {
                    saveUser(view, email, password,gender, level)
                }else{
                    showSnackbar(view = view, message = getString(R.string.label_password_not_combine), context = this, color = R.color.pink_700)
                }
            } else {
                showSnackbar(
                    view = view,
                    message = getString(R.string.label_empty_fields),
                    context = this,
                    R.color.pink_700
                )
            }
        }

        binding.rgGender.setOnCheckedChangeListener {
                radioGroup, index ->
                if (index == R.id.rbMasc)
                    gender = "M"
                else
                    gender = "F"
        }
    }

    private fun saveUser(view: View, email: String, password: String,gender:String,level:String) {
        val repository = UserController(this)
        val user = User(email = email, password = password, level = level, gender = gender)
        val result = repository.insert(user)
        if (result > 0) {
            showSnackbar(
                view = view,
                message = getString(R.string.label_insert_confirm),
                context = this,
                color = R.color.green
            )
        } else {
            showSnackbar(
                view = view,
                message = getString(R.string.label_invalid_informations),
                context = this,
                color = R.color.pink_700
            )
        }
    }

    fun showSnackbar(
        view: View,
        message: String,
        context: Context,
        color: Int
    ) {
        val snackbar = Snackbar.make(this, view, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, color))
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
        snackbar.show()
    }
}