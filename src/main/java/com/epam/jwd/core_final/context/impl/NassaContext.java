package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewMemberServiceImpl;
import com.epam.jwd.core_final.service.impl.FlightMissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


// todo
public class NassaContext implements ApplicationContext {

    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<Planet> planets = new ArrayList<>();
    private final Collection<FlightMission> missions = new ArrayList<>();

    private final CrewService CREW_SERVICE = CrewMemberServiceImpl.getInstance();
    private final SpacemapService SPACEMAP_SERVICE = SpacemapServiceImpl.getInstance();
    private final SpaceshipService SPACESHIP_SERVICE = SpaceshipServiceImpl.getInstance();
    private final FlightMissionServiceImpl MISSION_SERVICE = FlightMissionServiceImpl.getInstance();

    private final CrewMemberFactory CREW_MEMBER_FACTORY = CrewMemberFactory.getInstance();
    private final PlanetFactory PLANET_FACTORY = PlanetFactory.getInstance();
    private final SpaceshipFactory SPACESHIP_FACTORY = SpaceshipFactory.getInstance();
    private final MissionFactory MISSION_FACTORY = MissionFactory.getInstance();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (Spaceship.class.equals(tClass)) {
            return (Collection<T>) spaceships;
        } else if (CrewMember.class.equals(tClass)) {
            return (Collection<T>) crewMembers;
        } else if (Planet.class.equals(tClass)) {
            return (Collection<T>) planets;
        } else if (FlightMission.class.equals(tClass)) {
            return (Collection<T>) missions;
        }
        throw new IllegalStateException("Unexpected value: " + tClass);
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        ApplicationProperties properties = ApplicationProperties.getInstance();

        initPlanets(properties.getInputRootDir(), properties.getSpacemapFileName());
        initCrewMembers(properties.getInputRootDir(), properties.getCrewFileName());
        initSpaceships(properties.getInputRootDir(), properties.getSpaceshipsFileName());
        initMissions(properties.getInputRootDir(), properties.getMissionsFileName(), properties.getDateTimeFormat());
        System.out.println("s");
    }

    private void initMissions(String dir, String file, String dateTimeFormat){
        File resFile = new File(getClass().getClassLoader().getResource(dir + "/" + file).getFile());
        try (FileReader reader = new FileReader(resFile); BufferedReader br = new BufferedReader(reader)) {
            String line = br.readLine();
            while (line != null) {
                String[] substrings = line.split(";");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat, Locale.US);

                missions.add(MISSION_SERVICE.createMission(MISSION_FACTORY.create(
                        substrings[0],
                        LocalDate.parse( substrings[1], formatter),
                        LocalDate.parse(substrings[2], formatter),
                        SPACEMAP_SERVICE.getPlanetByName(substrings[3]),
                        SPACEMAP_SERVICE.getPlanetByName(substrings[4])
                )));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPlanets(String dir, String file) {
        File resFile = new File(getClass().getClassLoader().getResource(dir + "/" + file).getFile());
        try (FileReader reader = new FileReader(resFile); BufferedReader br = new BufferedReader(reader)) {
            int rowCount = 1;
            String line = br.readLine();
            while (line != null) {
                int columnCount = 1;
                String[] points = line.split(",");
                for (String point: points) {
                    if (!point.equals("null")){
                        planets.add(SPACEMAP_SERVICE.addPlanetToMap(
                                PLANET_FACTORY.create(new Point(columnCount, rowCount), point)));
                    }
                    columnCount++;
                }
                line = br.readLine();
                rowCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSpaceships(String dir, String file) {
        File resFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(dir + "/" + file)).getFile());
        try (FileReader reader = new FileReader(resFile); BufferedReader br = new BufferedReader(reader)) {
            String spaceshipString = br.readLine();
            while (spaceshipString != null) {
                String[] parts = spaceshipString.split(";");
                String team = parts[2];
                team = team.replace('}', ' ');
                team = team.substring(1);
                Map<Role, Short> teamMap = new HashMap<>();
                while (team.length() != 0) {
                    teamMap.put(Role.resolveRoleById(team.charAt(0) - '0'), (short) (team.charAt(2) - '0'));
                    team = team.substring(4);
                }
                spaceships.add(SPACESHIP_SERVICE.createSpaceship(
                        SPACESHIP_FACTORY.create(
                                teamMap,
                                Long.parseLong(parts[1]),
                                parts[0]
                        )));
                spaceshipString = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCrewMembers(String dir, String file) {
        File resFile = new File(getClass().getClassLoader().getResource(dir + "/" + file).getFile());
        try (FileReader reader = new FileReader(resFile); BufferedReader br = new BufferedReader(reader)) {
            int symbol = br.read();
            String crewMember = "";
            while (symbol != -1) {
                char c = (char) symbol;
                if (c == ';') {
                    String role = crewMember.substring(0, crewMember.indexOf(','));
                    crewMember = crewMember.replace(role + ",", "");
                    String name = crewMember.substring(0, crewMember.indexOf(','));
                    crewMember = crewMember.replace(name + ",", "");
                    String rank = crewMember;

                    crewMembers.add(CREW_SERVICE.createCrewMember(CREW_MEMBER_FACTORY.create(
                            Role.resolveRoleById(Long.parseLong(role)),
                            name,
                            Rank.resolveRankById(Long.parseLong(rank)))));
                    crewMember = "";
                } else {
                    crewMember += Character.toString(c);
                }
                symbol = br.read(); // Читаем символ
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
