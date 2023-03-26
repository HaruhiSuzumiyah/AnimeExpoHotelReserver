import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AnimeBotDriver {
    public static void main(String[] args) {

        List<String> hotels = new ArrayList<>();
        EmailUsernamePassword usernamePassword = new EmailUsernamePassword();
        PersonalInformation myInfo = new PersonalInformation();

        hotels.add("50395305"); //AC Hotel
        hotels.add("50395299"); //Moxy
        hotels.add("1450096"); //JW Marriot
        hotels.add("11188113"); //Residence Inn
        hotels.add("3203"); // E-Central Hotel
        // Recipient's email ID needs to be mentioned.
        String to = usernamePassword.username;

        // Sender's email ID needs to be mentioned
        String from = usernamePassword.username;

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        // Used to debug SMTP issues

        ObjectMapper objectMapper = new ObjectMapper();
        boolean noHotel = true;

        while (noHotel) {
            try {
                WebDriver driver = new ChromeDriver();
                driver.get("https://book.passkey.com/go/AXAttendee2023");
                String title = driver.getTitle();
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
                WebElement calendarIn = driver.findElement(By.className("check-in-value"));
                calendarIn.click();
                WebElement june30 = driver.findElement(By.id("dp_in_5_30"));
                june30.click();
                WebElement calendarOut = driver.findElement(By.className("check-out-value"));
                calendarOut.click();
                WebElement july4 = driver.findElement(By.id("dp_out_6_5"));
                july4.click();
                WebElement twoGuests = driver.findElement(By.id("spinner-guest"));
                //twoGuests.sendKeys(Keys.BACK_SPACE + "2");
                WebElement submit = driver.findElement(By.id("submitQuickBook"));
                submit.click();
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
                List<String> foundHotels = new ArrayList<>();

                for (String hotelId : hotels) {
                    try {
                        WebElement hotel = driver.findElement(By.id(hotelId));
                        System.out.println("HOTEL FOUND AT ID: " + hotelId);
                        foundHotels.add(hotelId);
                        WebElement hotelSubmit = hotel.findElement(By.className("button"));
                        hotelSubmit.click();

                        WebElement listRoom = driver.findElement(By.className("list-room"));
                        WebElement listRoomItem = listRoom.findElement(By.className("list-view-item"));
                        WebElement listRoomSubmit = listRoomItem.findElement(By.className("button"));

                        listRoomSubmit.click();
                        WebElement myFirstName = driver.findElement(By.id("reservations0.guests0.firstName"));
                        myFirstName.sendKeys(myInfo.firstName);

                        WebElement myLastName = driver.findElement(By.id("reservations0.guests0.lastName"));
                        myLastName.sendKeys(myInfo.lastName);

                        WebElement myShowClixOrder = driver.findElement(By.id("reservations0.guests0.position"));
                        myShowClixOrder.sendKeys(myInfo.clixOrder);

                        WebElement myEmail = driver.findElement(By.id("reservations0.guests0.email"));
                        myEmail.sendKeys(myInfo.email);

                        WebElement myEmailConfirmation = driver.findElement(By.id("reservations0.guests0.confirmEmail"));
                        myEmailConfirmation.sendKeys(myInfo.email);

                        WebElement myPhoneNumber = driver.findElement(By.id("reservations0.guests0.phoneNumber"));
                        myPhoneNumber.sendKeys(myInfo.phoneNumber);

                        WebElement myAddress = driver.findElement(By.id("reservations0.guests0.address.address1"));
                        myAddress.sendKeys(myInfo.myAddress);

                        WebElement myCity = driver.findElement(By.id("reservations0.guests0.address.city"));
                        myCity.sendKeys(myInfo.myCity);

                        WebElement myZip = driver.findElement(By.id("reservations0.guests0.address.zip"));
                        myZip.sendKeys(myInfo.myZip);

                        WebElement myState = driver.findElement(By.id("reservations0.guests0.address.state"));
                        myState.sendKeys(myInfo.myState);

                        Select selectCountry = new Select(driver.findElement(By.id("reservations0.guests0.address.country.alpha2Code")));
                        selectCountry.selectByVisibleText(myInfo.myCountry);

                        WebElement goToPayment = driver.findElement(By.className("continue-sec"))
                                .findElement(By.className("button"));

                        goToPayment.click();
                        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

                        Select selectCardType = new Select(driver.findElement(By.id("billingInfo0.payment.creditCard.cardTypeId")));
                        selectCardType.selectByVisibleText(myInfo.myCardType);

                        WebElement cardNumber = driver.findElement(By.id("billingInfo0.payment.creditCard.cardNumber"));
                        cardNumber.sendKeys(myInfo.myCardNumber);

                        Select selectCardExpMonth = new Select(driver.findElement(By.id("billingInfo0.payment.creditCard.expiryMonth")));
                        selectCardExpMonth.selectByVisibleText(myInfo.myCardExpMonth);

                        Select selectCardExpYear = new Select(driver.findElement(By.id("billingInfo0.payment.creditCard.expiryYear")));
                        selectCardExpYear.selectByVisibleText(myInfo.myCardExpYear);

                        WebElement confirmPayment = driver.findElement(By.className("continue-sec"))
                                .findElement(By.className("button"));

                        confirmPayment.click();
                        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
                        WebElement firstCheckbox = driver.findElement(By.id("confirmation-section"))
                                .findElement(By.id("confirmation-box"))
                                .findElement(By.className("checkbox"));
                        firstCheckbox.click();
                        WebElement secondCheckbox = driver.findElement(By.id("confirmation-box-1"))
                                .findElement(By.className("checkbox"));
                        secondCheckbox.click();

                        WebElement saveAndConfirm = driver.findElement(By.id("save_and_confirm_button"));
                        saveAndConfirm.click();

                        noHotel = false;
                        break;
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("NO HOTEL FOUND AT ID: " + hotelId);
                    }
                }

                if (foundHotels.size() > 0) {
                    if(usernamePassword.useEmail) {
                        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(usernamePassword.username, usernamePassword.password);
                            }
                        });
                        session.setDebug(true);
                        try {
                            // Create a default MimeMessage object.
                            MimeMessage message = new MimeMessage(session);
                            // Set From: header field of the header.
                            message.setFrom(new InternetAddress(from));
                            // Set To: header field of the header.
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                            // Set Subject: header field
                            message.setSubject("HOTELS AVAILABLE");
                            // Now set the actual message
                            message.setText(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundHotels));
                            System.out.println("sending...");
                            // Send message
                            Transport.send(message);
                            System.out.println("Sent message successfully....");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                driver.close();
            } catch (Exception e) {

            }


        }
    }

}
