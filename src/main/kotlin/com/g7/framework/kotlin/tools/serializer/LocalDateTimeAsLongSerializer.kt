package com.g7.framework.kotlin.tools.serializer

import cn.hutool.core.date.LocalDateTimeUtil
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * @author dreamyao
 * @title
 * @date 2021/8/19 9:58 下午
 * @since 1.0.0
 */
class LocalDateTimeAsLongSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.LONG)
    override fun serialize(encoder: Encoder, value: LocalDateTime) =
        encoder.encodeLong(value.toInstant(ZoneOffset.of("+8")).toEpochMilli())

    override fun deserialize(decoder: Decoder): LocalDateTime = LocalDateTimeUtil.of(decoder.decodeLong())
}