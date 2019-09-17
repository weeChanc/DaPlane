package com.example.daplane

import com.example.daplane.`interface`.Scene
import com.example.daplane.entity.BackGround
import com.example.daplane.entity.Floor
import com.example.daplane.entity.GameBoy


class GameModel {
    companion object{
        var scene = mutableListOf<Scene>()

        fun nextFrameData(clock: Long) {
            if (clock == 0L) {
                initData()
            } else {
                scene.forEach {
                    it.nextFrame(clock)
                }
            }
        }

        private fun initData() {
            scene.add(BackGround(0f,0f,1080,1920))
            scene.add(Floor(120f, 120f, 40, 20))
            scene.add(Floor(220f, 80f, 40, 20))
            scene.add(Floor(440f, 80f, 40, 20))
            scene.add(GameBoy(120f, 420f, 40, 80, true, true))
        }
    }
}