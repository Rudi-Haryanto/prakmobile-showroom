package com.unpas.tubes.ui.promo

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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.unpas.tubes.R

class PromoFragment : Fragment() {

    data class Person(val model: String, val tanggalAwal: String, val tanggalAkhir: String, val persentase: String)

    private val peopleList: List<Person> = listOf(
        Person("Nmax", "12-05-2023", "17-05-2023", "50%"),
        Person("Beat", "06-06-2023", "10-06-2023", "10%"),
        Person("PCX", "22-05-2023", "27-05-2023", "30%"),
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_promo, container, false)
        val tableLayout: TableLayout = root.findViewById(R.id.tableLayout)
        val horizontalScrollView: HorizontalScrollView = root.findViewById(R.id.horizontalScrollView)

        for (person in peopleList) {
            val tableRow = TableRow(requireContext())

            val modelCell = createTableCell(person.model)
            val tanggalAwalCell = createTableCell(person.tanggalAwal)
            val tanggalAkhirCell = createTableCell(person.tanggalAkhir)
            val persentaseCell = createTableCell(person.persentase)

            tableRow.addView(modelCell)
            tableRow.addView(tanggalAwalCell)
            tableRow.addView(tanggalAkhirCell)
            tableRow.addView(persentaseCell)

            tableLayout.addView(tableRow)
        }

        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab)
        fab.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_promo, null)

            val modelText = bottomSheetView.findViewById<EditText>(R.id.modelText)
            val tanggalAwal = bottomSheetView.findViewById<EditText>(R.id.tanggalAwalText)
            val tanggalAkhir = bottomSheetView.findViewById<EditText>(R.id.tanggalAkhirText)
            val persentase = bottomSheetView.findViewById<EditText>(R.id.persentaseText)
            val button = bottomSheetView.findViewById<Button>(R.id.promoButton)

            // Mengambil input teks dari argumen fragment
            val input1 = arguments?.getString("model")
            val input2 = arguments?.getString("tanggalAwal")
            val input3 = arguments?.getString("tanggalAkhir")
            val input4 = arguments?.getString("persentase")

            // Mengisi input teks pada EditText
            modelText.setText(input1)
            tanggalAwal.setText(input2)
            tanggalAkhir.setText(input3)
            persentase.setText(input4)

            button.setOnClickListener {
                val updatedInput1 = modelText.text.toString()
                val updatedInput2 = tanggalAwal.text.toString()
                val updatedInput3 = tanggalAkhir.text.toString()
                val updatedInput4 = persentase.text.toString()

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