package com.example.recycler

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler.Adapter.Onclick
import com.example.recycler.Adapter.Recycle_Adapter
import com.example.recycler.Data.Data
import com.example.recycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Onclick {
    lateinit var binding: ActivityMainBinding
    var list = arrayListOf<Data>()
    lateinit var recycleAdapter: Recycle_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        list.add(Data("Sahil",51,"DSA"))
        list.add(Data("Sam",30,"CNS"))
        list.add(Data("ram",49,"Java"))
        recycleAdapter= Recycle_Adapter(list,this)
        binding.RecycleViewItem.layoutManager= LinearLayoutManager(
            this,LinearLayoutManager.HORIZONTAL,false
        )
        binding.RecycleViewItem.adapter=recycleAdapter


        binding.ADDBtn.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_list)
            val name = dialog.findViewById<TextView>(R.id.InputStudentName)
            val rollno = dialog.findViewById<TextView>(R.id.InputRollNo)
            val subject = dialog.findViewById<TextView>(R.id.InputSubject)
            val btn = dialog.findViewById<Button>(R.id.ADD_list)
            dialog.show()

            btn.setOnClickListener {
                val Iname = name.text.toString()
                val IRollno = rollno.text.toString()
                val Isubject = subject.text.toString()

                if (IRollno.isNullOrEmpty() || Iname.isNullOrEmpty() || Isubject.isNullOrEmpty()) {
                    Toast.makeText(this, "Please enter a valid details", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    list.add(Data(Iname, IRollno.toInt(), Isubject))
                    recycleAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }

        }


    }

    override fun update(position: Int) {
      val dialog = Dialog(this)
        dialog.setContentView(R.layout.update)
        val name = dialog.findViewById<EditText>(R.id.InputStudentName)
        val rollno = dialog.findViewById<EditText>(R.id.InputRollNo)
        val subject = dialog.findViewById<EditText>(R.id.InputSubject)
        val btn = dialog.findViewById<Button>(R.id.Update_list)
        dialog.show()
        btn.setOnClickListener{

            val Irollno = rollno.text.toString()
            val Iname = name.text.toString()
            val Isubject = subject.text.toString()
            if(Irollno.isNullOrEmpty() || Iname.isNullOrEmpty() || Isubject.isNullOrEmpty()){
                Toast.makeText(this, "Please enter a valid details", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            else{
            list.set(position,Data(Iname,Irollno.toInt(),Isubject))
            recycleAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        }

    }

    override fun delete(position: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.alert_dialog2)
        val btn = dialog.findViewById<Button>(R.id.Yes)
        val btn2 = dialog.findViewById<Button>(R.id.No)
        dialog.show()

        btn2.setOnClickListener {
            dialog.dismiss()
        }

        btn.setOnClickListener {
            list.removeAt(position)
            recycleAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }
}