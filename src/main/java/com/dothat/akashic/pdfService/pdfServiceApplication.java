/**
 * Created by satya on 21/03/15.
 */
package com.dothat.akashic.pdfService;

import io.dropwizard.Application;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.dothat.akashic.pdfService.resources.pdfServiceResource;
//import com.dothat.akashic.pdfService.health.TemplateHealthCheck;

public class pdfServiceApplication extends Application<pdfServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new pdfServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<pdfServiceConfiguration> bootstrap) {
        // add multi part
        bootstrap.addBundle(new MultiPartBundle());

    }

    @Override
    public void run(pdfServiceConfiguration configuration,
                    Environment environment) {
        final pdfServiceResource resource = new pdfServiceResource();
//        TODO - need to put in healthchecks
//          final TemplateHealthCheck healthCheck =
//                new TemplateHealthCheck(configuration.getTemplate());
//        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}