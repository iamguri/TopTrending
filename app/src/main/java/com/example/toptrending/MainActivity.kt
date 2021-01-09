package com.example.toptrending

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Top Trending in India
        topTrending.setOnClickListener{
            val intent = Intent(this, TopTrendingActivity::class.java)
            startActivity(intent)
        }

//        Top Science in India
        science_icon.setOnClickListener{
            val intent =  Intent(this, ScienceActivity::class.java);
            startActivity(intent);
        }

//        Top Business in India
        business_icon.setOnClickListener{
            val intent = Intent(this, BusinessActivity::class.java)
            startActivity(intent)
        }

//        Top Sports in India
        sports_icon.setOnClickListener{
            val intent = Intent(this, SportsActivity::class.java)
            startActivity(intent)
        }

//        Technology Headlines
        technology_icon.setOnClickListener{
            val intent = Intent(this, TechnologyActivity::class.java)
            startActivity(intent)
        }


//        Top BBC Headlines
        bbc_icon.setOnClickListener{
            val intent = Intent(this, BBCActivity::class.java,)
            startActivity(intent)
        }

//        Times of India HeadLines
        timesofindia_icon.setOnClickListener{
            val intent = Intent(this, TimesOfIndiaActivity::class.java)
            startActivity(intent)
        }

//        Farmer Protest
        farmer_img.setOnClickListener{
//            progress_bar.visibility = View.VISIBLE
            val intent = Intent(this, FarmerActivity::class.java)
            startActivity(intent)
        }

    }
}
