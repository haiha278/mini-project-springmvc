package lg.cns.miniproject.service.project;

import lg.cns.miniproject.dto.project.GetAllProjectDTO;
import lg.cns.miniproject.entity.Project;
import lg.cns.miniproject.modelMapper.Mapper;
import lg.cns.miniproject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private Mapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, Mapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllProjectDTO> getAllProject() {
        List<GetAllProjectDTO> projectList = projectRepository.findAll().stream().map(project -> {
            GetAllProjectDTO getAllProjectDTO = (GetAllProjectDTO) mapper.mapToDTO(project, GetAllProjectDTO.class);
            return getAllProjectDTO;
        }).toList();
        return projectList;
    }
}
