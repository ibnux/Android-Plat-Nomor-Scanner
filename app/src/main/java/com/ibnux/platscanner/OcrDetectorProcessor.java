/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibnux.platscanner;

import android.content.Context;
import android.content.Intent;
//import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.SparseArray;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.ibnux.platscanner.camera.GraphicOverlay;

import java.util.ArrayList;
import java.util.List;

/**
 * A very simple Processor which receives detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> mGraphicOverlay;
    private Context cx;
    List<String> plats = new ArrayList<>();
    String regex = "^([A-Za-z]{1,2})(\\s|-)*([0-9]{1,4})(\\s|-)*([A-Za-z]{0,3})$";

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, Context cx) {
        mGraphicOverlay = ocrGraphicOverlay;
        this.cx = cx;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        //CarsworldApi.log("items: "+ items.toString());
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            String plt = item.getValue().trim().replace("  "," ");
            Log.w("OCR", plt);
            if(!plats.contains(plt)) {
                if (plt.matches(regex)) {
                    Log.w("OCR", plt+" FOUND");
                    Intent intent = new Intent("OcrCaptureActivity");
                    intent.putExtra("hasil", plt);
                    LocalBroadcastManager.getInstance(cx).sendBroadcast(intent);
                    plats.add(plt);
                }
            }
        }
    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        mGraphicOverlay.clear();
    }
}
