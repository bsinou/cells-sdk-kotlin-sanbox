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
 * @param currentOffset 
 * @param currentPage 
 * @param limit 
 * @param nextOffset 
 * @param prevOffset 
 * @param total 
 * @param totalPages 
 */


data class RestPagination (

    @Json(name = "CurrentOffset")
    val currentOffset: kotlin.Int? = null,

    @Json(name = "CurrentPage")
    val currentPage: kotlin.Int? = null,

    @Json(name = "Limit")
    val limit: kotlin.Int? = null,

    @Json(name = "NextOffset")
    val nextOffset: kotlin.Int? = null,

    @Json(name = "PrevOffset")
    val prevOffset: kotlin.Int? = null,

    @Json(name = "Total")
    val total: kotlin.Int? = null,

    @Json(name = "TotalPages")
    val totalPages: kotlin.Int? = null

) {


}

