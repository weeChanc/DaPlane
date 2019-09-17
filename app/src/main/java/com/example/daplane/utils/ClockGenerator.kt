package com.example.daplane.utils

typealias ClockCallback = (clock: Long) -> Unit

class ClockGenerator {

    companion object {
        fun clockGen(millis: Long): (callback: ClockCallback) -> Unit {
            var currentClock = 0L;
            return {
                while (true) {
                    Thread.sleep(millis)
                    it.invoke(currentClock++);
                }
            }
        }

        fun gameClock(onClock: ClockCallback) {
            clockGen(4)(onClock);
        }
    }
}