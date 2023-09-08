package com.dicoding.aplikasidaftarbinatang

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.aplikasidaftarbinatang.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAnimals: RecyclerView
    private val list = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAnimals = binding.rvAnimals
        rvAnimals.setHasFixedSize(true)

        list.addAll(getListAnimals())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                // tindakan yang akan diambil saat menu "About" diklik
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_list -> {
                rvAnimals.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvAnimals.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_share -> {
                shareAnimal()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun shareAnimal() {
        // memeriksa apakah anima tidak null
        val animal = intent.getParcelableExtra<Animal>("key_animal")
        if (animal != null) {
            val shareText = "Nama: ${animal.name}\nDeskripsi: ${animal.description}"
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun getListAnimals(): ArrayList<Animal> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAnimal = ArrayList<Animal>()
        for (i in dataName.indices) {
            val animal = Animal(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listAnimal.add(animal)
        }
        return listAnimal
    }

    private fun showRecyclerList() {
        rvAnimals.layoutManager = LinearLayoutManager(this)
        val listAnimalAdapter = ListAnimalAdapter(list)
        rvAnimals.adapter = listAnimalAdapter

        listAnimalAdapter.setOnItemClickCallback(object : ListAnimalAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Animal) {
                showSelectedAnimal(data)
            }
        })
    }

    private fun showSelectedAnimal(animal: Animal) {
        Toast.makeText(this, "Anda memilih " + animal.name, Toast.LENGTH_SHORT).show()
    }

}
