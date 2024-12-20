package com.example.room_db

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import com.example.room_db.database.daftarBelanja
import com.example.room_db.database.daftarBelanjaDB
import com.example.room_db.database.historyBelanjaDB

class HistoryBelanja : AppCompatActivity() {
    private lateinit var DB: historyBelanjaDB
    private lateinit var adapterDaftarHistory: AdapterHistory
    private var arDaftar : MutableList<daftarBelanja> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history_belanja)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        DB = historyBelanjaDB.getDatabase(this)
        adapterDaftarHistory = AdapterHistory(arDaftar)
        var _rvDaftarHistory = findViewById<RecyclerView>(R.id.rvDaftarHistory)
        _rvDaftarHistory.layoutManager = LinearLayoutManager(this)
        _rvDaftarHistory.adapter = adapterDaftarHistory
    }
    override fun onStart() {
        super.onStart()
        //Dispatcher Main untuk berhubungan dgn ui, menampilkan data
        CoroutineScope(Dispatchers.Main).async {
            val historyDaftarBelanja = DB.funhistoryBelanjaDAO().selectAll()
            Log.d("data ROOM", historyDaftarBelanja.toString())
            adapterDaftarHistory.isiData(historyDaftarBelanja)
        }
    }
}