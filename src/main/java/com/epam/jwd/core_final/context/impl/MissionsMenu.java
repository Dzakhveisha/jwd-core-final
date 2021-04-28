package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.FlightMissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


public class MissionsMenu extends NassaMenu {

    public static final Logger LOGGER = LoggerFactory.getLogger("LOGGER");

    private final MissionFactory MISSION_FACTORY = MissionFactory.getInstance();
    private final SpacemapService SPACEMAP_SERVICE = SpacemapServiceImpl.getInstance();
    private final SpaceshipService SPACESHIP_SERVICE = SpaceshipServiceImpl.getInstance();
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
                " 4 -> automatic assign spaceship\n" +
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
                FlightMission mission = MISSION_SERVICE.createMission(MISSION_FACTORY.create(name, LocalDate.now(),
                        LocalDate.now().plusMonths(1 + (int) (Math.random() * 60)), end, start));
                System.out.println(mission);
            }
            break;
            case 2:
                System.out.println(" Enter the id of mission: ");
                int id = scan.nextInt();
                try {
                    FlightMission update = MISSION_SERVICE.updateMissionDetails(
                            MISSION_SERVICE.findMissionByCriteria(new FlightMissionCriteria().searchByID(id)).
                                    orElse(null));
                    System.out.println(update);
                } catch (UnknownEntityException ex) {
                    LOGGER.error(ex.getMessage());
                }
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
                try {
                    FlightMission mission2 = MISSION_SERVICE.findMissionByCriteria(new FlightMissionCriteria()
                            .searchByID(id)).orElseThrow(new UnknownEntityException("Mission"));
                    Spaceship spaceship = SPACESHIP_SERVICE.findSpaceshipByCriteria(
                            new SpaceshipCriteria().searchByReadiness()
                                    .searchByMinDistance(mission2.getDistance()))
                            .orElse(null);

                    //ArrayList<CrewMember> crewList = new ArrayList<>();
                    //for ( int i = 1; i <= 4; i++){
                    //    Short count = spaceship.getCrew().get(Role.resolveRoleById(i));
                    //}
                    mission2 = MISSION_SERVICE.assignSpaceship(id, spaceship);
                    System.out.println(mission2);
                } catch (UnknownEntityException ex) {
                    LOGGER.error(ex.getMessage());
                }
                break;
            case 6:
                System.out.println(" Enter the id of mission ");
                id = scan.nextInt();
                try {
                    FlightMission recordedMission = MISSION_SERVICE.findMissionByCriteria(
                            new FlightMissionCriteria().searchByID(id)).orElseThrow(new UnknownEntityException("Mission"));
                    System.out.println(recordedMission);
                    JSON_CONVERTER.toJSON(recordedMission);
                } catch (UnknownEntityException ex) {
                    LOGGER.error(ex.getMessage());
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
        return 0;
    }

}
