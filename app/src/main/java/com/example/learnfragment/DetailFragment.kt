package com.example.learnfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.learnfragment.R.id.detailTextView

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_activity, container, false)

        val nama = arguments?.getString("nama")
        val email = arguments?.getString("email")
        val jurusan = arguments?.getString("jurusan")
        val semester = arguments?.getString("semester")

        val detailTextView = view.findViewById<TextView>(R.id.detailTextView)
        val detailText = "Nama: $nama\nEmail: $email\nJurusan: $jurusan\nSemester: $semester"
        detailTextView.text = detailText

        return view
    }
}
