package dev.akuot.mypost

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostRvadapter (var context:Context ,var postList: List<Post>): RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding =PostListItembinding
            .inflat
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
    }
}
class PostViewHolder(var binding:PostListItemBinding):RecyclerView.ViewHolder(binding.root)