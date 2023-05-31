package com.example.artelista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.artelista.databinding.ActivityCrearcuentaBinding
import com.example.artelista.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        val firebaseAuth:FirebaseAuth=FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener({v->
            if (validarUsuario().equals(true)){
                firebaseAuth.signInWithEmailAndPassword(binding.txtUsuario.text.toString(),
                    binding.txtContraseA.text.toString()).addOnCompleteListener(this, OnCompleteListener<AuthResult>{ task->
                    if (task.isSuccessful){
                        startActivity(Intent(this, Activity_menu::class.java))
                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        binding.txtUsuario.setText("")
                        binding.txtUsuario.setText("")
                    }else{
                        Toast.makeText(this,"El Usuario y Clave no existen.",Toast.LENGTH_SHORT).show();
                    }
                })
            }
        })

        val tvCrearCuenta: TextView = binding.btncrear
        tvCrearCuenta.setOnClickListener({ v ->
            val intent = Intent(v.getContext(), Crearcuenta::class.java)
            startActivity(intent)
        })
    }
    fun validarUsuario():Boolean{
        try {
            var validaok:Boolean=false
            if(binding.txtUsuario.text?.length?.equals(0)!!){
                binding.txtUsuario.requestFocus()
                binding.txtUsuario.setError("Debe ingresar su correo electronico.")
                return validaok
            }
            if(binding.txtContraseA.text?.length?.equals(0)!!){
                binding.txtContraseA.requestFocus()
                binding.txtContraseA.setError("Debe ingresar una contraseña.")
                return validaok
            }
            validaok=true
            return validaok
        }catch (e:Exception){
            e.message?.let{Log.e("Error en valida",it)};
            return false;
        }
    }
}
