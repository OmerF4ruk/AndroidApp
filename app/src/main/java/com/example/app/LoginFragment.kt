package com.example.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.app.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val currentUser=FirebaseAuth.getInstance().currentUser
        if(currentUser!=null){
            val action = LoginFragmentDirections.actionLoginFragmentToHerbFragment()
            findNavController().navigate(action)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupButton.setOnClickListener {
            //kayıt işlemi
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                //kullanıcı oluşturuldu
                val action = LoginFragmentDirections.actionLoginFragmentToHerbFragment()
                findNavController().navigate(action)
            }.addOnFailureListener { exception ->
                //hata var
                Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
        binding.loginButton.setOnClickListener {//girişi işlemi
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToHerbFragment()
                findNavController().navigate(action)
            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
        //
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}