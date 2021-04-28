package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.FlightMissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.util.JsonConverter;

import java.time.LocalDate;

public class MissionsMenu extends NassaMenu {

    private final MissionFactory MISSION_FACTORY = MissionFactory.getInstance();
    private final SpacemapService SPACEMAP_SERVICE = SpacemapServiceImpl.getInstance();
    private final MissionService MISSION_SERVICE = FlightMissionServiceImpl.getInstance();
    private final JsonConverter JSON_CONVERTER = JsonConverter.getInstance();

    public MissionsMenu(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void printAvailableOptions() {
        System.out.println(" 1 -> create mission by hands\n" +
                " 2 -> automatic update mission\n" +
                " 3 -> automatic generate mission\n" +
                " 4 -> automatic assign crew and spaceship\n" +
                " 5 -> launch\n" +
                " 6 -> save mission to JSON file\n");
    }

    @Override
    public int handleUserInput() {
        int command = scan.nextInt();
        switch (command) {
            case 1: {
                System.out.println(" Enter the name of mission: ");
                scan.nextLine();
                String name = scan.nextLine();
                System.out.println(" Enter the id of start planet ");
                Planet start = SPACEMAP_SERVICE.getPlanetByID(Long.parseLong(scan.nextLine()));
                System.out.println(" Enter the id of end planet: ");
                Planet end = SPACEMAP_SERVICE.getPlanetByID(Long.parseLong(scan.nextLine()));
                FlightMission mission =MISSION_SERVICE.createMission(MISSION_FACTORY.create(name, LocalDate.now(),
                        LocalDate.now().plusMonths(1 + (int) (Math.random() * 60)),end, start));
                System.out.println(mission);
            }
            break;
            case 2:
                System.out.println(" Enter the id of mission: ");
                int id = scan.nextInt();
                FlightMission update = MISSION_SERVICE.updateMissionDetails(
                        MISSION_SERVICE.findMissionByCriteria(new FlightMissionCriteria().searchByID(id)).
                                orElse(null));
                System.out.println(update);
                break;
            case 3:
                System.out.println(" Enter the name of mission: ");
                scan.nextLine();
                String name = scan.nextLine();
                FlightMission mission = MISSION_SERVICE.createMission(MISSION_FACTORY.create(name, LocalDate.now(),
                        LocalDate.now().plusMonths(1 + (int) (Math.random() * 60)),
                        SPACEMAP_SERVICE.getRandomPlanet(), SPACEMAP_SERVICE.getRandomPlanet()));
                System.out.println(mission);
                break;
            case 4:
                System.out.println(" Enter the id of mission: ");
                id = scan.nextInt();
                FlightMission mission1 =  MISSION_SERVICE.findMissionByCriteria(new FlightMissionCriteria().searchByID(id)).
                        orElse(null);


                break;
            case 5:
                System.out.println(" Enter the id of mission: ");
                id = scan.nextInt();

                break;
            case 6:
                System.out.println(" Enter the id of mission ");
                id = scan.nextInt();
                FlightMission recordedMission = MISSION_SERVICE.findMissionByCriteria(
                        new FlightMissionCriteria().searchByID(id)).orElse(null);
                System.out.println(recordedMission);
                JSON_CONVERTER.toJSON(recordedMission);
                break;
        }
        return 0;
    }

}
