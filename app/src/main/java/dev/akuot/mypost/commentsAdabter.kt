package dev.akuot.mypost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.akuot.mypost.databinding.CommentsListItemBinding

class commentsAdabter (var commentList: List<Comment>):RecyclerView.Adapter<CommentsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
      var binding =CommentsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComment=commentList.get(position)
        with(holder.binding){
            tvcommentId.text=currentComment.id.toString()
            tvcommentsEmail.text=currentComment.email
            tvcommentsBody.text=currentComment.body
            tvcommentsName.text=currentComment.name
            tvcommentspostId.text=currentComment.postId.toString()
        }
    }

    override fun getItemCount(): Int {
        return commentList.size

    }
}

class CommentsViewHolder(var binding:CommentsListItemBinding):RecyclerView.ViewHolder(binding.root){

}