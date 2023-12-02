package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clocktimeapi.clocktimeapi.exceptions.UserIdNotFoundException;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.dto.TimekeepingReadDTO;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities.TimekeepingEntity;
import br.com.clocktimeapi.clocktimeapi.modules.timekeeping.repositories.TimekeepingRepository;

@Service
public class TimekeepingReadService {
    
    @Autowired
    private TimekeepingRepository workdayRepository;

    public TimekeepingReadDTO read(Integer user_id) {
        TimekeepingEntity workday = workdayRepository.findById(user_id)
                .orElseThrow(() -> new UserIdNotFoundException());

        TimekeepingReadDTO workdayReadDTO = new TimekeepingReadDTO(
            workday.getEmployee(),
            workday.getCheck_in(),
            workday.getCheck_out());

        return workdayReadDTO;
    }

}
