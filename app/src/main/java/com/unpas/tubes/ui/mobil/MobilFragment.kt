package com.unpas.tubes.ui.mobil

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.unpas.tubes.R

class MobilFragment : Fragment() {

    data class Person(val merk: String, val model: String, val bahanBakar: String, val dijual: String, val deskripsi: String)

    private val peopleList: List<Person> = listOf(
        Person("Mitsubishi", "Xpander", "Bensin", "Ya", "Ini Xpander"),
        Person("Hyundai", "Stargazer", "Bensin", "Tidak", "Ini Stargazer"),
        Person("Toyota", "Yaris", "Bensin", "Ya", "Ini Yaris"),
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_mobil, container, false)
        val tableLayout: TableLayout = root.findViewById(R.id.tableLayout)
        val horizontalScrollView: HorizontalScrollView = root.findViewById(R.id.horizontalScrollView)

        for (person in peopleList) {
            val tableRow = TableRow(requireContext())

            val merkCell = createTableCell(person.merk)
            val modelCell = createTableCell(person.model)
            val bahanBakarCell = createTableCell(person.bahanBakar)
            val dijualCell = createTableCell(person.dijual)
            val deskripsiCell = createTableCell(person.deskripsi)

            tableRow.addView(merkCell)
            tableRow.addView(modelCell)
            tableRow.addView(bahanBakarCell)
            tableRow.addView(dijualCell)
            tableRow.addView(deskripsiCell)

            tableLayout.addView(tableRow)
        }

        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab)
        fab.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_mobil, null)

            val merkText = bottomSheetView.findViewById<EditText>(R.id.merkText)
            val modelText = bottomSheetView.findViewById<EditText>(R.id.modelText)
            val bahanBakarText = bottomSheetView.findViewById<EditText>(R.id.bahanBakarText)
            val dijualText = bottomSheetView.findViewById<EditText>(R.id.dijualText)
            val deskripsiText = bottomSheetView.findViewById<EditText>(R.id.deskripsiText)
            val button = bottomSheetView.findViewById<Button>(R.id.mobilButton)

            // Mengambil input teks dari argumen fragment
            val input1 = arguments?.getString("merk")
            val input2 = arguments?.getString("model")
            val input3 = arguments?.getString("bahanBakar")
            val input4 = arguments?.getString("dijual")
            val input5 = arguments?.getString("deskripsi")

            // Mengisi input teks pada EditText
            merkText.setText(input1)
            modelText.setText(input2)
            bahanBakarText.setText(input3)
            dijualText.setText(input4)
            deskripsiText.setText(input5)

            button.setOnClickListener {
                val updatedInput1 = merkText.text.toString()
                val updatedInput2 = modelText.text.toString()
                val updatedInput3 = bahanBakarText.text.toString()
                val updatedInput4 = dijualText.text.toString()
                val updatedInput5 = deskripsiText.text.toString()

                // Lakukan sesuatu dengan nilai updatedInput1 dan updatedInput2 di sini

                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

        return root
    }

    private fun createTableCell(text: String): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        textView.setPadding(72, 16, 16, 16)
        return textView
    }

}