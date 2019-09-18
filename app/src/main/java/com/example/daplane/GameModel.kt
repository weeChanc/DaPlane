package com.example.daplane

import android.view.SurfaceHolder
import com.example.daplane.base.Scene
import com.example.daplane.entity.BackGround
import com.example.daplane.entity.Floor
import com.example.daplane.entity.GameBoy


object GameModel {
    var displayScenes = mutableListOf<Scene>()

    fun nextFrameData(clock: Long) {
        if (clock == 0L) {
            initData()
        } else {
            displayScenes.forEach {
                it.nextFrame(clock)
            }
        }
    }

    private fun initData() {
        displayScenes.add(BackGround(0f, 0f, 1080, 1920))
        displayScenes.add(Floor(120f, 320f, 40, 20))
        displayScenes.add(Floor(220f, 480f, 100, 20))
        displayScenes.add(Floor(440f, 380f, 40, 20))
        displayScenes.add(GameBoy(120f, 220f, 140, 80))
    }
}