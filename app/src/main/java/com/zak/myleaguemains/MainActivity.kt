package com.zak.myleaguemains

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvChamps: RecyclerView
    private val list = ArrayList<Champ>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        rvChamps = findViewById(R.id.rv_champs)
        rvChamps.setHasFixedSize(true)

        list.addAll(getListChamps())
        showRecyclerList()
    }

    private fun getListChamps(): ArrayList<Champ> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataAlias = resources.getStringArray(R.array.data_alias)
        val dataSplashArt = resources.obtainTypedArray(R.array.data_splashart)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataQuotes = resources.getStringArray(R.array.data_quotes)
        val listChamp = ArrayList<Champ>()
        for (i in dataName.indices) {
            val champ = Champ(dataName[i], dataAlias[i], dataSplashArt.getResourceId(i, -1), dataDescription[i], dataQuotes[i])
            listChamp.add(champ)
        }
        return listChamp
    }

    private fun  showRecyclerList() {
        rvChamps.layoutManager = LinearLayoutManager(this)
        val listChampAdapter = ListChampAdapter(list)
        rvChamps.adapter = listChampAdapter

        listChampAdapter.setOnItemClickCallback(object : ListChampAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Champ) {
                showSelectedChampDetail(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val aboutIntent = Intent(this@MainActivity, AboutPageActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedChampDetail(champ: Champ) {
        val champ = Champ(champ.name, champ.alias, champ.splashart, champ.description, champ.quotes)
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_CHAMP, champ)
        startActivity(moveWithObjectIntent)
    }

}