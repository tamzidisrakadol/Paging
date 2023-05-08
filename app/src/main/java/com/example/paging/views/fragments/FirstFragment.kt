package com.example.paging.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging.R
import com.example.paging.adapter.QuoteAdapter
import com.example.paging.databinding.FragmentFirstBinding
import com.example.paging.viewmodel.QuoteViewModel


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var quoteViewModel: QuoteViewModel


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        //       quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        //       val adapter = QuoteAdapter()
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//        binding.recyclerView.adapter = adapter
//        quoteViewModel.list.observe(viewLifecycleOwner, Observer {
//            adapter.submitData(lifecycle,it)
//        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}