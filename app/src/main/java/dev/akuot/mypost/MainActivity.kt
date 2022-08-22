package dev.akuot.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.akuot.mypost.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
    }

    fun fetchPosts(){
        var apiClient = APIClient.buildApiClient(APIinterface::class.java)
        var request=apiClient.getPosts()

        request.enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    var posts = response.body()
                    Toast.makeText(baseContext,"fetched ${posts!!.size} post ",Toast.LENGTH_LONG).show()
                    var postRv =PostRvAdapter(posts)
                    binding.rvPost.layoutManager=LinearLayoutManager(this@MainActivity)
                    binding.rvPost.adapter=postRv
                }
            }



            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}