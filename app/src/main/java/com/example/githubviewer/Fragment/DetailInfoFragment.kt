package com.example.githubviewer.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.githubviewer.R

class DetailInfoFragment : Fragment() {

    private lateinit var textViewRepoName: TextView
    private lateinit var textViewRepoDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_info, container, false)

        textViewRepoName = view.findViewById(R.id.textViewRepoName)
        textViewRepoDescription = view.findViewById(R.id.textViewRepoDescription)

        // Получите данные о репозитории из ViewModel и установите их на соответствующие элементы UI
        // Например, textViewRepoName.text = repo.name
        // textViewRepoDescription.text = repo.description

        return view
    }
}
