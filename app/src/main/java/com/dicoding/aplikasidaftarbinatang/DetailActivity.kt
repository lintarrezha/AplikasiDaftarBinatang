package com.dicoding.aplikasidaftarbinatang

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private var animal: Animal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ambil data binatang dari Intent
        animal = intent.getParcelableExtra("key_animal")

        // menampilkan data binatang di layout XML
        val imageView: ImageView = findViewById(R.id.imageView)
        val textViewName: TextView = findViewById(R.id.textViewName)
        val textViewDescription: TextView = findViewById(R.id.textViewDescription)

        // Set gambar, nama, dan deskripsi binatang
        animal?.let {
            imageView.setImageResource(it.photo)
            textViewName.text = it.name
            textViewDescription.text = it.description
        }
    }

    // Tombol back activity detail
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                shareAnimal()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun shareAnimal() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Info Binatang")
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Nama: ${animal?.name}\nDeskripsi: ${animal?.description}"
        )
        startActivity(Intent.createChooser(shareIntent, "Bagikan lewat"))
    }
}
