package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    public CrewMemberCriteria searchByRank(Rank rank){
        this.predicates.add(crewMember -> crewMember.getRank() == rank);
        return this;
    }

    public CrewMemberCriteria searchByRole(Role role){
        this.predicates.add(crewMember -> crewMember.getRole() == role);
        return this;
    }

    public CrewMemberCriteria searchByReadiness(){
        this.predicates.add(CrewMember::getReadyForNextMissions);
        return this;
    }
}
