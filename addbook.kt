import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.io.IOException
import java.util.Scanner
import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.bind.Unmarshaller
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

data class Customer(
    val customerId: Int,
    val companyName: String,
    val contactName: String,
    val contactTitle: String,
    val address: String,
    val city: String,
    val email: String,
    val postalCode: String,
    val country: String,
    val phone: String,
    val fax: String
)

fun main() {
    val scanner = Scanner(System.`in`)
    val addressBook = mutableListOf<Customer>()

    while (true) {
        println("1. Add customer")
        println("2. View all customers")
        println("3. Convert address book to JSON")
        println("4. Convert address book to XML")
        println("5. Validate address book against schema")
        println("6. Exit")

        print("Enter your choice: ")
        val choice = scanner.nextInt()

        when (choice) {
            1 -> {
                val customer = createCustomer(scanner)
                addressBook.add(customer)
                println("Customer added successfully.")
            }
            2 -> {
                if (addressBook.isEmpty()) {
                    println("No customers found.")
                } else {
                    addressBook.forEach { println(it) }
                }
            }
            3 -> {
                val objectMapper = ObjectMapper().registerKotlinModule()
                val json = objectMapper.writeValueAsString(addressBook)
                File("addressbook.json").writeText(json)
                println("Address book converted to JSON successfully.")
            }
            4 -> {
                val context = JAXBContext.newInstance(Customer::class.java)
                val marshaller = context.createMarshaller()
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true)
                marshaller.marshal(addressBook, File("addressbook.xml"))
                println("Address book converted to XML successfully.")
            }
            5 -> {
                try {
                    val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    val schema = schemaFactory.newSchema(File("addressbook.xsd"))
                    val context = JAXBContext.newInstance(Customer::class.java)
                    val unmarshaller: Unmarshaller = context.createUnmarshaller()
                    unmarshaller.setSchema(schema)
                    unmarshaller.unmarshal(StreamSource(File("addressbook.xml")))
                    println("Address book is valid.")
                } catch (e: JAXBException) {
                    println("JAXBException: ${e.message}")
                } catch (e: IOException) {
                    println("IOException: ${e.message}")
                }
            }
            6 -> {
                println("Exiting address book.")
                return
            }
            else -> {
                println("Invalid choice. Please try again.")
            }
        }
    }
}

private fun createCustomer(scanner: Scanner): Customer {
    print("Enter customer ID: ")
    val customerId = scanner.nextInt()
    scanner.nextLine()

    print("Enter company name: ")
    val companyName = scanner.nextLine()

    print("Enter contact name: ")
    val contactName = scanner.nextLine()

    print("Enter contact title: ")
    val contactTitle = scanner.nextLine()

    print("Enter address: ")
    val address = scanner.nextLine()

