package com.dicoding.aplikasidaftarbinatang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.aplikasidaftarbinatang.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //foto diri, nama, dan email pada halaman About
        binding.imgProfile.setImageResource(R.drawable.lintar)
        binding.tvName.text = "Lintar Rezha Candra Krisna"
        binding.tvEmail.text = "lintarrezha7@gmail.com"
    }
}
