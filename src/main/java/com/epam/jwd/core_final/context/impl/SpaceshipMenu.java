package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipMenu extends NassaMenu {

    private final SpaceshipService SPACESHIP_SERVICE = SpaceshipServiceImpl.getInstance();

    public static final Logger LOGGER = LoggerFactory.getLogger("LOGGER");

    public SpaceshipMenu(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void printAvailableOptions() {
        System.out.println(" 1 -> search\n 2 -> automatic update");
    }

    @Override
    public int handleUserInput() {
        int comm = scan.nextInt();
        if ( comm == 1) {
            SpaceshipCriteria criteria = new SpaceshipCriteria();
            System.out.println("Write one or more numbers to select an action: ");
            System.out.println(" 1 -> view all\n 2 -> find by name \n 3 -> find  by id\n 4 -> find all ready\n" +
                    " 5 -> find by max and min distance\n ");
            scan.nextLine();
            String command = scan.nextLine();
            for (char symbol : command.toCharArray()) {
                switch (symbol) {
                    case '2':
                        System.out.println("Enter the name for search: ");
                        criteria.searchByName(scan.nextLine());
                        break;
                    case '3':
                        System.out.println("Enter the id for search: ");
                        criteria.searchByID(scan.nextLong());
                        break;
                    case '4':
                        criteria.searchByReadiness();
                        break;
                    case '5':
                        System.out.println("Enter the min distance:");
                        long number = scan.nextLong();
                        criteria.searchByMinDistance(number);
                        System.out.println("Enter the max distance: ");
                        number = scan.nextLong();
                        criteria.searchByMaxDistance(number);
                        break;
                }
            }
            System.out.println(SPACESHIP_SERVICE.findAllSpaceshipsByCriteria(criteria));
        } else {
            System.out.println("Enter the id of Spaceship :");
            long id = scan.nextLong();
            try {
                Spaceship update = SPACESHIP_SERVICE.updateSpaceshipDetails(
                        SPACESHIP_SERVICE.findSpaceshipByCriteria(new SpaceshipCriteria().searchByID(id)).
                                orElse(null));
                System.out.println(update);
            }
            catch (UnknownEntityException ex){
               LOGGER.error(ex.getMessage());
            }
        }
        return 0;
    }
}

