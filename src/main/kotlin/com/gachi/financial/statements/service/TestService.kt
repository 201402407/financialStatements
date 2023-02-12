package com.gachi.financial.statements.service

import org.springframework.stereotype.Service

@Service
class TestService {

    // 단위 : 억
    fun calculate(sales: Int, operatingIncome: Int, nowYearNetProfit: Int, nowPrice: Int, marketCapitalization: Int, sharesOutstanding: Int) {
        val eps = getEps(
            nowYearNetProfit = nowYearNetProfit * 100000000,
            sharesOutstanding = sharesOutstanding,
        )
    }

    // EPS = 순이익 / 발행 주식 수
    fun getEps(nowYearNetProfit: Int, sharesOutstanding: Int, lastOneYearEps: Int? = null, lastTwoYearEps: Int? = null, ) {
        val oneYearEps = getOneYearEps(
            oneYearNetProfit = nowYearNetProfit,
            sharesOutstanding = sharesOutstanding
        )

        val averageEps: Int? = if (lastOneYearEps != null && lastTwoYearEps != null) {
            getAverageEps(
                nowYearEps = oneYearEps,
                lastOneYearEps = lastOneYearEps,
                lastTwoYearEps = lastTwoYearEps
            )
        } else {
            null
        }
    }

    private fun getOneYearEps(oneYearNetProfit: Int, sharesOutstanding: Int): Int = oneYearNetProfit / sharesOutstanding

    private fun getAverageEps(nowYearEps: Int, lastOneYearEps: Int, lastTwoYearEps: Int)
    : Int = ((nowYearEps * 3) + (lastOneYearEps * 2) + lastTwoYearEps) / 6

}