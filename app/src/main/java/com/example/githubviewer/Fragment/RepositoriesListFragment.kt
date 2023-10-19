package com.example.githubviewer.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubviewer.Adapter.RepositoriesAdapter
import com.example.githubviewer.R

class RepositoriesListFragment : Fragment() {

    private lateinit var recyclerViewRepositories: RecyclerView
    private val adapter = RepositoriesAdapter() // Создайте адаптер для списка репозиториев

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repositories_list, container, false)

        recyclerViewRepositories = view.findViewById(R.id.recyclerViewRepositories)
        recyclerViewRepositories.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewRepositories.adapter = adapter

        // Установите обработчик нажатия на элемент списка, если необходимо
        adapter.setOnItemClickListener { repository ->
            // Обработка нажатия на элемент списка, например, навигация к деталям репозитория
        }

        // Получите список репозиториев из ViewModel и установите его в адаптер
        // Например, adapter.submitList(repositories)

        return view
    }
}
