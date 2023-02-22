# Assessment--

AddressBook
Backend Assessment

Manual Implementation of ADS Back-End Test

**Due to storage issues and timing, I have manually coded the requirements and explained my logic in the form of a code review**
The first task required me to create an “Address Book” that app with capabilities that simulate a command line utility (CLU) written in Kotlin. Task instructions are as prompted:
1.)	Convert address book xml to json
2.)	Convert address book json to xml
3.)	Validate it against schema (schema is not provided, BIG plus if derived from the code based on ab.xml)
* Code Logic Review *
Based on instructions and main function, I first need to create a class to represent a contact with fields based on the data source:
 “The data source is supplied in an XML file (download ab.xml from
   https://drive.google.com/file/d/1lztASk0Ui3mf53-
   HLJHmWCgVQNTrrDLm/view?usp=sharing”

Fields: Customer Id, Company Name, Contact Name, Contact Title, Address, City, Email, Postal Code, Country, Phone, Fax
