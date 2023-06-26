package com.zak.myleaguemains

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.zak.myleaguemains.databinding.ActivityDetailBinding
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CHAMP = "extra_champ"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        val tvChampNameDetail: TextView = findViewById(R.id.tv_champ_name_detail)
        val tvChampAliasDetail: TextView = findViewById(R.id.tv_champ_alias_detail)
        val ivChampDetail: ImageView = findViewById(R.id.iv_champ_detail)
        val tvChampDescription: TextView = findViewById(R.id.tv_champ_description)
        val tvChampQuotes: TextView = findViewById(R.id.tv_champ_quotes)
        val tvChampNameQuotes: TextView = findViewById(R.id.tv_champ_name_quotes)

        val champ = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_CHAMP, Champ::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CHAMP)
        }
        if (champ != null) {
            tvChampNameDetail.text = champ.name
            tvChampAliasDetail.text = champ.alias
            ivChampDetail.setImageResource(champ.splashart)
            tvChampDescription.text = champ.description
            tvChampQuotes.text = champ.quotes
            tvChampNameQuotes.text = " — " + champ.name


        }
    }
}