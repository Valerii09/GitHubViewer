package com.example.githubviewer.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.githubviewer.R

class AuthFragment : Fragment() {

    private lateinit var editTextUsername: EditText
    private lateinit var buttonSignIn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)

        editTextUsername = view.findViewById(R.id.editTextUsername)
        buttonSignIn = view.findViewById(R.id.buttonSignIn)

        buttonSignIn.setOnClickListener {
            val username = editTextUsername.text.toString()
            if (username.isNotEmpty()) {
                // Вызов метода авторизации с использованием ViewModel
                // Например, authViewModel.onSignButtonPressed(username)
            } else {
                Toast.makeText(requireContext(), "Введите имя пользователя", Toast.LENGTH_SHORT).show()
            }
        }

        // Добавьте обработку других элементов UI и логику, если необходимо

        return view
    }
}
