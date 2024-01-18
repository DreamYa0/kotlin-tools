package com.g7.framework.kotlin.tools.util

import com.g7.framwork.common.util.json.JsonUtils
import com.google.gson.reflect.TypeToken
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType

/**
 * @author dreamyao
 * @title
 * @date 2021/8/17 8:59 下午
 * @since 1.0.0
 */
// 对象序列化
inline fun <reified T> T.toJson(): String = JsonUtils.toJson(this)
// 反序列化非泛型对象或泛型对象，如：T List<T> Map<K,V> 等
inline fun <reified T : Any> String.fromJson(): T = JsonUtils.fromJson(this, typeToken<T>())
// 对象复制，对象转换 如果Map转对象
inline fun <reified T, reified E> E.transfer(): T = this.toJson().fromJson()

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
inline fun <reified T : Any> typeToken(): Type {
    val type = gsonTypeToken<T>()

    if (type is ParameterizedType && type.isWildcard())
        return type.rawType

    return removeTypeWildcards(type)
}

@Suppress("PROTECTED_CALL_FROM_PUBLIC_INLINE")
inline fun <reified T : Any> gsonTypeToken(): Type = object : TypeToken<T>() {}.type

fun ParameterizedType.isWildcard(): Boolean {
    var hasAnyWildCard = false
    var hasBaseWildCard = false
    var hasSpecific = false

    val cls = this.rawType as Class<*>
    cls.typeParameters.forEachIndexed { i, variable ->
        val argument = actualTypeArguments[i]

        if (argument is WildcardType) {
            val hit = variable.bounds.firstOrNull { it in argument.upperBounds }
            if (hit != null) {
                if (hit == Any::class.java)
                    hasAnyWildCard = true
                else
                    hasBaseWildCard = true
            } else
                hasSpecific = true
        } else
            hasSpecific = true
    }

    if (hasAnyWildCard && hasSpecific)
        throw IllegalArgumentException("Either none or all type parameters can be wildcard in $this")

    return hasAnyWildCard || (hasBaseWildCard && !hasSpecific)
}

fun removeTypeWildcards(type: Type): Type {

    if (type is ParameterizedType) {
        val arguments = type.actualTypeArguments
            .map { if (it is WildcardType) it.upperBounds[0] else it }
            .map { removeTypeWildcards(it) }
            .toTypedArray()
        return TypeToken.getParameterized(type.rawType, *arguments).type
    }

    return type
}