package com.example.githubviewer.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubviewer.R
import com.example.githubviewer.Repository.Repository

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>() {

    private val repositories: MutableList<Repository> = mutableListOf()
    private var onItemClick: ((Repository) -> Unit)? = null

    fun setOnItemClickListener(listener: (Repository) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    fun submitList(newRepositories: List<Repository>) {
        repositories.clear()
        repositories.addAll(newRepositories)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repositoryName: TextView = itemView.findViewById(R.id.textViewRepoName)

        fun bind(repository: Repository) {
            repositoryName.text = repository.name

            itemView.setOnClickListener {
                onItemClick?.invoke(repository)
            }
        }
    }
}
