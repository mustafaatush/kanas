package com.kodyuzz.kanas.utils.common

import com.kodyuzz.kanas.R
import java.util.regex.Pattern

object Validator {

    private val EMAIL_ADDRESS = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    private const val MIN_PASSWORD_LENGTH = 6

    fun validateLoginField(email: String?, password: String?): List<Validation> =
        ArrayList<Validation>().apply {
            when {
                email.isNullOrEmpty() ->
                    add(
                        Validation(
                            Validation.Field.EMAIL,
                            Resource.error(R.string.email_field_empty)
                        )
                    )
                !EMAIL_ADDRESS.matcher(email).matches() ->
                    add(
                        Validation(
                            Validation.Field.EMAIL,
                            Resource.error(R.string.email_field_invalid)
                        )
                    )
                else ->
                    add(Validation(Validation.Field.EMAIL, Resource.success()))
            }
            when {
                password.isNullOrEmpty() ->
                    add(
                        Validation(
                            Validation.Field.PASSWORD,
                            Resource.error(R.string.password_field_empty)
                        )
                    )
                password.length < MIN_PASSWORD_LENGTH ->
                    add(
                        Validation(
                            Validation.Field.PASSWORD,
                            Resource.error(R.string.password_field_small_length)
                        )
                    )
                else -> add(Validation(Validation.Field.PASSWORD, Resource.success()))
            }
        }
}

data class Validation(val field: Field, val resouce: Resource<Int>) {
    enum class Field {
        EMAIL,
        PASSWORD
    }
}