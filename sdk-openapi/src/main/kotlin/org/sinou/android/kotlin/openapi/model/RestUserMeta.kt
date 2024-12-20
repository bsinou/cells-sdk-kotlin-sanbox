/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.sinou.android.kotlin.openapi.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param jsonValue 
 * @param namespace 
 * @param editable 
 * @param nodeUuid 
 */


data class RestUserMeta (

    @Json(name = "JsonValue")
    val jsonValue: kotlin.String,

    @Json(name = "Namespace")
    val namespace: kotlin.String,

    @Json(name = "Editable")
    val editable: kotlin.Boolean? = null,

    @Json(name = "NodeUuid")
    val nodeUuid: kotlin.String? = null

) {


}
