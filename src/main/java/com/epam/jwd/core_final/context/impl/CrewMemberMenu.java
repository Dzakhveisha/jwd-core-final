package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewMemberServiceImpl;

public class CrewMemberMenu extends NassaMenu {

    private final CrewService CREW_SERVICE = CrewMemberServiceImpl.getInstance();


    public CrewMemberMenu(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void printAvailableOptions() {
        System.out.println(" 1 -> search\n 2 -> automatic update");
    }

    @Override
    public int handleUserInput() {
        int comm = scan.nextInt();
        if (comm == 1) {
            CrewMemberCriteria criteria = new CrewMemberCriteria();
            System.out.println("Write one or more numbers to select an action: ");
            System.out.println(" 1 -> view all\n 2 -> find by name \n 3 -> find  by id\n 4 -> find all ready\n" +
                    " 5 -> find by Role and Rank ");
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
                        System.out.println("Enter the id of the Role for search (if any - 0): ");
                        long id = scan.nextLong();
                        if (id != 0) {
                            criteria.searchByRole(Role.resolveRoleById(id));
                        }
                        System.out.println("Enter the id of the Rank for search (if any - 0): ");
                        id = scan.nextLong();
                        if (id != 0) {
                            criteria.searchByRank(Rank.resolveRankById(id));
                        }
                        break;
                }
            }
            System.out.println(CREW_SERVICE.findAllCrewMembersByCriteria(criteria));
        } else {
            System.out.println("Enter the id of Crew Member :");
            long id = scan.nextLong();
            CrewMember update = CREW_SERVICE.updateCrewMemberDetails(
                    CREW_SERVICE.findCrewMemberByCriteria(new CrewMemberCriteria().searchByID(id)).
                            orElse(new CrewMember(Role.PILOT, "", Rank.CAPTAIN)));
            System.out.println(update);
        }
        return 0;
    }
}
