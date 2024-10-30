package com.example.tripcalculator

import org.junit.Assert
import org.junit.Test

class TripCalculatorTest {

    @Test
    fun calculateTripPrice_safeZoneTraffic(){
        val km = 10f
        val time = 20
        val kmPrice = km * 7.5

        val expectedPrice = ((8+kmPrice+20)*0.05f).toInt()
        Assert.assertEquals(expectedPrice, calculatePrice(10f,20, 0.05f))

    }
}