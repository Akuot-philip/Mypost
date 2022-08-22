package dev.akuot.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.akuot.mypost.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId = 0
    lateinit var binding: ActivityCommentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostById()
        setUpToolbar()
        fetchpostIdcomments()
    }

    fun obtainPostId() {
        postId = intent.extras?.getInt("POST_ID") ?: 0
    }

    fun fetchPostById() {
        val apiClient = APIClient.buildApiClient(APIinterface::class.java)
        val request = apiClient.getPostById(postId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    var post = response.body()
                    binding.tvPostBody.text = post?.body
                    binding.tvPostTitle.text = post?.title
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }
        })
    }

    fun setUpToolbar() {
     setSupportActionBar(binding.tbhome)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    fun fetchpostIdcomments(){
        var retro =APIClient.buildApiClient((APIinterface::class.java))
        var request=retro.getComments()
        request.enqueue(object:Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    var comment = response.body() ?: emptyList()
                    displayComments(comment)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun displayComments(commentList: List<Comment>){
        val commentsAdabter=commentsAdabter(commentList)
        binding.rvcomments.layoutManager=LinearLayoutManager(this)
        binding.rvcomments.adapter=commentsAdabter

    }
}