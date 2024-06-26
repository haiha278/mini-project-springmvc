package lg.cns.miniproject.service.team;

import lg.cns.miniproject.dto.team.GetAllTeamDTO;
import lg.cns.miniproject.entity.Team;
import lg.cns.miniproject.modelMapper.Mapper;
import lg.cns.miniproject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private Mapper mapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, Mapper mapper) {
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllTeamDTO> getAllTeam() {
        List<GetAllTeamDTO> teamList = teamRepository.findAll().stream().map(team -> {
            GetAllTeamDTO getAllTeamDTO = (GetAllTeamDTO) mapper.mapToDTO(team, GetAllTeamDTO.class);
            return getAllTeamDTO;
        }).toList();
        return teamList;
    }
}
