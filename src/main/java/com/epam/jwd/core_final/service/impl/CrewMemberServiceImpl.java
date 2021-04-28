package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CrewMemberServiceImpl implements CrewService {

    private static CrewMemberServiceImpl instance;

    private final CrewMemberFactory CREW_MEMBER_FACTORY = CrewMemberFactory.getInstance();

    private List<CrewMember> crewList = new ArrayList<>();

    private CrewMemberServiceImpl(){}

    public static CrewMemberServiceImpl getInstance() {
        if (instance == null) {
            synchronized( CrewMemberServiceImpl.class) {
                if (instance == null) {
                    instance = new  CrewMemberServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return (List<CrewMember>) crewList;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
         return crewList.stream().filter((Predicate<? super CrewMember>) criteria.build()).collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return crewList.stream().filter((Predicate<? super CrewMember>) criteria.build()).findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        if (crewList.contains(crewMember)) {
            CrewMember updateMember = CREW_MEMBER_FACTORY.create(
                    Role.resolveRoleById(1 + (int) (Math.random() * 4)), // random
                    crewMember.getName(),
                    Rank.resolveRankById(1 + (int) (Math.random() * 4)));  // random;

            crewList.remove(crewList.indexOf(crewMember)); // delete old
            return createCrewMember(updateMember);
        }
        else {
            throw new UnknownEntityException("Crew member");
        }
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        crewList.add(crewMember);
        return crewMember;
    }
}
