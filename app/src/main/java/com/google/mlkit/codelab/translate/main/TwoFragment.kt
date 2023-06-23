package com.google.mlkit.codelab.translate.main

import android.R.attr.bitmap
import android.R.attr.bottom
import android.R.attr.left
import android.R.attr.right
import android.R.attr.top
import android.R.attr.width
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.decodeBitmap
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.mlkit.codelab.translate.R
import com.google.mlkit.codelab.translate.R.*
import kotlin.math.max


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
class TwoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null
    private var param5: String? = null
    private lateinit var canvas : Canvas
    private lateinit var ivCapture : ImageView
    private lateinit var btnBack : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
            param5 = it.getString(ARG_PARAM5)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(layout.fragment_two, container, false)
        ivCapture = view.findViewById(R.id.ivCapture)
        btnBack =  view.findViewById(R.id.back)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val rect = Rect(param2!!.toInt(), param3!!.toInt(),param4!!.toInt(),param5!!.toInt())
        val bitmap : Bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver,param1!!.toUri())
//        val paint = Paint()
//        paint.color = Color.RED
//        paint.style = Paint.Style.STROKE
//        canvas.drawRect(rect, paint)
        val ratioHeight =  bitmap.height.toInt() / param4!!.toInt()
        val ratioWidth = bitmap.width.toInt()/ param5!!.toInt()
        val ratio = max(ratioHeight,ratioWidth)
        Log.d("toanratio" , "$ratioHeight    $ratioWidth   $param4  $param5")
        val height= bitmap.height * 30 / 100
        val width = bitmap.width * 50 / 100
        Log.d("toanrect","${param2!!.toDouble().toInt() }   ${param3} $width   $height  ${bitmap.height.toInt()}  ${bitmap.width.toInt()}")
        val croppedBitmap =
            Bitmap.createBitmap(bitmap, param2!!.toDouble().toInt() * ratio, param3!!.toDouble().toInt() * ratio - param3!!.toDouble().toInt(),width,height)
        Glide.with(requireContext()).load(croppedBitmap).into(ivCapture)
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3 : String,param4 : String,param5 : String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                }
            }
    }
}