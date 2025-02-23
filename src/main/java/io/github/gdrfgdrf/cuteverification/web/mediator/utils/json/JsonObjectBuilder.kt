package io.github.gdrfgdrf.cuteverification.web.mediator.utils.json

import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.math.BigDecimal
import java.math.BigInteger

class JsonObjectBuilder {
    private val json = Jsons.new()

    fun putArray(key: String): ArrayNode = json.putArray(key)

    fun put(key: String, value: String): ObjectNode = json.put(key, value)
    fun put(key: String, value: Int): ObjectNode = json.put(key, value)
    fun put(key: String, value: Long): ObjectNode = json.put(key, value)
    fun put(key: String, value: Float): ObjectNode = json.put(key, value)
    fun put(key: String, value: Double): ObjectNode = json.put(key, value)
    fun put(key: String, value: Short): ObjectNode = json.put(key, value)
    fun put(key: String, value: Boolean): ObjectNode = json.put(key, value)
    fun put(key: String, value: ByteArray): ObjectNode = json.put(key, value)
    fun put(key: String, value: BigDecimal): ObjectNode = json.put(key, value)
    fun put(key: String, value: BigInteger): ObjectNode = json.put(key, value)

    fun build(): String {
        return json.toString()
    }
}