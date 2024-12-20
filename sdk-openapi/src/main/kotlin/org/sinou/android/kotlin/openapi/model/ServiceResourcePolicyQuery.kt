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

import org.sinou.android.kotlin.openapi.model.ServiceResourcePolicyAction

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param action 
 * @param any 
 * @param empty 
 * @param leftIdentifier 
 * @param subjects 
 */


data class ServiceResourcePolicyQuery (

    @Json(name = "Action")
    val action: ServiceResourcePolicyAction? = ServiceResourcePolicyAction.ANY,

    @Json(name = "Any")
    val any: kotlin.Boolean? = null,

    @Json(name = "Empty")
    val empty: kotlin.Boolean? = null,

    @Json(name = "LeftIdentifier")
    val leftIdentifier: kotlin.String? = null,

    @Json(name = "Subjects")
    val subjects: kotlin.collections.List<kotlin.String>? = null

) {


}
