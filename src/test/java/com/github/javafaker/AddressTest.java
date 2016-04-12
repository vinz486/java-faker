package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.javafaker.matchers.IsANumber.isANumber;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.DecimalFormatSymbols;

public class AddressTest {

    private static final Logger logger = LoggerFactory.getLogger(AddressTest.class);
    private static final char decimalSeparator = new DecimalFormatSymbols().getDecimalSeparator();
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testStreetAddressIsANumber() {
        final String streetAddressNumber = faker.address().streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertThat(streetAddressNumber, matchesRegularExpression("[0-9]+ [A-Za-z0-9 ]+"));
    }

    @Test
    public void testLatitude() {
        String latStr;
        Double lat;
        for (int i = 0; i < 100; i++) {
            latStr = faker.address().latitude().replace(decimalSeparator, '.');
            assertThat(latStr, isANumber());
            lat = new Double(latStr);
            assertThat("Latitude is less then -90", lat >= -90);
            assertThat("Latitude is greater than 90", lat <= 90);
        }
    }

    @Test
    public void testLongitude() {
        String longStr;
        Double lon;
        for (int i = 0; i < 100; i++) {
            longStr = faker.address().longitude().replace(decimalSeparator, '.');
            assertThat(longStr, isANumber());
            lon = new Double(longStr);
            assertThat("Longitude is less then -180", lon >= -180);
            assertThat("Longitude is greater than 180", lon <= 180);
        }
    }


    @Test
    public void testTimeZone() {
        assertThat(faker.address().timeZone(), matchesRegularExpression("[A-Za-z_]+/[A-Za-z_]+[/A-Za-z_]*"));
    }

    @Test
    public void testState() {
        assertThat(faker.address().state(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void testCity() {
        assertThat(faker.address().city(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void testCountry() {
        assertThat(faker.address().country(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void testCountryCode() {
        assertThat(faker.address().countryCode(), matchesRegularExpression("[A-Za-z ]+"));
    }
}
