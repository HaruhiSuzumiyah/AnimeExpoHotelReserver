# AnimeExpoHotelReserver
``` 
hotels.add("50395305"); //AC Hotel
hotels.add("50395299"); //Moxy
hotels.add("1450096"); //JW Marriot
hotels.add("11188113"); //Residence Inn
hotels.add("3203"); // E-Central Hotel
```
These are the hotels, they're in a list, and will be attempted to be reserved in ascending order.

The number is the hotel id from the webpage.

```
String username = "";
String password = "";
boolean useEmail = false;
```

This is for getting an email when your reservation is made. Don't worry about it if you don't want to use it.

```aidl
    String firstName = "";
    String lastName = "";
    String clixOrder = "";
    String email = "";
    String phoneNumber = "";
    String myAddress = "";
    String myCity = "";
    String myZip = "";
    String myState = "";
    String myCountry = "";
    String myCardType = "";
    String myCardNumber = "";
    String myCardExpMonth = "";
    String myCardExpYear = "";
```

Fill this in with your information so it can reserve a room for you.