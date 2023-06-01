package com.unpas.tubes.ui.anggota

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.unpas.tubes.R

class AnggotaFragment : Fragment() {

    data class Person(val npm: String, val nama: String)

    private val peopleList: List<Person> = listOf(
        Person("183040133", "Andra Bahari"),
        Person("183040026", "Dimas Gilang Nurpamdudi"),
        Person("183040177", "M. Ash Shidiq Alief Al Fawwaz"),
        Person("203040147", "Rudi Haryanto"),
        Person("203040116", "M. Faishal Abriansyah Samsudin")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_anggota, container, false)
        val tableLayout: TableLayout = root.findViewById(R.id.tableLayout)

        for (person in peopleList) {
            val tableRow = TableRow(requireContext())

            val npmCell = createTableCell(person.npm)
            val namaCell = createTableCell(person.nama)

            tableRow.addView(npmCell)
            tableRow.addView(namaCell)

            tableLayout.addView(tableRow)
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        // Menyembunyikan tombol appBarMain
        (requireActivity() as AppCompatActivity).findViewById<FloatingActionButton>(R.id.fab).hide()
    }

    override fun onPause() {
        super.onPause()

        // Menampilkan kembali tombol appBarMain
        (requireActivity() as AppCompatActivity).findViewById<FloatingActionButton>(R.id.fab).show()
    }

    private fun createTableCell(text: String): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        textView.setPadding(65, 16, 16, 16)
        return textView
    }

}