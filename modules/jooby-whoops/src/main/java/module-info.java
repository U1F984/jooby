/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
module io.jooby.whoops {
  exports io.jooby.whoops;

  requires io.jooby;
  requires com.github.spotbugs.annotations;
  requires typesafe.config;
  requires org.slf4j;

  // SHADED: All content after this line will be removed at build time
  requires static unbescape;
  requires static io.pebbletemplates;
}
