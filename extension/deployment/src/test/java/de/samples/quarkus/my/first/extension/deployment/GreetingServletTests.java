package de.samples.quarkus.my.first.extension.deployment;

import io.quarkus.test.QuarkusUnitTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static io.restassured.RestAssured.when;

class GreetingServletTests {

  @RegisterExtension
  static final QuarkusUnitTest config = new QuarkusUnitTest()
    .withEmptyApplication();

  @Test
  void testGreeting() {
    when()
      .get("/hello")
      .then()
      .statusCode(200);
  }

}
