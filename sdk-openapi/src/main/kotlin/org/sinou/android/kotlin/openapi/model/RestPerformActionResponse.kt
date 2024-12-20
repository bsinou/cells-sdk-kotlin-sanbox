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

import org.sinou.android.kotlin.openapi.model.RestActionStatus
import org.sinou.android.kotlin.openapi.model.RestBackgroundAction
import org.sinou.android.kotlin.openapi.model.RestNode

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param affectedNodes 
 * @param backgroundActions 
 * @param status 
 */


data class RestPerformActionResponse (

    @Json(name = "AffectedNodes")
    val affectedNodes: kotlin.collections.List<RestNode>? = null,

    @Json(name = "BackgroundActions")
    val backgroundActions: kotlin.collections.List<RestBackgroundAction>? = null,

    @Json(name = "Status")
    val status: RestActionStatus? = RestActionStatus.Performed

) {


}

