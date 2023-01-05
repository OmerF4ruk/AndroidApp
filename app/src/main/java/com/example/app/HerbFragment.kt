package com.example.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.databinding.FragmentHerbBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import android.content.Intent as Intent1

class HerbFragment :Fragment(){
    private var _binding: FragmentHerbBinding? = null
    private val binding get() = _binding!!

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter:HerbRecyclerAdapter
    private  var herbs=ArrayList<Herb>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHerbBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newHerbButton.setOnClickListener {
         val action = HerbFragmentDirections.actionHerbFragmentToNewHerbFragment()
           Navigation.findNavController(view).navigate(action)
        }
        adapter= HerbRecyclerAdapter()
        binding.herbRecycler.adapter=adapter
        binding.herbRecycler.layoutManager=LinearLayoutManager(requireContext())



        firestore.collection("Herbs")
            .addSnapshotListener { value, error ->
                if(error!==null){
                    Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
                }else{
                    if(value!=null){
                        if(value.isEmpty){
                            Toast.makeText(requireContext(), "mesaj yok", Toast.LENGTH_LONG).show()
                        }else{
                            val documents= value.documents
                            herbs.clear()
                           for(document in documents){

                                val name =document.get("name") as String
                                val date =document.get("date") as String

                                val herb= Herb(name,date)
                                herbs.add(herb)
                                adapter.herbs=herbs
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}