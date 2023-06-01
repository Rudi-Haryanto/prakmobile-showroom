package com.unpas.tubes.ui.motor

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
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.unpas.tubes.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MotorFragment : Fragment() {

    //data class MotorData(val id: Int, val model: String, val warna: String, val kapasitas: Int, val tanggal_rilis: Date, val harga: String)

//    private val peopleList: List<Person> = listOf(
//        Person("Mitsubishi", "Xpander", "Bensin", "Ya", "Ini Xpander"),
//        Person("Hyundai", "Stargazer", "Bensin", "Tidak", "Ini Stargazer"),
//        Person("Toyota", "Yaris", "Bensin", "Ya", "Ini Yaris"),
//    )

    private var currentId = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_motor, container, false)
        val tableLayout: TableLayout = root.findViewById(R.id.tableLayout)
        val horizontalScrollView: HorizontalScrollView = root.findViewById(R.id.horizontalScrollView)

        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab)
        fab.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetView = inflater.inflate(R.layout.bottom_sheet_motor, container, false)

            val modelText = bottomSheetView.findViewById<EditText>(R.id.modelText)
            val warnaText = bottomSheetView.findViewById<EditText>(R.id.warnaText)
            val kapasitasText = bottomSheetView.findViewById<EditText>(R.id.kapasitasText)
            val tanggalRilisText = bottomSheetView.findViewById<EditText>(R.id.tanggalRilisText)
            val hargaText = bottomSheetView.findViewById<EditText>(R.id.hargaText)
            val button = bottomSheetView.findViewById<Button>(R.id.motorButton)

            // Mengambil input teks dari argumen fragment
            val input1 = arguments?.getString("model")
            val input2 = arguments?.getString("warna")
            val input3 = arguments?.getInt("kapasitas")
            val input4 = arguments?.getString("tanggal_rilis")
            val input5 = arguments?.getInt("harga")
            val tanggalRilis: Date? = if (input4 != null) SimpleDateFormat("yyyy-MM-dd").parse(input4) else null

            // Mengisi input teks pada EditText
            modelText.setText(input1)
            warnaText.setText(input2)
            kapasitasText.setText(input3.toString())
            tanggalRilisText.setText(tanggalRilis.toString())
            hargaText.setText(input5.toString())

            button.setOnClickListener {
                val updatedInput1 = modelText.text.toString()
                val updatedInput2 = warnaText.text.toString()
                val updatedInput3 = kapasitasText.text.toString().toInt() // Mengubah tipe data String menjadi Int
                val updatedInput4 = tanggalRilisText.text.toString() // Tanggal rilis masih dalam bentuk String
                val updatedInput5 = hargaText.text.toString().toInt() // Mengubah tipe data String menjadi Int

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val updatedTanggalRilis = dateFormat.parse(updatedInput4)

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://ppm-api.gusdya.net/api/sepeda-motor/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val motorService = retrofit.create(MotorService::class.java)

                val motorData = MotorData(
                    id = generateNextId(),
                    model = updatedInput1,
                    warna = updatedInput2,
                    kapasitas = updatedInput3,
                    tanggal_rilis = updatedTanggalRilis,
                    harga = updatedInput5
                )

                val call = motorService.sendMotorData(motorData)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            // Proses pengiriman data berhasil
                            Toast.makeText(requireContext(), "Data berhasil dikirim", Toast.LENGTH_SHORT).show()
                        } else {
                            // Proses pengiriman data gagal
                            Toast.makeText(requireContext(), "Gagal mengirim data", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        // Proses pengiriman data gagal
                        Toast.makeText(requireContext(), "Gagal mengirim data", Toast.LENGTH_SHORT).show()
                    }
                })

                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

        return root
    }

    private fun generateNextId(): Int {
        val id = currentId
        currentId++
        return id
    }

    private fun createTableCell(text: String): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        textView.setPadding(72, 16, 16, 16)
        return textView
    }

}