package nl.avthart.todo.app.graphql

data class UserType(val id: Long, val salutation: String? = "", val title: String? = "", val firstname: String, val lastname: String, val email: String, val phone: String? = "",
                    val homepage: String? = "", val image: String? = "", val position: String? = "", val address: Address? = null, val company: Company? = null
)

data class Image(val hash: String)
data class Company(val id: String, val name: String, val address: Address?)
data class Country(val iso: String, val name: String)
data class Address(val street: String, val zip: String, val city: String, val country: Country)

data class CreateUserInput(val salutation: String, val title: String, val firstname: String, val lastname: String, val email: String, val phone: String, val homepage: String, val position: String)
data class CreateUserPayload(val user: UserType?)

data class UpdateUserInput(val id: Long, val salutation: String? = "", val title: String? = "", val firstname: String, val lastname: String, val phone: String? = "", val homepage: String? = "", val position: String? = "")
data class UpdateUserPayload(val user: UserType?)


