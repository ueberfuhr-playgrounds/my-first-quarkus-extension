package de.samples.quarkus.my.first.extension.deployment;

import de.samples.quarkus.my.first.extension.runtime.GreetingServlet;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.undertow.deployment.ServletBuildItem;

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

}
