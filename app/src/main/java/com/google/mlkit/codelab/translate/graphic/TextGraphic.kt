package com.google.mlkit.codelab.translate.graphic

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import com.google.mlkit.vision.text.Text
import com.google.mlkit.codelab.translate.graphic.GraphicOverlay.Graphic
import java.lang.Float.max
import java.lang.Float.min
import java.util.Arrays


// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

///**
// * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
// * overlay view.
// */
class TextGraphic internal constructor(
    overlay: GraphicOverlay?,
    private val element: Text.Element?,
    private val rect1: Rect
) :
    Graphic(overlay) {
    private val rectPaint: Paint = Paint()
    private val textPaint: Paint

    init {
        rectPaint.color = FRAME_COLOR
//        rectPaint.style = Paint.Style.STROKE
        rectPaint.style = Paint.Style.FILL
//        rectPaint.strokeWidth = STROKE_WIDTH
        textPaint = Paint()
        textPaint.color = TEXT_COLOR
        textPaint.textSize = TEXT_SIZE
        // Redraw the overlay, as this graphic has been added.
        postInvalidate()
    }

    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
//    fun draw(canvas: Canvas) {
//        Log.d(TAG, "on draw text graphic")
//        checkNotNull(element) { "Attempting to draw a null text." }
//
//        // Draws the bounding box around the TextBlock.
//        val rect = RectF(element.boundingBox)
//        canvas.drawRect(rect, rectPaint)
//
//        // Renders the text at the bottom of the box.
//        canvas.drawText(element.text, rect.left, rect.bottom, textPaint)
//    }

    companion object {
        private const val TAG = "TextGraphic"
        private const val TEXT_COLOR = Color.BLACK
        private const val FRAME_COLOR = Color.WHITE
        private const val TEXT_SIZE = 30.0f
        private const val STROKE_WIDTH = 1.0f
    }

    override fun draw(canvas: Canvas?) {
        Log.d(TAG, "on draw text graphic")
        checkNotNull(element) { "Attempting to draw a null text." }

        // Draws the bounding box around the TextBlock.
//        180.0 left  483.0 top     540.0 right  896.99994 bottom
//        element.boundingBox!!.top += 500
//        element.boundingBox!!.left += 200
//        element.boundingBox!!.right += 540
//        element.boundingBox!!.bottom += 896
        val rect = RectF(element.boundingBox)
        rect.top += rect1.top + 30
        rect.left += rect1.left + 30
        rect.right += rect1.right + 30
        rect.bottom += rect1.bottom + 30
        Log.d("toanelement", "${element.boundingBox!!.top}")
        ////////////////////////////////
//        val x0 = translateX(rect.left)
//        val x1 = translateX(rect.right)
//        rect.left = min(x0, x1)
//        rect.right = max(x0, x1)
//        rect.top = translateY(rect.top)
//        rect.bottom = translateY(rect.bottom)
//        canvas!!.drawRect(rect, rectPaint)
        val textWidth = textPaint.measureText(element.text)
        val bound : Rect = Rect()
        val texHeigh = textPaint.getTextBounds(element.text,0,element.text.length, bound)
        canvas!!.drawRect(
            rect.left ,
            rect.top - TEXT_SIZE,
            rect.left + textWidth,
            rect.top + bound.height(),
            rectPaint
        )
        // Renders the text at the bottom of the box.
        canvas.drawText(element.text, rect.left, rect.top , textPaint)
        ////////////////////////////////////////////
//        canvas?.drawRect(rect, rectPaint)
//        // Renders the text at the bottom of the box.
//        canvas?.drawText(element.text, rect.left, rect.bottom, textPaint)

    }


}
