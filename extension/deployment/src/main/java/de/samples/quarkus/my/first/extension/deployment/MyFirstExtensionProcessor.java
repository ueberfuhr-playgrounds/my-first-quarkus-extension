package de.samples.quarkus.my.first.extension.deployment;

import de.samples.quarkus.my.first.extension.runtime.GreetingServlet;
import io.quarkus.deployment.IsDevelopment;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.devui.spi.page.CardPageBuildItem;
import io.quarkus.devui.spi.page.Page;
import io.quarkus.undertow.deployment.ServletBuildItem;
import io.quarkus.vertx.http.deployment.HttpRootPathBuildItem;

class MyFirstExtensionProcessor {

  private static final String FEATURE = "my-first-extension";

  @BuildStep
  FeatureBuildItem feature() {
    return new FeatureBuildItem(FEATURE);
  }

  @BuildStep
  ServletBuildItem servlet() {
    return ServletBuildItem
      .builder("greeting-servlet", GreetingServlet.class.getName())
      .addMapping("/hello")
      .build();
  }

  @BuildStep(onlyIf = IsDevelopment.class)
  CardPageBuildItem cardPage(HttpRootPathBuildItem rootPath) {
    final var result = new CardPageBuildItem();
    result.addPage(
      Page
        .externalPageBuilder("Tutorial")
        .url("https://quarkus.io/guides/dev-ui")
        .isHtmlContent()
        .icon("font-awesome-solid:globe")
        .doNotEmbed()
        .staticLabel("external link")
    );
    result.addPage(
      Page
        .externalPageBuilder("Greeting Servlet")
        .url(rootPath.resolvePath("hello"))
        .isHtmlContent()
        .icon("font-awesome-solid:spider")
    );

    result.addBuildTimeData("vendor", new Vendor("Ralf Ueberfuhr", "IT-Trainer"));
    result.addBuildTimeData("vendorlist", new Vendor[]{new Vendor("Ralf Ueberfuhr", "IT-Trainer")});
    result.addPage(
      Page
        .rawDataPageBuilder("Vendor (raw data)")
        .buildTimeDataKey("vendor")
        .icon("font-awesome-brands:js")
    );
    result.addPage(
      Page
        .tableDataPageBuilder("Vendor (table data)")
        .buildTimeDataKey("vendorlist")
        .icon("font-awesome-solid:table")
        .showColumn("name")
        .showColumn("job")
    );
    result.addPage(
      Page
        .quteDataPageBuilder("Vendor (qute data)")
        .icon("font-awesome-solid:q")
        .templateLink("display-vendor.html")
    );
    // webcomponents are possible too
    return result;
  }

  public record Vendor(
    String name,
    String job
  ) {
  }

}
