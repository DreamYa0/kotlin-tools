package com.g7.framework.kotlin.tools.util

import java.util.*

/**
 * @author dreamyao
 * @title
 * @date 2023/9/1 11:48
 * @since 1.0.0
 */
object EnhListUtils {

    fun <T> ofNonNull(vararg elements: T): List<T> {
        val list = mutableListOf<T>()
        for (element in elements) {
            if (Objects.nonNull(element)) {
                list.add(element)
            }
        }
        return list
    }
}