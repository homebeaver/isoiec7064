ISO/IEC 7064
============

A Java library that calculates/validates [ISO/IEC 7064](https://github.com/homebeaver/ungueltig/wiki/ISO-IEC_7064) check characters.

The algorithms currently included are all pure systems and pure polynomial implementations of:
* ISO/IEC 7064, MOD 11-2 for numeric strings with one check digit or the supplementary check character "X"
* ISO/IEC 7064, MOD 37-2 for alphanumeric strings with one check digit or letter or the supplementary check character "*"
* ISO/IEC 7064, MOD 97-10 for numeric strings with two check digits
* ISO/IEC 7064, MOD 661-26 for alphabetic strings with two check letters
* ISO/IEC 7064, MOD 1271-36 for alphanumeric strings with two check digits or letters

This is a full implementation of the ISO/IEC standard. Also hybrid systems are implemeted:
* ISO/IEC 7064, MOD 11,10 for numeric strings with one check digit
* ISO/IEC 7064, MOD 27,26 for alphabetic strings with one check letter
* ISO/IEC 7064, MOD 37,36 for alphanumeric strings with one check digit or letter

```java
CheckDigit numericCheck = IsoIecPure11System.getInstance();

String checkDigit = numericCheck.calculate("123456");
boolean isValid = numericCheck.isValid("123456X");
```
