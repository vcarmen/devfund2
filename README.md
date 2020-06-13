# Converter service


<h3>**Requirements**</h3>
- Gradle 6.1
- Java 11

<h3>**How to Execute**</h3>
<code>./gradlew build</code>

#

<h3>Supported ConvertTo formats</h3>
* psd
* pdf (In the next version)

<h3>**How to Test endpoint**</h3>
Replace the file, convertTo, and md5 fields.

<code>curl --location --request POST 'localhost:8080/api/v1/converter' \
--form 'file=@/Downloads/image.jpg' \
--form 'convertTo=psd' \
--form 'md5=504E57A898B6F07CC0C06F5485B72228'</code>