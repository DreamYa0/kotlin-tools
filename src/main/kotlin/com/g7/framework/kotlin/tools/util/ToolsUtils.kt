package com.g7.framework.kotlin.tools.util

import cn.hutool.core.date.DateUtil
import cn.hutool.core.date.LocalDateTimeUtil
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.util.Date

/**
 * @author dreamyao
 * @title
 * @date 2021/9/9 5:50 下午
 * @since 1.0.0
 */
fun <T> Collection<T>?.isNotNullOrNotEmpty(): Boolean = !isNullOrEmpty()
fun CharSequence?.isNotNullOrNotBlank(): Boolean = !isNullOrBlank()

fun BigDecimal.toYuan(): BigDecimal {
    // 分转元（金额转换）
    return this.divide(BigDecimal("100"), 2, RoundingMode.HALF_UP) //四舍五入保留两位小数
}

fun BigDecimal.toTon(): BigDecimal {
    // 千克转吨（载重转换）
    return this.divide(BigDecimal("1000"), 3, RoundingMode.HALF_UP) //四舍五入保留三位小数
}

fun BigDecimal.scale3(): BigDecimal {
    // 四舍五入保留三位小数
    return this.divide(BigDecimal.ONE, 3, RoundingMode.HALF_UP)
}

// LocalDateTime 转 Date
fun LocalDateTime?.toDate(): Date? {
    return if (this != null) {
        DateUtil.date(this)
    } else {
        null
    }
}

// Date 转 LocalDateTime
fun Date?.toLocalDateTime(): LocalDateTime? {
    return if (this != null) {
        LocalDateTimeUtil.of(this)
    } else {
        null
    }
}