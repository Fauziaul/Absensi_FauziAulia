package org.d3if0097.fingger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import org.d3if0097.fingger.databinding.ActivityMainBinding
import org.d3if0097.fingger.model.ResponseLogin
import org.d3if0097.fingger.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var binding :ActivityMainBinding? = null
    private var username : String = " "
    private var password : String = " "
//    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.btnSign.setOnClickListener {
            username = binding!!.txtEmailInput.text.toString()
            password = binding!!.txtPswInput.text.toString()

            when {
                username == "" -> {
                    binding!!.txtEmailInput.error = "Username Tidak Boleh Kosong"
                }
                password == "" -> {
                    binding!!.txtPswInput.error = "Password Tidak Boleh Kosong"
                }
                else ->{
                    binding!!.loading.visibility = View.VISIBLE
                    getData()
                }
            }
        }

//        binding!!.btnSign.setOnClickListener { login() }
//    }
//
//    private fun login() {
//        Log.d("MainActivity", "Tombol diklik!")
//        val username = binding!!.txtEmailInput.text.toString()
//        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(this, R.string.email_Invalid, Toast.LENGTH_LONG).show()
//            return
//        }
//        Log.d("MainActivity", "Tombol diklik!")
//        val pasword = binding!!.txtPswInput.text.toString()
//        if (TextUtils.isEmpty(pasword)) {
//            Toast.makeText(this, R.string.psw_Invalid, Toast.LENGTH_LONG).show()
//            return
//        }
//        getData()


    }



    private fun getData () {
        val api = RetrofitClient().getInstace()
        api.login(username, password).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    if (response.body()?.response == true) {
                        binding!!.loading.visibility = View.GONE
                        startActivity(Intent(this@MainActivity, Beranda::class.java))
                    } else {
                        binding!!.loading.visibility = View.GONE
                        Toast.makeText(
                            this@MainActivity,
                            "login gagal, periksa kembali username dan password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    Toast.makeText(this@MainActivity, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
               Log.d("error", "${t.message}")
            }

        })
    }
}