package com.example.artelista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.artelista.databinding.ActivityCrearcuentaBinding
import com.google.firebase.auth.FirebaseAuth

class Crearcuenta : AppCompatActivity() {

    private lateinit var binding:ActivityCrearcuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearcuentaBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        val toolbar:Toolbar = binding.tbCrearCuenta
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.strCrear)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.btnCrearCuenta.setOnClickListener{
            if (valida().equals(true))
            {
                addCuentaUsuario()
            }
        }

    }

    fun valida():Boolean{
        try{
            var validaok:Boolean=false
            //validacion de el correo
            if(binding.txtCorreo.text?.length?.equals(0)!!){
                binding.txtCorreo.requestFocus()
                binding.txtCorreo.setError("El correo es un valor requerido")
                return validaok
            }
            //validacion de la contraseña
            if(binding.txtCrearContraseA.text?.length?.equals(0)!!){
                binding.txtCrearContraseA.requestFocus()
                binding.txtCrearContraseA.setError("Debe Ingresar una contraseña")
                return validaok
            }
            //validacion de la confirmacion de la contraseña
            if(binding.txtConfrimarContraseA.text?.length?.equals(0)!!){
                binding.txtConfrimarContraseA.requestFocus()
                binding.txtConfrimarContraseA.setError("Debe Ingresar la confirmacion de la contraseña")
                return validaok
            }

            val strpassword:String=if(binding.txtCrearContraseA.text!= null)
                binding.txtCrearContraseA.text.toString()
            else
                ""
            val strpasswordconfirmar:String=if(binding.txtConfrimarContraseA.text!= null)
                binding.txtConfrimarContraseA.text.toString()
            else
                ""
            if(strpassword.equals(strpasswordconfirmar)==false)
            {
                binding.txtCrearContraseA.requestFocus()
                binding.txtCrearContraseA.setError("La contraseña y la confirmacion no coinciden")
                return validaok
            }
            validaok=true
            return validaok
        }catch (e:Exception){
            e.message?.let{Log.e("Error en valida",it)};
            return false;
        }
    }
    fun addCuentaUsuario()
    {
        val firebaseAuth:FirebaseAuth=FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(binding.txtCorreo.text.toString(),
        binding.txtCrearContraseA.text.toString())
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"El Usuario ha sido creado.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"El Usuario no ha sido creado.",Toast.LENGTH_SHORT).show();
                }
            }
    }
}