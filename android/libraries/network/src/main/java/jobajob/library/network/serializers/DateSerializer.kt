package jobajob.library.network.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Custom KotlinX.Serialization serializer for the [Date] type: [Date] <-> [String]
 */
object DateSerializer : KSerializer<Date> {

    private val SIMPLE_FORMAT_DATE = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat? {
            return SimpleDateFormat("yyyy-MM-dd", Locale.US)
        }
    }

    private val SIMPLE_FORMAT = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat? {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        }
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        SIMPLE_FORMAT.get()?.let { formatter ->
            encoder.encodeString(formatter.format(value))
        }
    }

    override fun deserialize(decoder: Decoder): Date {

        val stringDate = decoder.decodeString()

        val date = listOf(SIMPLE_FORMAT_DATE, SIMPLE_FORMAT)
            .asSequence()
            .mapNotNull { formatter ->
                try {
                    formatter.get()!!.parse(stringDate)
                } catch (ignored: Throwable) {
                    null
                }
            }
            .take(1)
            .firstOrNull()

        return date ?: Date(0)
    }
}