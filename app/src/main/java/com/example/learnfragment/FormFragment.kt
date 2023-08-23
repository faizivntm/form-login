package com.example.learnfragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_activity, container, false)


        val buttonLogin = view.findViewById<Button>(R.id.ButtonLogin)
        buttonLogin.setOnClickListener {
            val namaEditText = view.findViewById<EditText>(R.id.nama)
            val emailEditText = view.findViewById<EditText>(R.id.email)
            val jurusanEditText = view.findViewById<EditText>(R.id.jurusan)
            val semesterEditText = view.findViewById<EditText>(R.id.semester)

            val nama = namaEditText.text.toString()
            val email = emailEditText.text.toString()
            val jurusan = jurusanEditText.text.toString()
            val semester = semesterEditText.text.toString()

            if (isValidEmail(email) && isAllFieldsFilled(nama, email, jurusan, semester)) {
                val detailFragment = DetailFragment()

                val args = Bundle()
                args.putString("nama", nama)
                args.putString("email", email)
                args.putString("jurusan", jurusan)
                args.putString("semester", semester)
                detailFragment.arguments = args

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, detailFragment)
                    .addToBackStack(null)
                    .commit()



            } else {
                if (!isValidEmail(email)) {
                    showInvalidEmailDialog()
                } else if (!isAllFieldsFilled(nama, email, jurusan, semester)) {
                    showFieldsEmptyDialog()
                }
            }
        }

        return view
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isAllFieldsFilled(nama: String, email: String, jurusan: String, semester: String): Boolean {
        return nama.isNotEmpty() && email.isNotEmpty() && jurusan.isNotEmpty() && semester.isNotEmpty()
    }

    private fun showInvalidEmailDialog() {
        val builder = AlertDialog.Builder(
            context
        )
        builder.setTitle("Validasi Gagal")
        builder.setMessage("Email tidak sesuai dengan format yang benar.")
        builder.setPositiveButton("Tutup") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showFieldsEmptyDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Validasi Gagal")
        builder.setMessage("Mohon isi semua field dengan benar.")
        builder.setPositiveButton("Tutup") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}
