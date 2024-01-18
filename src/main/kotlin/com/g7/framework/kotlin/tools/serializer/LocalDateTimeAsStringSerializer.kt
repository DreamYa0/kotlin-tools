package com.g7.framework.kotlin.tools.serializer

import cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN
import cn.hutool.core.date.LocalDateTimeUtil
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author dreamyao
 * @title
 * @date 2021/8/19 9:58 下午
 * @since 1.0.0
 */
class LocalDateTimeAsStringSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: LocalDateTime) =
        encoder.encodeString(value.format(DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN)))

    override fun deserialize(decoder: Decoder): LocalDateTime =
        LocalDateTimeUtil.parse(decoder.decodeString(), NORM_DATETIME_PATTERN)
}