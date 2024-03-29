package smarthome.io.ui;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import smarthome.model.*;
import smarthome.repository.*;

import java.util.ArrayList;

@EnableJpaRepositories(basePackages = "smarthome")
@EntityScan(basePackages = "smarthome")
@ComponentScan(basePackages = "smarthome")
@SpringBootApplication
public class Application {

    SensorTypeList sensorTypeList = new SensorTypeList();
    GAList gaList = new GAList();
    SystemAdministrationUI systemAdministrationUI = new SystemAdministrationUI();
    HouseAdministrationUI houseAdministrationUI = new HouseAdministrationUI();
    RegularUsageUI regularUsageUI = new RegularUsageUI();
    PowerUserUI powerUserUI = new PowerUserUI();
    RoomOwnerUI roomOwnerUI = new RoomOwnerUI();

    private static final String DEFAULT = "Default";
    Location loc = new Location(1, 1, 1);
    Address a1 = new Address(DEFAULT, DEFAULT, "0000-000", DEFAULT, DEFAULT, loc);
    OccupationArea oc = new OccupationArea(1, 1);
    GeographicalArea g1 = new GeographicalArea(DEFAULT, DEFAULT, DEFAULT, oc, loc);
    House house = House.getHouseInstance(a1, g1);
    TypeGAList typeGAList = TypeGAList.getTypeGAListInstance();
    static final Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);

    }

    @Bean
    public CommandLineRunner demo(GeoRepository geoRep, RoomRepository rRep, SensorTypeRepository unitRep, TypeGARepository typeRep,
                                  ExternalSensorRepository extSensorRep, InternalSensorRepository intSensorRep, HouseGridRepository gridsRep) {
        Repositories.setTypeGARepository(typeRep);
        Repositories.setGeoRepository(geoRep);
        Repositories.setRoomRepository(rRep);
        Repositories.setSensorTypeRepository(unitRep);
        Repositories.setExternalSensorRepository(extSensorRep);
        Repositories.setInternalSensorRepository(intSensorRep);
        Repositories.setGridsRepository(gridsRep);

        return args -> {
            int option = -1;
            while (option != 0) {
                ArrayList<String> options = new ArrayList<>();
                options.add("[1] System Administration");
                options.add("[2] House Administration");
                options.add("[3] Regular User");
                options.add("[4] Power User");
                options.add("[5] Room Owner");
                UtilsUI.showList("Main Menu", options, false, 5);

                option = UtilsUI.requestIntegerInInterval(0, 5, "Please choose an action between 1 and 5, or 0 to exit the program");
                switch (option) {
                    case 1:
                        systemAdministrationUI.menu(gaList, sensorTypeList);
                        break;
                    case 2:
                        houseAdministrationUI.menu(sensorTypeList, gaList);
                        break;
                    case 3:
                        regularUsageUI.menu(sensorTypeList);
                        break;
                    case 4:
                        powerUserUI.menu();
                        break;
                    case 5:
                        roomOwnerUI.menu();

                        break;
                    default:
                        //no action needed
                }
            }
        };
    }

    //Code by NPS, added by AA.

    @Bean
    public ServletWebServerFactory servletContainer() {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

    /*
    We need to redirect from HTTP to HTTPS. Without SSL, this application used
    port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
    redirected to HTTPS on 8443.
     */
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }


}