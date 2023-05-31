package com.example.artelista
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.artelista.databinding.ActivityLoginBinding
import com.example.artelista.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        try
        {
            super.onCreate(savedInstanceState)

            binding=ActivitySplashScreenBinding.inflate(layoutInflater)
            val view=binding.root
            setContentView(view)

//Agregamos la referencia al ImageView
            val LogoApp:ImageView = binding.imgArteLista
            val AnimLogo:Animation = AnimationUtils.loadAnimation(this,
                R.anim.anim1)
            LogoApp.startAnimation(AnimLogo)
//Intent para crear instancia de la activity Login
            val intent = Intent(this, Login::class.java)
            AnimLogo.setAnimationListener(object:
                Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }
                override fun onAnimationEnd(animation: Animation?) {
                    startActivity(intent)
                    finish()
                }
                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        }
        catch (e:java.lang.Exception)
        { e.printStackTrace() }
    }
}