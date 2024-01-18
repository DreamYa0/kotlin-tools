package com.g7.framework.kotlin.tools.serializer

import cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN
import cn.hutool.core.date.DateUtil
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

/**
 * @author dreamyao
 * @title
 * @date 2022/3/2 12:09 下午
 * @since 1.0.0
 */
class DateAsStringSerializer : KSerializer<Date> {

    override val descriptor: SerialDescriptor get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Date = DateUtil.parse(decoder.decodeString(), NORM_DATETIME_PATTERN)

    override fun serialize(encoder: Encoder, value: Date) =
        encoder.encodeString(DateUtil.format(value, NORM_DATETIME_PATTERN))
}