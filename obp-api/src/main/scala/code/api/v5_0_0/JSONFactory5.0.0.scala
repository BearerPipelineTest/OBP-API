/**
  * Open Bank Project - API
  * Copyright (C) 2011-2019, TESOBE GmbH
  * *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * *
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * *
  * Email: contact@tesobe.com
  * TESOBE GmbH
  * Osloerstrasse 16/17
  * Berlin 13359, Germany
  * *
  * This product includes software developed at
  * TESOBE (http://www.tesobe.com/)
  *
  */
package code.api.v5_0_0

import code.api.v3_1_0.{PostConsentEntitlementJsonV310}
import com.openbankproject.commons.model.{AccountRoutingJsonV121, UserAuthContext, UserAuthContextUpdate}
import net.liftweb.json.JsonAST.JValue

import java.util.Date
import scala.collection.immutable.List

case class UserAuthContextJsonV500(
  user_auth_context_id: String,
  user_id: String,
  key: String,
  value: String,
  time_stamp: Date,
  consumer_id: String,
)

case class UserAuthContextsJsonV500(
  user_auth_contexts: List[UserAuthContextJsonV500]
)

case class UserAuthContextUpdateJsonV500(
  user_auth_context_update_id: String,
  user_id: String,
  key: String,
  value: String,
  status: String,
  consumer_id: String,
)


case class PostConsentRequestResponseJson(consentRequestId: String)

case class ConsentRequestResponseJson(
  consent_request_id: String, 
  payload : JValue, 
  consumer_id : String
)
case class AccountAccessV500(
//  bank_routing: Option[BankRoutingJsonV121],
//  branch_routing: Option[BranchRoutingJsonV141],
  account_routing: AccountRoutingJsonV121,
  view_id: String
)

case class PostConsentRequestJsonV500(
  everything: Boolean,
  account_access: List[AccountAccessV500],
  entitlements: Option[List[PostConsentEntitlementJsonV310]],
  consumer_id: Option[String],
  email: Option[String],
  phone_number: Option[String],
  valid_from: Option[Date],
  time_to_live: Option[Long]
)

case class ConsentJsonV500(consent_id: String, jwt: String, status: String, consent_request_id: Option[String])
object JSONFactory500 {

  def createUserAuthContextJson(userAuthContext: UserAuthContext): UserAuthContextJsonV500 = {
    UserAuthContextJsonV500(
      user_auth_context_id= userAuthContext.userAuthContextId,
      user_id = userAuthContext.userId,
      key = userAuthContext.key,
      value = userAuthContext.value,
      time_stamp = userAuthContext.timeStamp,
      consumer_id = userAuthContext.consumerId,
    )
  }
  
  def createUserAuthContextsJson(userAuthContext: List[UserAuthContext]): UserAuthContextsJsonV500 = {
    UserAuthContextsJsonV500(userAuthContext.map(createUserAuthContextJson))
  }

  def createUserAuthContextUpdateJson(userAuthContextUpdate: UserAuthContextUpdate): UserAuthContextUpdateJsonV500 = {
    UserAuthContextUpdateJsonV500(
      user_auth_context_update_id= userAuthContextUpdate.userAuthContextUpdateId,
      user_id = userAuthContextUpdate.userId,
      key = userAuthContextUpdate.key,
      value = userAuthContextUpdate.value,
      status = userAuthContextUpdate.status,
      consumer_id = userAuthContextUpdate.consumerId
    )
  }
}

