# VoxMartCountryCode
This repository contains an Android project regarding the Country Mobile Number parser. To complete this project I used Kotlin, MVVM Pattern, Android Jetpack Room, Kotlin coroutine, Junit4, Google Truth etc.

### App Preview
<img src="https://github.com/aliahmedbd/VoxMartCountryCode/blob/master/20220118_131715.gif" alt="" data-canonical-src="https://github.com/aliahmedbd/CreditScoreTechnicalTask/blob/main/Screenshot%202021-12-19%20at%205.45.34%20PM.png" width="300" height="550" />


APK link: https://github.com/aliahmedbd/VoxMartCountryCode/blob/master/voxmartcountrycode.apk


## Application Flow

1. A splash screen will be appeared
2. After splash screen loaded successfully then Dial screen will be appeard. In Dial Screen there are four forms where we can enter the User number and dial number. Clickling on Parse button it will parse the number according to the requirements. 
3. Another bottom menu named as "Country Code", this view will fetch the country code from ROOM Database as well as user can add their own country code also.


## Problem Solving
The main challenge to parse number from the user dial number and need to convert it in intnernational number according to the Dial Number country. Here is parse number code sample:
```java
    fun parse(dialledNumber: String?, userNumber: String?): String? {
        val internationalCode = countryCodes[destination]
        if (validNumber(dialledNumber, userNumber) && dialledNumber?.let { validCountryDialNumber(it) } == true) {
            return if (internationalCode?.let { dialledNumber.contains(it) } == true) {
                dialledNumber
            } else {
                val number = dialledNumber?.drop(1)
                return internationalCode + number
            }
        }
        return null
    }
```


## Technical Description

Here is the list of technologies are used to build this application:

1. <b>Kotlin</b> Primary language
2. <b>MVVM Architecture</b>: MVVM architecure is followed for the code boilerplate. Where View, ViewModel, Repisitory are clearly used for maintailed the SOLID principle.
3. <b>Motion Layout</b> : `MotionLayout` is used for animate the app icon in the Splash screen.
4. <b> Android Jetpack ROOM</b> : ROOM is a ORM based sql lite database. Using ROOM app can save and fetch the country code.
5. <b>Kotlin Coroutine</b> : To reduce the main thread task we can divide the task in many thread asychronously using the Kotlin Coroutine using `lifecycle` scope. Here is the sample example:   
```java
   viewModelScope.launch(Dispatchers.IO) {
            countryCodeRepository.addCountryCode(countryCode)
        }
```
6.  <b>Hilt (Dependancy Injection used)</b> For dependancy injection I used HILT where HILT provide the ROOM database dependancy.
7.  <b>Unit Testing (JUnit, Google Truth)</b>: In this project I also introduced the in line Unit Testing code. For Unit testing I used JUnit and Google Truth. Google truth is used to see the clear visibility of any error. Here is the example of Unit Tesing code:

```java
    @Before
    fun setUp() {
        countryCodes = mutableMapOf("UK" to "+44", "US" to "+1", "FR" to "+33")
        nationalTrunkPrefixes = mutableMapOf("UK" to 0, "US" to 1, "FR" to 0)
    }

    @Test
    fun `check valid dial number`() {
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "UK").validNumber("+447833733777")
        assertThat(result).isTrue()
    }

    @Test
    fun `check valid dial number according to country`(){
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "UK").validCountryDialNumber("+447833733777")
        assertThat(result).isTrue()
    }

    @Test
    fun `check invalid dial number according to country`(){
        val result =
            NumberParser(countryCodes, nationalTrunkPrefixes, "US").validCountryDialNumber("+447833733777")
        assertThat(result).isFalse()
    }
```

9. Other usage technologies are Android Jetpack, Material Design, GIT for Version control.

Thanks for reading the technical documentation, if you wanted to know further please mail me at aliahmedaiub@gmail.com or you can visit my official webpage: https://aliahmedbd.github.io/
