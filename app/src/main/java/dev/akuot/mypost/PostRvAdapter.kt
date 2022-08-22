package dev.akuot.mypost

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.akuot.mypost.databinding.PostListItemBinding

class PostRvAdapter(var postList: List<Post>): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding = PostListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        var posting=PostViewHolder(binding)
        return posting
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost = postList.get(position)
        with(holder.binding){
            tvUserId.text = currentPost.userId.toString()
            tvId.text = currentPost.id.toString()
            tvBody.text = currentPost.body
            tvTitle.text = currentPost.title
            val context =holder.itemView.context
            holder.binding.cvPost.setOnClickListener{
                val intent =Intent(context, CommentsActivity::class.java)
                intent.putExtra("POST_ID", currentPost.id)
                context.startActivity(intent)
            }
        }



    }


    override fun getItemCount(): Int {
        return postList.size
    }
}

class PostViewHolder(var binding: PostListItemBinding): RecyclerView.ViewHolder(binding.root){

}