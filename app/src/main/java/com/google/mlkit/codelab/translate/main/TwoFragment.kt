package com.google.mlkit.codelab.translate.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.mlkit.codelab.translate.R
import com.google.mlkit.codelab.translate.R.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class TwoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var ivCapture : ImageView
    private lateinit var btnBack : Button
    private lateinit var textResult : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(layout.fragment_two, container, false)
        ivCapture = view.findViewById(R.id.ivCapture)
        btnBack =  view.findViewById(R.id.back)
        textResult = view.findViewById(R.id.result)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(param1).into(ivCapture)
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        textResult.text = param2
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}