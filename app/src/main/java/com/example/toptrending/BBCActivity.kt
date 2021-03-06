package com.example.toptrending


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.item_view.*

class BBCActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bbc_recycler)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()

        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter
    }


    private fun fetchData() {
        val url = "https://saurav.tech/NewsAPI/everything/bbc-news.json"
//        val url = "https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=9485dbed563145c5b58b6800baf4c4be"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("publishedAt")
                    )
                    newsArray.add(news)
                }

                mAdapter.updateNews(newsArray)
            },
            {
                Toast.makeText(this, "Oh Snap!! looks like something is wrong..", Toast.LENGTH_SHORT).show()

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

//    private fun fetchData() {
//        val url = "https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=9485dbed563145c5b58b6800baf4c4be"
//        val jsonObjectRequest = object: JsonObjectRequest(
//            Request.Method.GET,
//            url,
//            null,
//            Response.Listener {
//                val newsJsonArray = it.getJSONArray("articles")
//                val newsArray = ArrayList<News>()
//                for(i in 0 until newsJsonArray.length()) {
//                    val newsJsonObject = newsJsonArray.getJSONObject(i)
//                    val news = News(
//                        newsJsonObject.getString("title"),
//                        newsJsonObject.getString("author"),
//                        newsJsonObject.getString("url"),
//                        newsJsonObject.getString("urlToImage"),
//                        newsJsonObject.getString("description"),
//                        newsJsonObject.getString("publishedAt")
////                        newsJsonObject.getString("source")
//                    )
//                    newsArray.add(news)
//                }
//                mAdapter.updateNews(newsArray)
//            },
//            Response.ErrorListener {
//                Toast.makeText(this, "Error Fetching News", Toast.LENGTH_LONG).show()
//            }
//
//        ) {
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers["User-Agent"] = "Mozilla/5.0"
//                return headers
//            }
//        }
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//    }
//
//
//
//    override fun onItemClicked(item: News) {
//        val builder =  CustomTabsIntent.Builder()
//        val customTabsIntent = builder.build()
//        customTabsIntent.launchUrl(this, Uri.parse(item.url))
//    }
}